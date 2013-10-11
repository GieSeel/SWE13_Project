package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view;

import javax.swing.JComponent;

public interface MapPanelInterface {
    /**
     * Add component for additional map information.
     * 
     * @param info
     *            the information component
     */
    void addInformation(JComponent info);

    /**
     * Add the camping ground map.
     * 
     * @param map
     *            the map
     */
    void addMap(JComponent map);
}
