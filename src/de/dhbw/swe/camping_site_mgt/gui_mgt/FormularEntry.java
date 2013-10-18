package de.dhbw.swe.camping_site_mgt.gui_mgt;

import javax.swing.JComponent;

public class FormularEntry {

    /**
     * Constructor.
     * 
     * @param subject
     *            the entries subject
     * @param object
     *            the entries object
     */
    public FormularEntry(final String subject, final JComponent object) {
	super();
	this.subject = subject;
	this.object = object;
    }

    /**
     * Returns the object.
     * 
     * @return the object
     */
    public JComponent getObject() {
	return object;
    }

    /**
     * Returns the subject.
     * 
     * @return the subject
     */
    public String getSubject() {
	return subject;
    }

    /** The entry object. */
    private final JComponent object;
    /** The entry subject. */
    private final String subject;
}
