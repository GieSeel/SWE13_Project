package de.dhbw.swe.camping_site_mgt.gui_mgt;

import javax.swing.JTabbedPane;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view.MapPanel;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.SearchPanel;

public class CampingplaceAdministrationTabbedPane extends JTabbedPane {

    /**   */
    private static final long serialVersionUID = 1L;

    public CampingplaceAdministrationTabbedPane() {
	lm = LanguageMgr.getInstance();

	setTabPlacement(JTabbedPane.BOTTOM);

	// map = new MapPanel();
	// this.addTab(lm.get(lp.MAP), map);
	// this.setMnemonicAt(2, KeyEvent.VK_3);

	// search = new SearchPanel();
	// this.addTab("Search_Tab", search);
	// this.setMnemonicAt(0, KeyEvent.VK_1);

	// formular = new FormularPanel();
	// this.addTab("Formular_Tab", formular);
	// this.setMnemonicAt(1, KeyEvent.VK_2);

	// options = new OptionsPanel();
	// addTab(lm.get(lp.GUI_TAB_OPTIONS), options);
    }

    private final FormularPanel formular = null;
    private final LanguageMgr lm;
    private LanguageProperties lp;
    private final MapPanel map = null;
    private final OptionsPanel options = null;
    private final SearchPanel search = null;
}
