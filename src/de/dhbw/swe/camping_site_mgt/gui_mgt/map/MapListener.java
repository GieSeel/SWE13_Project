package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;

public interface MapListener {
    void areaSelected(Area area);

    void pitchSelected(PitchInterface pitch);
}
