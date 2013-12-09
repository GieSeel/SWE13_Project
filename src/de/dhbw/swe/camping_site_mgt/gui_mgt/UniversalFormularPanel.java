package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.*;

import javax.swing.*;

public class UniversalFormularPanel extends JPanel {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public UniversalFormularPanel() {
	setLayout(new GridBagLayout());
	gbc = new GridBagConstraints();

	gbc.fill = GridBagConstraints.BOTH;
	gbc.insets = new Insets(3, 1, 5, 2);

	currentLine = 0;
	// gbc.weighty = 1;
	// gbc.anchor = GridBagConstraints.NORTH;
    }

    /**
     * Ads a entry to the form without description.
     * 
     * @param object
     *            the content
     */
    public void add(final JComponent object) {
	add(object, GridBagConstraints.CENTER);
    }

    /**
     * Ads a entry to the form without description.
     * 
     * @param object
     *            the content
     * @param position
     *            the position from {@link GridBagConstraints}
     */
    public void add(final JComponent object, final int position) {

	gbc.gridy = currentLine;
	gbc.gridx = 0;
	gbc.weightx = 0.0;
	gbc.weighty = 0.0;
	gbc.gridwidth = 2;
	gbc.anchor = position;
	add(object, gbc);

	currentLine++;
    }

    /**
     * Ads a entry to the form.
     * 
     * @param title
     *            the entries title
     * @param object
     *            the entries content
     */
    public void add(final String title, final JComponent object) {
	gbc.gridy = currentLine;
	gbc.gridwidth = 1;
	gbc.weightx = 0.5;
	gbc.gridx = 0;
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	final JLabel titleLb = new JLabel(title);
	titleLb.setToolTipText(title);
	add(titleLb, gbc);

	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.CENTER;
	add(object, gbc);
	currentLine++;
    }

    /** The current line. */
    private int currentLine;

    /** The {@link GridBagConstraints}. */
    private final GridBagConstraints gbc;
}
