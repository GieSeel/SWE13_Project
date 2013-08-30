package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;

import javax.swing.*;

public class MapPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public MapPanel() {
	final LayoutManager mapPanelLayout = new BorderLayout();
	setLayout(mapPanelLayout);

	mapPane = new MapPane("map/Valalta_BigMap_improved3_unfertig.png");

	add(mapPane, BorderLayout.WEST);
	add(new JLabel("ADDITIONAL INFORMATION"), BorderLayout.EAST);
    }

    private final MapPane mapPane;
}