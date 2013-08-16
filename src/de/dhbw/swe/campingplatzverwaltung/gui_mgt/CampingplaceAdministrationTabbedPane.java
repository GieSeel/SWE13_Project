package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import javax.swing.JTabbedPane;

public class CampingplaceAdministrationTabbedPane extends JTabbedPane {

    /**   */
    private static final long serialVersionUID = 1L;

    public CampingplaceAdministrationTabbedPane() {
	search = new SearchPanel();
	this.addTab("Search_Tab", search);
	// this.setMnemonicAt(0, KeyEvent.VK_1);

	formular = new FormularPanel();
	this.addTab("Formular_Tab", this.formular);
	// this.setMnemonicAt(1, KeyEvent.VK_2);

	map = new MapPanel();
	this.addTab("Map_Tab", this.map);
	// this.setMnemonicAt(2, KeyEvent.VK_3);
    }

    private final FormularPanel formular;
    private final MapPanel map;
    private final SearchPanel search;
}
