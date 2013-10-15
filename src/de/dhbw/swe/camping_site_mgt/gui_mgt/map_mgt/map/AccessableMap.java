package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map;

import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.Area;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

public interface AccessableMap {
    /**
     * @return the selected {@link Area}.
     */
    Area getSelectedArea();

    /**
     * @return the selected {@link Pitch}.
     */
    PitchInterface getSelectedPitch();

    /**
     * Sets the selected {@link Area}.
     * 
     * @param areName
     *            the name of the {@link Area}
     */
    void setSelectedArea(String areName);

    /**
     * Sets the selected {@link Pitch}
     * 
     * @param pitchNumber
     *            the name of the {@link Pitch}
     */
    void setSelectedPitch(int pitchNumber);
}
