package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map;

import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.AreaInterface;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;

public interface MapListener {
    void areaSelected(AreaInterface area);

    void pitchSelected(PitchInterface pitch);
}
