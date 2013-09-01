package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;

public class Gui extends JFrame {
    /** The scale factor especially for map components. */
    static float scaleFactor = 1;

    /** The singleton instance. */
    private static Gui instance;

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

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
	return statusBar.clearStatus();
    }

    public boolean setStatusBarStatus(final String txt) {
	return statusBar.setStatus(txt);
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

    private void initGui() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	// this.setUndecorated(true); // FULL-fullscreen :)

	tabs = new CampingplaceAdministrationTabbedPane();
	loginScreen = new LoginPanel();
	statusBar = new StatusBarPanel();
	statusBar.setPreferredSize(new Dimension(this.getPreferredSize().width, 20));

	setLayout(new BorderLayout());
	add(tabs, BorderLayout.CENTER);
	add(statusBar, BorderLayout.SOUTH);

	setVisible(true);
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

    private LoginPanel loginScreen;

    /** The main {@link JPanel}. */
    private JPanel mainPanel;

    private StatusBarPanel statusBar;
    private CampingplaceAdministrationTabbedPane tabs;
}
