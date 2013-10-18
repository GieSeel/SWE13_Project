package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.LanguageListener;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.EditDialog;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.MapPanelController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.MapController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.options.OptionController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.*;

public class GuiController {
    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    /** The scale factor especially for map components. */
    private static float scaleFactor = 1;

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
	init();
    }

    public void register(final ApplicationClosedListener appClosedListener) {
	view.register(appClosedListener);
    }

    public void unregister(final ApplicationClosedListener appClosedListener) {
	view.unregister(appClosedListener);
    }

    private void addlanguageListener() {
	optionController.register(new LanguageListener() {

	    @Override
	    public void languageChanged() {
		repaintGui();
	    }
	});
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

    /**
     *
     */
    private void init() {
	view = new Gui();
	adminTabsCtrl = new AdministrationTabsController();
	searchPanelController = new SearchPanelController();

	final MapController mapController;
	mapController = new MapController("map/Valalta_BigMap_v8.png");
	final MapInformationController mapInfoController = new MapInformationController();
	mapPanelCtrl = new MapPanelController(mapController, mapInfoController);

	optionController = new OptionController();
	addlanguageListener();

	initAdministration();
	initView();

	addSearchTableListener();
    }

    @SuppressWarnings("static-access")
    private void initAdministration() {
	adminTabsCtrl.addTab(lm.get(lp.MAP), "icon/map.png",
		mapPanelCtrl.getGuiSnippet());
	adminTabsCtrl.addTab(lm.get(lp.SEARCH), "icon/search.png",
		searchPanelController.getGuiSnippet());
	// TODO other ad other tabs with controllers
	adminTabsCtrl.addTab(lm.get(lp.GUI_TAB_OPTIONS), "icon/settings.png",
		optionController.getGuiSnippet());
    }

    private void initView() {
	view.add(adminTabsCtrl.getGuiSnippet());
	view.setVisible();
    }

    private void repaintGui() {
	view.dispose();
	view = null;
	init();
    }

    /** The {@link AdministrationTabsController}. */
    private AdministrationTabsController adminTabsCtrl;

    /** The {@link LanguageProperties}. */
    private LanguageProperties lp;

    /** The {@link MapPanelController}. */
    private MapPanelController mapPanelCtrl;

    private OptionController optionController;

    /** The {@link SearchPanelController}. */
    private SearchPanelController searchPanelController;

    /** The {@link Gui}. */
    private Gui view = null;
}
