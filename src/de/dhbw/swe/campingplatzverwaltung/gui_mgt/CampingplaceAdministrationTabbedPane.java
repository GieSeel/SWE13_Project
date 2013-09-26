package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import javax.swing.JTabbedPane;

import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;
import de.dhbw.swe.campingplatzverwaltung.gui_mgt.map.MapPanel;

public class CampingplaceAdministrationTabbedPane extends JTabbedPane {

    /**   */
    private static final long serialVersionUID = 1L;

    public CampingplaceAdministrationTabbedPane() {
	lm = LanguageMgr.getInstance();

	map = new MapPanel();
	this.addTab(lm.get(lp.MAP), map);
	// this.setMnemonicAt(2, KeyEvent.VK_3);

	// search = new SearchPanel();
	// this.addTab("Search_Tab", search);
	// this.setMnemonicAt(0, KeyEvent.VK_1);
	//
	// formular = new FormularPanel();
	// this.addTab("Formular_Tab", formular);
	// // this.setMnemonicAt(1, KeyEvent.VK_2);

	options = new OptionsPanel();
	addTab(lm.get(lp.GUI_TAB_OPTIONS), options);
    }

    private final FormularPanel formular = null;
    private final LanguageMgr lm;
    private LanguageProperties lp;
    private final MapPanel map;
    private final OptionsPanel options;
    private final SearchPanel search = null;
}
