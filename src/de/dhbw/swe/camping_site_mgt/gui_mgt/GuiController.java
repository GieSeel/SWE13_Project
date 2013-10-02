package de.dhbw.swe.camping_site_mgt.gui_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map.*;

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

	final MapController mapController;
	mapController = new MapController("map/Valalta_BigMap_v7.png");
	final MapInformationController mapInfoController = new MapInformationController();
	mapPanelCtrl = new MapPanelController(mapController, mapInfoController);

	initAdministration();
	initView();
    }

    private void initAdministration() {
	adminTabsCtrl.addTab(lm.get(lp.MAP), mapPanelCtrl.getGuiSnippet());
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

    /** The {@link Gui}. */
    private final Gui view;
}
