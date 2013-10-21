package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.booking_mgt.BookingMgr;
import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.MapPanelController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.MapAreas;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.MapController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.options.OptionController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.SearchPanelController;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchMgr;

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

    public GuiController(
	    final HashMap<Class<?>, BaseDataObjectMgr> theDataObjectMgrs) {
	dataObjectMgr = theDataObjectMgrs;
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
     *
     */
    private void init() {
	view = new Gui();
	adminTabsCtrl = new AdministrationTabsController();
	searchPanelController = new SearchPanelController(
		(CountryMgr) dataObjectMgr.get(CountryMgr.class),
		(BookingMgr) dataObjectMgr.get(BookingMgr.class),
		(PersonMgr) dataObjectMgr.get(PersonMgr.class));

	final MapController mapController;
	mapController = new MapController("map/Valalta_BigMap_v8.png",
		new MapAreas().getAreas(),
		(PitchMgr) dataObjectMgr.get(PitchMgr.class));
	final MapInformationController mapInfoController = new MapInformationController();
	mapPanelCtrl = new MapPanelController(mapController, mapInfoController);

	optionController = new OptionController();
	addlanguageListener();

	initAdministration();
	initView();
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

    private final HashMap<Class<?>, BaseDataObjectMgr> dataObjectMgr;

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
