package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;

public class MapPanelController implements Displayable {

    public MapPanelController(final MapController mapController,
	    final MapInformationController mapInfoController) {
	mapCtrl = mapController;
	addMapListener();
	mapInfoCtrl = mapInfoController;

	initView();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    private void addMapListener() {
	mapCtrl.register(new MapListener() {

	    @Override
	    public void areaSelected(final Area area) {
		mapInfoCtrl.selectedArea(area);
	    }

	    @Override
	    public void pitchSelected(final PitchInterface pitch) {
		mapInfoCtrl.selectedPitch(pitch);
	    }
	});
    }

    private void initView() {
	view = new MapPanel();
	view.addMap(mapCtrl.getGuiSnippet());
	view.addInformation(mapInfoCtrl.getGuiSnippet());
    }

    /** The {@link MapController}. */
    private final MapController mapCtrl;

    /** The {@link MapInformationController}. */
    private final MapInformationController mapInfoCtrl;

    /** The view. */
    private MapPanel view;
}
