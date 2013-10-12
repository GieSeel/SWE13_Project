package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info;

import java.awt.Polygon;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.Area;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

public interface MapInformationInterface extends Displayable {

    /**
     * Set the {@link Area} name information on screen.
     * 
     * @param name
     *            the {@link Area} name
     */
    void setAreaName(String name);

    /**
     * Set the {@link Pitch} characteristics information on screen.
     * 
     * @param characteristics
     *            the description
     */
    void setPitchCharacteristics(String characteristics);

    /**
     * Set the {@link Pitch} delivery point information on screen.
     * 
     * @param deliveryPoint
     *            the delivery {@link Site}
     */
    void setPitchDeliveryPoint(Site deliveryPoint);

    /**
     * Set the {@link Pitch} expanse information on screen.
     * 
     * @param width
     *            the width
     * @param height
     *            the height
     */
    void setPitchExpanse(int width, int height);

    /**
     * Set the {@link Pitch} name information on screen.
     * 
     * @param name
     *            the name
     */
    void setPitchName(String name);

    /**
     * Set the {@link Pitch} shape information on screen.
     * 
     * @param shape
     *            the {@link Polygon}
     */
    void setPitchShape(Polygon shape);

    /**
     * Set the {@link Pitch} nature of soil information on screen.
     * 
     * @param natureOfSoil
     *            the {@link Pitch_NatureOfSoil}
     */
    void setPitchSoil(Pitch_NatureOfSoil natureOfSoil);

    /**
     * Set the {@link Pitch} type information on screen.
     * 
     * @param type
     *            the {@link Pitch_Type}
     */
    void setPitchType(Pitch_Type type);

}
