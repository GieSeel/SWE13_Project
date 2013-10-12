package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info;

import java.awt.Rectangle;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.AreaInterface;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.view.MapInformationPanel;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;

public class MapInformationController implements Displayable {
    public MapInformationController() {
	view = new MapInformationPanel();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view.getGuiSnippet();
    }

    public void selectedArea(final AreaInterface area) {
	if (area == null) {
	    return;
	}
	view.setAreaName(area.getName() + " - (" + area.getPitchCount() + ")");
    }

    public void selectedPitch(final PitchInterface pitch) {
	if (pitch == null) {
	    return;
	}
	view.setPitchName(pitch.getName());
	view.setPitchType(pitch.getType());
	view.setPitchDeliveryPoint(pitch.getDeliveryPoint());
	view.setPitchExpanse(pitch.getWidth(), pitch.getHeight());
	final Rectangle bounds = pitch.getShape().getBounds();
	view.setPitchSoil(pitch.getNatureOfSoil());
	view.setPitchShape(pitch.getShape(bounds.x, bounds.y));
	view.setPitchCharacteristics(pitch.getCharacteristics());
    }

    /** The view. */
    private final MapInformationInterface view;
}
