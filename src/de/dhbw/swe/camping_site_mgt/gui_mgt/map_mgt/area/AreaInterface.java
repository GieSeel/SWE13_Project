package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area;

import java.awt.*;

public interface AreaInterface {
    /**
     * @return the frame containing the shape.
     */
    Rectangle getAreaFrame();

    /**
     * @return the name.
     */
    String getName();

    /**
     * @return the pitch count.
     */
    int getPitchCount();

    /**
     * @return the {@link Polygon} shape.
     */
    Polygon getPoly();

    /**
     * @return the scaled x points.
     */
    int[] getScaledxPoints();

    /**
     * @return the scaled y points.
     */
    int[] getScaledyPoints();

    /**
     * @return the x points.
     */
    int[] getxPoints();

    /**
     * @return the y points.
     */
    int[] getyPoints();
}
