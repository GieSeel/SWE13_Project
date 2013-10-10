package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;

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
     * @return the length of the Pitch in <code>dm</code>.
     */
    int getLength();

    /**
     * @return the description for the {@link Pitch} nature of soil.
     */
    Pitch_NatureOfSoil getNatureOfSoil();

    /**
     * Returns the shape.
     * 
     * @return the shape as {@link Polygon}.
     */
    Polygon getShape();

    /**
     * Get the shifted shape.
     * 
     * @param xShift
     *            the pixels count to shift in x direction
     * @param yShift
     *            the pixels count to shift in y direction
     * @return the shifted {@link Polygon}
     */
    Polygon getShape(int xShift, int yShift);

    /**
     * @return the {@link Pitch_Type} of the {@link Pitch}.
     */
    Pitch_Type getType();

    /**
     * @return the width of the {@link Pitch}.
     */
    int getWidth();

    /**
     * Returns the shape xCoords.
     * 
     * @return the xCoords in pattern: <code>[x1, x2, x3]</code>
     */
    String getxCoords();

    /**
     * Returns the shape yCoords.
     * 
     * @return the yCoords in pattern: <code>[y1, y2, y3]</code>
     */
    String getyCoords();

}
