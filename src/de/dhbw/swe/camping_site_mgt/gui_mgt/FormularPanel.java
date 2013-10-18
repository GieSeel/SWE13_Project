package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.Vector;

import javax.swing.*;

public class FormularPanel extends JPanel {

    /**   */
    private static final long serialVersionUID = 1L;

    public FormularPanel(final Vector<FormularEntry> entries) {
	final int numPairs = entries.size();

	setLayout(new SpringLayout());
	for (final FormularEntry entry : entries) {
	    final JComponent object = entry.getObject();
	    if (entry.getSubject() != null) {
		final JLabel label = new JLabel(entry.getSubject());
		add(label);
		label.setLabelFor(object);
	    }
	    add(object);
	}

	SpringUtilities.makeCompactGrid(this, numPairs, 2, // rows, cols
		2, 2, // initX, initY
		2, 2); // xPad, yPad
    }
}
