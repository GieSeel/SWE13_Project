package de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar;

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
    public boolean cleanupHoverInfo();

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
