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
	    lpGbc.weightx = 1;
	    final JLabel label = new JLabel(string, JLabel.LEFT);
	    label.setPreferredSize(new Dimension(280, 30));
	    this.add(label, lpGbc);
	    lpGbc.weighty = 2;
	    lpGbc.gridy = 1;
	    this.add(c, lpGbc);
	}
    }

    /**   */
    private static final long serialVersionUID = 1L;

    public BaseFormularPanel() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void add(final String subject, final JComponent value) {
	add(new LabelPanel(subject, value));
    }
}
