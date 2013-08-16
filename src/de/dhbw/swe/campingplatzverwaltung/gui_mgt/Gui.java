package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;

import javax.swing.JFrame;

public class Gui extends JFrame {
    static Gui gui;

    /**   */
    private static final long serialVersionUID = 1L;

    static public Gui getInstance() {
	if (gui == null) {
	    gui = new Gui();
	}
	return gui;
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

    private LoginPanel loginScreen;
    private StatusBarPanel statusBar;
    private CampingplaceAdministrationTabbedPane tab;
}
