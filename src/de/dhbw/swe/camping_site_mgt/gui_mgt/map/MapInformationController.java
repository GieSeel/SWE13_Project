package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class MapInformationController implements Displayable {
    public MapInformationController() {
	view = new MapInformationPanel();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    /** The view. */
    private final JComponent view;
}
