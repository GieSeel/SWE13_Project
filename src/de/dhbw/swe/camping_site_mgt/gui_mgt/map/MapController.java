package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class MapController implements Displayable {
    public MapController() {
	view = new Map("map/Valalta_BigMap_v7.png");
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    /** The view. */
    private final JComponent view;
}
