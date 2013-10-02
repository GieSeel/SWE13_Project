package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.*;

import javax.swing.*;

public class MapPanel extends JPanel implements MapPanelInterface {

    /** The serial Version UID. */
    private static final long serialVersionUID = 1L;

    public MapPanel() {
	final LayoutManager mapPanelLayout = new BorderLayout();
	setLayout(mapPanelLayout);

    }

    @Override
    public void addInformation(final JComponent info) {
	add(info, BorderLayout.EAST);
    }

    @Override
    public void addMap(final JComponent map) {
	add(map, BorderLayout.WEST);
    }

}