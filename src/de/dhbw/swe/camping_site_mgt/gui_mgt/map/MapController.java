package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class MapController implements Displayable {
    public MapController(final String mapPath) {
	view = new Map(mapPath);
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    /** The view. */
    private final JComponent view;
}
