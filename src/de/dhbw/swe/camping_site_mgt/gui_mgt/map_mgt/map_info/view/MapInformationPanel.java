package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.view;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.gui_mgt.BaseFormularPanel;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationInterface;

public class MapInformationPanel extends BaseFormularPanel implements
	MapInformationInterface {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public MapInformationPanel() {
	super();
	initTFArea();
	initTFPichID();
    }

    @Override
    public JComponent getGuiSnippet() {
	return this;
    }

    @Override
    public void setAreaName(final String name) {
	areaTf.setText(name);
    }

    @Override
    public void setPitchName(final String name) {
	pitchIdTF.setText(name);
    }

    private void initTFArea() {
	areaTf = new JTextField();
	areaTf.setEnabled(false);
	add("Area:", areaTf);
    }

    private void initTFPichID() {
	pitchIdTF = new JTextField();
	pitchIdTF.setEnabled(false);
	add("Pitch:", pitchIdTF);
    }

    private JTextField areaTf;

    private JTextField pitchIdTF;
}
