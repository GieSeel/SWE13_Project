package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.AreaInterface;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view.MapPanel;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationController;
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
	    public void areaSelected(final AreaInterface area) {
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