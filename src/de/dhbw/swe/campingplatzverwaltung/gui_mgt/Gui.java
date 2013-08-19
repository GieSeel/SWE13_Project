package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;

public class Gui extends JFrame {

    static Gui gui;

    /** The {@link LanguageMgr}. */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    /**   */
    private static final long serialVersionUID = 1L;

    static public Gui getInstance() {
	if (gui == null) {
	    gui = new Gui();
	}
	return gui;
    }

    /**
     * Constructor.
     */
    public Gui() {
	super(lm.get(LanguageProperties.GUI_MAINFRAME_TITLE));
	initDisplay();
    }

    public boolean clearOutput() {
	return this.statusBar.clearOutput();
    }

    public void initGui() {
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	// this.setUndecorated(true); // FULL-fullscreen :)

	this.tab = new CampingplaceAdministrationTabbedPane();
	this.loginScreen = new LoginPanel();
	this.statusBar = new StatusBarPanel();
	this.statusBar.setPreferredSize(new Dimension(
		this.getPreferredSize().width, 20));

	this.setLayout(new BorderLayout());
	this.add(tab, BorderLayout.CENTER);
	this.add(statusBar, BorderLayout.SOUTH);

	this.setVisible(true);
    }

    public boolean outputMessage(final String txt) {
	return this.statusBar.outputMessage(txt);
    }

    /**
     * @return the initialized main {@link JPanel}
     */
    private JPanel getMainPanel() {
	mainPanel = new JPanel();
	mainPanel.setBackground(new Color(44, 15, 23));
	mainPanel.requestFocus();
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
     * Set close handling.
     */
    private void setCloseAppOn(final int keyEvent) {
	addKeyListener(new KeyAdapter() {
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
    private CampingplaceAdministrationTabbedPane tab;
}
