package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;

import javax.swing.JComponent;

/**
 * the controller class for administrative parts.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class AdministrationTabsController implements Displayable {
    /**
     * Constructor.
     */
    public AdministrationTabsController() {
	tabs = new HashMap<>();
    }

    /**
     * Adds a new administration tab.
     * 
     * @param name
     *            the name of the tab
     * @param content
     *            the content for the tab
     * @return if adding was successful
     */
    public boolean addTab(final String name, final JComponent content) {
	if (!tabs.containsKey(name)) {
	    tabs.put(name, content);
	    return true;
	}
	return false;
    }

    @Override
    public JComponent getGuiSnippet() {
	view = new CampingplaceAdministrationTabbedPane();
	for (final String key : tabs.keySet()) {
	    view.addTab(key, tabs.get(key));
	}
	return view;
    }

    /** The views. */
    private final HashMap<String, JComponent> tabs;

    /** The view. */
    private CampingplaceAdministrationTabbedPane view;
}
