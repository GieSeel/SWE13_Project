package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class StatusBarPanel extends JPanel implements StatusBarInterface {
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public StatusBarPanel() {
	setBorder(new BevelBorder(BevelBorder.LOWERED));
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	statusLabel = new JLabel();
	statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
	add(statusLabel, BorderLayout.WEST);

	hoverLabel = new JLabel();
	hoverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	add(hoverLabel, BorderLayout.CENTER);

    }

    public boolean cleanupStatus() {
	statusLabel.setText("");
	return true;
    }

    @Override
    public boolean removeHoverInfo() {
	if (hoverLabel.getText().equals("")) {
	    return false;
	}
	hoverLabel.setText("");
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
