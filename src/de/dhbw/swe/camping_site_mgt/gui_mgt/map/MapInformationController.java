package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;

public class MapInformationController implements Displayable,
	MapInformationControllerInterface {
    public MapInformationController() {
	view = new JLabel("#####");
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    @Override
    public void setAreaName(final String name) {
	// TODO Auto-generated method stub

    }

    /** The view. */
    private final JComponent view;
}
