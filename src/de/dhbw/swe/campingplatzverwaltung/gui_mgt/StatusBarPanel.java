package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class StatusBarPanel extends JPanel {
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public StatusBarPanel() {
	this.setBorder(new BevelBorder(BevelBorder.LOWERED));
	this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	statusLabel = new JLabel();
	statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
	this.add(statusLabel);
    }

    public boolean clearOutput() {
	this.statusLabel.setText("");
	return true;
    }

    public boolean outputMessage(final String txt) {
	this.statusLabel.setText(txt);
	return true;
    }

    private final JLabel statusLabel;
}
