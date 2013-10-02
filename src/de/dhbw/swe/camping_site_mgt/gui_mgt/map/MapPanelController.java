package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class MapPanelController implements Displayable {

    public MapPanelController(final MapController mapController,
	    final MapInformationController mapInfoController) {
	mapCtrl = mapController;
	mapInfoCtrl = mapInfoController;

	initView();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    private void initView() {
	view = new MapPanel();
	view.addMap(mapCtrl.getGuiSnippet());
	view.addMap(mapInfoCtrl.getGuiSnippet());
    }

    /** The {@link MapController}. */
    private final MapController mapCtrl;

    /** The {@link MapInformationController}. */
    private final MapInformationController mapInfoCtrl;

    /** The view. */
    private MapPanel view;
}
