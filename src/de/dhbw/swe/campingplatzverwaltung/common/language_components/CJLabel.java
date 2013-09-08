package de.dhbw.swe.campingplatzverwaltung.common.language_components;

import javax.swing.JLabel;

import de.dhbw.swe.campingplatzverwaltung.common.LanguageListener;

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
