package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.gui_mgt.BaseFormularPanel;

public class MapInformationPanel extends BaseFormularPanel {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public MapInformationPanel() {
	super();

	final JTextField areaTf = new JTextField("anything");
	areaTf.setEnabled(false);
	add("Area", areaTf);

	final JComboBox<Integer> pichesCount = new JComboBox<>();
	for (int i = 10; i <= 200; i++) {
	    pichesCount.addItem(i);
	}
	add("Ptiches", pichesCount);
    }
}
