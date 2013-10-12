package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.view;

import java.awt.*;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.BaseFormularPanel;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationInterface;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

@SuppressWarnings("static-access")
public class MapInformationPanel extends BaseFormularPanel implements
	MapInformationInterface {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public MapInformationPanel() {
	super();
	initTFArea();
	initTFPichName();
	initPnlShape();
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
    public void setPitchCharacteristics(final String characteristics) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPitchDeliveryPoint(final Site deliveryPoint) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPitchExpanse(final int width, final int height) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPitchName(final String name) {
	pitchIdTF.setText(name);
    }

    @Override
    public void setPitchShape(final Polygon shape) {
	shapePnl.setPolygon(shape);
    }

    @Override
    public void setPitchSoil(final Pitch_NatureOfSoil natureOfSoil) {
	shapePnl.setNature(getColor(natureOfSoil));
	// TODO Auto-generated method stub

    }

    @Override
    public void setPitchType(final Pitch_Type type) {
	// TODO Auto-generated method stub

    }

    /**
     * Detects the color for nature.
     * 
     * @param natureOfSoil
     *            the {@link Pitch_NatureOfSoil}
     * @return the {@link Color}
     */
    private Color getColor(final Pitch_NatureOfSoil natureOfSoil) {
	if (natureOfSoil == Pitch_NatureOfSoil.GRASS) {
	    return Color.GREEN;
	}
	if (natureOfSoil == Pitch_NatureOfSoil.SAND) {
	    return Color.YELLOW;
	}
	// TODO add other colors
	return null;
    }

    private void initPnlShape() {
	shapePnl = new ShapePanel();
	shapePnl.setPreferredSize(new Dimension(100, 150));
	add("", shapePnl);
    }

    private void initTFArea() {
	areaTf = new JTextField();
	areaTf.setEnabled(false);
	add(lm.get(lp.AREA) + ":", areaTf);
    }

    private void initTFPichName() {
	pitchIdTF = new JTextField();
	pitchIdTF.setEnabled(false);
	add(lm.get(lp.PITCH) + ":", pitchIdTF);
    }

    private JTextField areaTf;
    private final LanguageMgr lm = LanguageMgr.getInstance();

    private LanguageProperties lp;

    private JTextField pitchIdTF;

    private ShapePanel shapePnl;
}
