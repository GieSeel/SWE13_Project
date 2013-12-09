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

	public void remove() {
	    Toolkit.getDefaultToolkit().removeAWTEventListener(this);
	}

	private void keyTyped(final KeyEvent e) {
	    if (e.getKeyCode() == keyCode) {
		final int n = JOptionPane.showConfirmDialog(null,
			LM.get(LanguageProperties.QUESTION_CLOSE_APPLICATION),
			LM.get(LanguageProperties.GUI_CLOSE_DIALOG_TITLE),
			JOptionPane.YES_NO_OPTION);
		if (n == 0) {
		    closeApp();
		}
	    }
	}

	private final int keyCode;
    }

    /** The screen size. */
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static Boolean fullscreen = true;

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr LM = LanguageMgr.getInstance();

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Gui.class);
    /**   */
    private static final long serialVersionUID = 1L;

    public static void setScreenSize(final int width, final int height) {
	screenSize = new Dimension(width, height);
	fullscreen = false;
    }

    /**
     * Constructor.
     */
    public Gui() {
	logger.info("Initialize GUI ...");
	initDisplay();
	logger.info("Initialize GUI successful");
    }

    @Override
    public void dispose() {
	super.dispose();
	closeListener.remove();
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
     * Closing the application.
     */
    private void closeApp() {
	logger.info("Close Application...");
	dispose();
	logger.info("Closed UI!");
	delegate.getDelegator().closedApplication();
	logger.info("Closed Application!");
    }

    /**
     * Initialize closing functionality.
     */
    private void initClosingApp() {
	setCloseAppOn(KeyEvent.VK_ESCAPE);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent e) {
		closeApp();
	    }
	});
    }

    /**
     * Initializes the display.
     */
    private void initDisplay() {
	setTitle(LM.get(LanguageProperties.GUI_MAINFRAME_TITLE));
	sizeFrame();

	setLayout(new BorderLayout());
	setBackground(new Color(44, 15, 23));

	final JComponent statusBar = StatusBarController.getInstance().getGuiSnippet();
	add(statusBar, BorderLayout.SOUTH);

	initClosingApp();
    }

    /**
     * Set close handling on specific {@link KeyEvent}.
     * 
     * @param keyCode
     *            the keys key code
     */
    private void setCloseAppOn(final int keyCode) {
	closeListener = new GlobalKeyCloseListener(keyCode);
    }

    /**
     * positioning and sizing of the frame according to the screen;
     */
    private void sizeFrame() {
	if (fullscreen) {
	    setUndecorated(true);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	setBounds(0, 0, screenSize.width, screenSize.height);
    }

    private GlobalKeyCloseListener closeListener;

    private final Delegate<ApplicationClosedListener> delegate = new Delegate<>(
	    ApplicationClosedListener.class);
}
