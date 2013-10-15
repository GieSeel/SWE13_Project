package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.*;

import javax.swing.*;

public class BaseFormularPanel extends JPanel {

    class LabelPanel extends JPanel {

	/**   */
	private static final long serialVersionUID = 1L;

	public LabelPanel(final String string, final JComponent c) {
	    super();
	    this.setLayout(new GridBagLayout());
	    final GridBagConstraints lpGbc = new GridBagConstraints();
	    lpGbc.anchor = GridBagConstraints.CENTER;
	    lpGbc.fill = GridBagConstraints.HORIZONTAL;

	    if (horizontalAlignment) {
		lpGbc.gridx = 0;
		lpGbc.weightx = 0.5;
		final JLabel label = new JLabel(string, JLabel.LEFT);
		this.add(label, lpGbc);
		lpGbc.gridy = 0;
		lpGbc.gridx = 1;
	    } else {
		lpGbc.weightx = 1;
		final JLabel label = new JLabel(string, JLabel.LEFT);
		this.add(label, lpGbc);
		lpGbc.gridy = 1;
	    }
	    lpGbc.weighty = 2;

	    this.add(c, lpGbc);
	}
    }

    /**   */
    private static final long serialVersionUID = 1L;

    public BaseFormularPanel() {
	this(true);
    }

    public BaseFormularPanel(final Boolean horizontalAlignment) {
	this.horizontalAlignment = horizontalAlignment;
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void add(final String subject, final JComponent value) {
	add(new LabelPanel(subject, value));
    }

    private final Boolean horizontalAlignment;
}
