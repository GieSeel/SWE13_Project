package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;

public class MapInformationController implements Displayable {
    public MapInformationController() {
	view = new MapInformationPanel();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view.getGuiSnippet();
    }

    public void selectedArea(final Area area) {
	if (area == null) {
	    return;
	}
	view.setAreaName(area.getName());
    }

    public void selectedPitch(final PitchInterface pitch) {
	if (pitch == null) {
	    return;
	}
	view.setPitchName(pitch.getArea() + pitch.getId());
    }

    /** The view. */
    private final MapInformationInterface view;
}
