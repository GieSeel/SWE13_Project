package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar.StatusBarController;

public class Gui extends JFrame {
    private class GlobalKeyCloseListener implements AWTEventListener {
	public GlobalKeyCloseListener(final int keyCode) {
	    this.keyCode = keyCode;
	    Toolkit.getDefaultToolkit().addAWTEventListener(this,
		    AWTEvent.KEY_EVENT_MASK);
	}

	@Override
	public void eventDispatched(final AWTEvent event) {
	    if (event.getID() == KeyEvent.KEY_RELEASED) {
		keyTyped((KeyEvent) event);
	    }
	}

	private void keyTyped(final KeyEvent e) {
	    if (e.getKeyCode() == keyCode) {
		final int n = JOptionPane.showConfirmDialog(null,
			lm.get(LanguageProperties.QUESTION_CLOSE_APPLICATION),
			lm.get(LanguageProperties.GUI_CLOSE_DIALOG_TITLE),
			JOptionPane.YES_NO_OPTION);
		if (n == 0) {
		    logger.info("Close Application...");
		    dispose();
		    delegate.getDelegator().closedApplication();
		    logger.info("Closed Application!");
		}
	    }
	}

	private final int keyCode;
    }

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Gui.class);

    /**   */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public Gui() {
	super(lm.get(LanguageProperties.GUI_MAINFRAME_TITLE));
	logger.info("Initialize GUI ...");
	initDisplay();
	logger.info("Initialize GUI successful");
    }

    public void addAdministration(final CampingplaceAdministrationTabbedPane thePane) {
	add(thePane, BorderLayout.CENTER);
    }

    public void register(final ApplicationClosedListener appClosedListener) {
	delegate.register(appClosedListener);
    }

    public void setVisible() {
	setVisible(true);
    }

    public void unregister(final ApplicationClosedListener appClosedListener) {
	delegate.unregister(appClosedListener);
    }

    /**
     * Initializes the display.
     */
    private void initDisplay() {
	sizeFrame();

	setLayout(new BorderLayout());
	setBackground(new Color(44, 15, 23));

	final JComponent statusBar = StatusBarController.getInstance().getGuiSnippet();
	add(statusBar, BorderLayout.SOUTH);

	setFocusable(true);
	setCloseAppOn(KeyEvent.VK_ESCAPE);
    }

    /**
     * Set close handling on specific {@link KeyEvent}.
     * 
     * @param keyCode
     *            the keys key code
     */
    private void setCloseAppOn(final int keyCode) {
	new GlobalKeyCloseListener(keyCode);
    }

    /**
     * positioning and sizing of the frame according to the screen;
     */
    private void sizeFrame() {
	setUndecorated(true);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds(0, 0, screenSize.width, screenSize.height);
    }

    private final Delegate<ApplicationClosedListener> delegate = new Delegate<>(
	    ApplicationClosedListener.class);
}
