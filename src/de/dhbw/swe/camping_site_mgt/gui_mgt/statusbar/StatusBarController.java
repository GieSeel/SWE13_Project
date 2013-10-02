package de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class StatusBarController implements StatusBarInterface, Displayable {
    /** The singleton instance. */
    private static StatusBarController instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized StatusBarController getInstance() {
	if (instance == null) {
	    instance = new StatusBarController();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private StatusBarController() {
	statusBar = new StatusBar();
    }

    @Override
    public boolean cleanupStatus() {
	return statusBar.cleanupStatus();
    }

    @Override
    public JComponent getGuiSnippet() {
	return statusBar;
    }

    @Override
    public boolean cleanupHoverInfo() {
	return statusBar.cleanupHoverInfo();
    }

    @Override
    public void setHoverInfo(final String info) {
	statusBar.setHoverInfo(info);
    }

    @Override
    public boolean setStatus(final String txt) {
	return statusBar.setStatus(txt);
    }

    private final StatusBar statusBar;
}
