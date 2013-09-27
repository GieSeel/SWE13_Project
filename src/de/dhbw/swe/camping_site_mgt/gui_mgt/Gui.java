package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

public class Gui extends JFrame {
    /** The scale factor especially for map components. */
    static float scaleFactor = 1;

    /** The singleton instance. */
    private static Gui instance;

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Gui.class);

    /**   */
    private static final long serialVersionUID = 1L;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized Gui getInstance() {
	if (instance == null) {
	    instance = new Gui();
	    instance.initGui();
	    // instance.initDisplay();
	}
	return instance;
    }

    /**
     * @return the components scale factor.
     */
    public static float getScaleFactor() {
	return scaleFactor;
    }

    /**
     * Sets the scale factor.
     * 
     * @param scaleFactor
     *            the factor
     */
    public static void setScaleFactor(final float scaleFactor) {
	Gui.scaleFactor = scaleFactor;
    }

    /**
     * Private constructor. Singleton.
     */
    private Gui() {
	super(lm.get(LanguageProperties.GUI_MAINFRAME_TITLE));
    }

    public boolean clearStatus() {
	return statusBar.cleanupStatus();
    }

    /**
     * 
     * @return if the GUI was already initialized.
     */
    public boolean isInitialized() {
	return initialized;
    }

    public boolean setStatusBarStatus(final String txt) {
	return statusBar.setStatus(txt);
    }

    public void startupGui() {
	// this.setUndecorated(true); // FULL-fullscreen :)

	tabs = new CampingplaceAdministrationTabbedPane();
	loginScreen = new LoginPanel();

	add(tabs, BorderLayout.CENTER);

	setVisible(true);
	initialized = true;
    }

    /**
     * @return the initialized main {@link JPanel}
     */
    private JPanel getMainPanel() {
	mainPanel = new JPanel();
	mainPanel.setBackground(new Color(44, 15, 23));
	mainPanel.requestFocus();
	statusBar = new StatusBarPanel();
	statusBar.setPreferredSize(new Dimension(this.getPreferredSize().width, 20));
	mainPanel.add(statusBar, BorderLayout.SOUTH);

	tabs = new CampingplaceAdministrationTabbedPane();
	mainPanel.add(tabs, BorderLayout.CENTER);

	return mainPanel;
    }

    /**
     * Initializes the display.
     */
    private void initDisplay() {
	sizeFrame();
	setLayout(new BorderLayout());
	add(getMainPanel());
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	setCloseAppOn(KeyEvent.VK_ESCAPE);
    }

    /**
     * initialize the basic GUI.
     */
    private void initGui() {
	logger.info("Initialize GUI ...");

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setExtendedState(JFrame.MAXIMIZED_BOTH);

	statusBar = new StatusBarPanel();
	statusBar.setPreferredSize(new Dimension(this.getPreferredSize().width, 20));

	setLayout(new BorderLayout());
	add(statusBar, BorderLayout.SOUTH);

	logger.info("Initialize GUI successful");
    }

    /**
     * Set close handling.
     */
    private void setCloseAppOn(final int keyEvent) {
	mainPanel.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(final KeyEvent keyE) {
		if (keyE.getKeyCode() == keyEvent) {
		    final int n = JOptionPane.showConfirmDialog(mainPanel,
			    lm.get(LanguageProperties.QUESTION_CLOSE_APPLICATION),
			    lm.get(LanguageProperties.GUI_CLOSE_DIALOG_TITLE),
			    JOptionPane.YES_NO_OPTION);
		    if (n == 0) {
			dispose();
		    }
		}
	    }
	});
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

    /** The initialized indicator. */
    private boolean initialized = false;

    private LoginPanel loginScreen;

    /** The main {@link JPanel}. */
    private JPanel mainPanel;

    private StatusBarPanel statusBar;
    private CampingplaceAdministrationTabbedPane tabs;
}
