package de.dhbw.swe.camping_site_mgt.common.language_components;

import javax.swing.JLabel;

import de.dhbw.swe.camping_site_mgt.common.LanguageListener;

public abstract class CJLabel extends JLabel implements LanguageListener {

    /**   */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     */
    public CJLabel() {
	super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the label text
     */
    public CJLabel(final String text) {
	super(text);
    }

}
