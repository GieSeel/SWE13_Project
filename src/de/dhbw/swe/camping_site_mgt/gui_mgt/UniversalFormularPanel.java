package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.*;

import javax.swing.*;

public class UniversalFormularPanel extends JPanel {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public UniversalFormularPanel() {
	setLayout(new GridBagLayout());
	gridBagConstraints = new GridBagConstraints();
    }

    /**
     * Ads a entry to the form without description.
     * 
     * @param object
     *            the content
     */
    public void add(final JComponent object) {

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

    }

    private final GridBagConstraints gridBagConstraints;
}
