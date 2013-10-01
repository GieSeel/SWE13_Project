package de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

class StatusBar extends JPanel implements StatusBarInterface {
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public StatusBar() {
	setBorder(new BevelBorder(BevelBorder.LOWERED));
	setLayout(new BorderLayout());

	statusLabel = new JLabel(" ");
	statusLabel.setEnabled(false);
	statusLabel.setMinimumSize(new Dimension(80, 20));
	statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	add(statusLabel, BorderLayout.EAST);

	hoverLabel = new JLabel("");
	hoverLabel.setEnabled(false);
	hoverLabel.setMinimumSize(new Dimension(80, 20));
	hoverLabel.setHorizontalAlignment(SwingConstants.LEFT);
	add(hoverLabel, BorderLayout.CENTER);
    }

    @Override
    public boolean cleanupHoverInfo() {
	if (hoverLabel.getText().equals("")) {
	    return false;
	}
	hoverLabel.setText("");
	return true;
    }

    @Override
    public boolean cleanupStatus() {
	statusLabel.setText("");
	return true;
    }

    @Override
    public void setHoverInfo(final String info) {
	hoverLabel.setText(info);
    }

    @Override
    public boolean setStatus(final String txt) {
	statusLabel.setText(txt);
	return true;
    }

    /** The {@link JLabel} for the hovered Element. */
    private final JLabel hoverLabel;

    /** The Label for the current state. */
    private final JLabel statusLabel;
}
