package de.dhbw.swe.campingplatzverwaltung.common.language_components;

import javax.swing.JButton;

import de.dhbw.swe.campingplatzverwaltung.common.LanguageListener;

public abstract class CJButton extends JButton implements LanguageListener {
    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     */
    public CJButton() {
	super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            the text of the Button
     */
    public CJButton(final String text) {
	super(text);
    }
}
