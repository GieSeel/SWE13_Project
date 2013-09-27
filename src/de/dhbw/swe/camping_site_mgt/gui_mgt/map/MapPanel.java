package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.*;

import javax.swing.*;

public class MapPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public MapPanel() {
	final LayoutManager mapPanelLayout = new BorderLayout();
	setLayout(mapPanelLayout);

	mapPane = new Map("map/Valalta_BigMap_v7.png");

	add(mapPane, BorderLayout.WEST);
	add(new JLabel("ADDITIONAL INFORMATION"), BorderLayout.EAST);
    }

    private final Map mapPane;
}