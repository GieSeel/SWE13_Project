package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.EditDialog;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.MapPanelController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.MapController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.SearchPanelController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.SearchTableListener;

public class GuiController {
    /** The scale factor especially for map components. */
    static float scaleFactor = 1;

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    /**
     * @return the components scale factor.
     */
    public static float getScaleFactor() {
	return scaleFactor;
    }

    /**
     * Sets the scale factor.
     * 
     * @param scaleFactor
     *            the factor
     */
    public static void setScaleFactor(final float theScaleFactor) {
	scaleFactor = theScaleFactor;
    }

    public GuiController() {
	view = new Gui();
	adminTabsCtrl = new AdministrationTabsController();
	searchPanelController = new SearchPanelController();

	final MapController mapController;
	mapController = new MapController("map/Valalta_BigMap_v7.png");
	final MapInformationController mapInfoController = new MapInformationController();
	mapPanelCtrl = new MapPanelController(mapController, mapInfoController);

	initAdministration();
	initView();

	addSearchTableListener();
    }

    public void register(final ApplicationClosedListener appClosedListener) {
	view.register(appClosedListener);
    }

    public void unregister(final ApplicationClosedListener appClosedListener) {
	view.unregister(appClosedListener);
    }

    /**
     * Adds the {@link SearchTableListener}.
     */
    private void addSearchTableListener() {
	searchPanelController.register(new SearchTableListener() {

	    @Override
	    public void editRow(final HashMap<Integer, ColumnInfo> columns,
		    final HashMap<Integer, Object> values) {
		final EditDialog editDialog = new EditDialog(columns, values);
	    }
	});
    }

    @SuppressWarnings("static-access")
    private void initAdministration() {
	adminTabsCtrl.addTab(lm.get(lp.MAP), mapPanelCtrl.getGuiSnippet());
	adminTabsCtrl.addTab(lm.get(lp.SEARCH),
		searchPanelController.getGuiSnippet());
	// TODO other ad other tabs with controllers
	adminTabsCtrl.addTab(lm.get(lp.GUI_TAB_OPTIONS), new OptionsPanel());
    }

    private void initView() {
	view.add(adminTabsCtrl.getGuiSnippet());
	view.setVisible();
    }

    /** The {@link AdministrationTabsController}. */
    private final AdministrationTabsController adminTabsCtrl;

    /** The {@link LanguageProperties}. */
    private LanguageProperties lp;

    /** The {@link MapPanelController}. */
    private final MapPanelController mapPanelCtrl;

    /** The {@link SearchPanelController}. */
    private final SearchPanelController searchPanelController;

    /** The {@link Gui}. */
    private final Gui view;
}
