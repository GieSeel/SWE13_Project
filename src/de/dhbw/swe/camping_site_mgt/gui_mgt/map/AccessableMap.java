package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

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
}
