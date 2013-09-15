package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

public interface StatusBarInterface {
    /**
     * 
     * @return cleanup statusbar.
     */
    public boolean cleanupStatus();

    /**
     * 
     * @return
     */
    public boolean removeHoverInfo();

    /**
     * Set information for hovered element.
     * 
     * @param info
     */
    public void setHoverInfo(final String info);

    /**
     * Set message for current state/job which is in progress
     * 
     * @param txt
     *            the message text
     * @return if it was successful
     */
    public boolean setStatus(final String txt);
}
