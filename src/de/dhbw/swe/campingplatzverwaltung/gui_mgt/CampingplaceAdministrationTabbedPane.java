package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import javax.swing.JTabbedPane;

import de.dhbw.swe.campingplatzverwaltung.gui_mgt.map.MapPanel;

public class CampingplaceAdministrationTabbedPane extends JTabbedPane {

    /**   */
    private static final long serialVersionUID = 1L;

    public CampingplaceAdministrationTabbedPane() {
	map = new MapPanel();
	this.addTab("Map_Tab", this.map);
	// this.setMnemonicAt(2, KeyEvent.VK_3);

	search = new SearchPanel();
	this.addTab("Search_Tab", search);
	// this.setMnemonicAt(0, KeyEvent.VK_1);

	formular = new FormularPanel();
	this.addTab("Formular_Tab", this.formular);
	// this.setMnemonicAt(1, KeyEvent.VK_2);

    }

    private final FormularPanel formular;
    private final MapPanel map;
    private final SearchPanel search;
}
