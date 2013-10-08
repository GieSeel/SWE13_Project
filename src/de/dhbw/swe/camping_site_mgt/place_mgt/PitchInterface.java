package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.gui_mgt.map.Area;

/**
 * The interface for {@link Pitch}.
 * 
 * @author GieSeel
 * @version 1.0
 */
public interface PitchInterface {
    /**
     * @return the name of the {@link Area} it belongs to.
     */
    String getArea();

    /**
     * @return the characteristics of a {@link Pitch}.
     */
    String getCharacteristics();

    /**
     * @return the delivery point {@link Site}.
     */
    Site getDeliveryPoint();

    /**
     * @return the ID of the {@link Pitch}.
     */
    int getId();

    /**
     * @return the length of the Pitch in <code>dm</code>.
     */
    int getLength();

    /**
     * @return the description for the {@link Pitch} nature of soil.
     */
    String getNatureOfSoil();

    /**
     * @return the {@link Pitch_Type} of the {@link Pitch}.
     */
    Pitch_Type getType();

    /**
     * @return the width of the {@link Pitch}.
     */
    int getWidth();
}
