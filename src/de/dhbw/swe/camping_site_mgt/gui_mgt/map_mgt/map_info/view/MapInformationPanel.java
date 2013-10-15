package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.BaseFormularPanel;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view.MapPanelInterface;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map_info.MapInformationInterface;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

@SuppressWarnings("static-access")
public class MapInformationPanel extends BaseFormularPanel implements
	MapInformationInterface {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public MapInformationPanel() {
	super();

	initComponentWidth();
	initTFArea();
	initTFPichName();
	initPnlShape();
	initTACharacteristics();
	initDeliveryPoint();
    }

    @Override
    public JComponent getGuiSnippet() {
	return this;
    }

    /**
     * Registers a {@link MapInformationListener}.
     * 
     * @param mapInfoListener
     *            the {@link MapInformationListener}
     */
    @Override
    public void register(final MapInformationListener mapInfoListener) {
	delegate.register(mapInfoListener);
    }

    @Override
    public void setAreaName(final String name) {
	areaTf.setText(name);
    }

    @Override
    public void setPitchCharacteristics(final String characteristics) {
	characteristicsTa.setText(characteristics);
    }

    @Override
    public void setPitchDeliveryPoint(final Site deliveryPoint) {
	final StringBuilder text = new StringBuilder();
	text.append(deliveryPoint.getId() + "\r\n");
	text.append(deliveryPoint.getDescription());
	deliveryPointTa.setText(text.toString());
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
     * Unregisters a {@link MapInformationListener}.
     * 
     * @param mapInfoListener
     *            the {@link MapInformationListener}
     */
    @Override
    public void unregister(final MapInformationListener mapInfoListener) {
	delegate.unregister(mapInfoListener);
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
	    return new Color(22, 191, 67);
	}
	if (natureOfSoil == Pitch_NatureOfSoil.SAND) {
	    return new Color(254, 246, 135);
	}
	if (natureOfSoil == Pitch_NatureOfSoil.GRAVEL) {
	    return new Color(227, 205, 146);
	}
	if (natureOfSoil == Pitch_NatureOfSoil.SOIL) {
	    return new Color(198, 156, 7);
	}
	return null;
    }

    private void initComponentWidth() {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	final Dimension screenSize = toolkit.getScreenSize();
	mapInfoComponentWidth = (int) ((1f - MapPanelInterface.MAP_SCREEN_COVERAGE) * screenSize.getWidth()) - 13;
	setPreferredSize(new Dimension(mapInfoComponentWidth, 20));
    }

    private void initDeliveryPoint() {
	deliveryPointTa = new JTextArea();
	deliveryPointTa.setBackground(new Color(238, 238, 238));
	add(lm.get(lp.DELIVERY_POINT) + ":", deliveryPointTa);
    }

    private void initPnlShape() {
	shapePnl = new ShapePanel();
	shapePnl.setPreferredSize(new Dimension(100, 230));
	add("", shapePnl);
    }

    private void initTACharacteristics() {
	characteristicsTa = new JTextArea();
	characteristicsTa.setBackground(new Color(238, 238, 238));
	add(lm.get(lp.DM_CHARACTERISTICS) + ":", characteristicsTa);
    }

    private void initTFArea() {
	areaTf = new JTextField();
	areaTf.setBackground(new Color(230, 230, 230));
	areaTf.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    delegate.getDelegator().areaChangedto(areaTf.getText());
		}
	    }
	});
	add(lm.get(lp.AREA) + ":", areaTf);
    }

    private void initTFPichName() {
	pitchIdTF = new JTextField();
	pitchIdTF.setBackground(new Color(230, 230, 230));
	pitchIdTF.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    delegate.getDelegator().pitchChangedTo(pitchIdTF.getText());
		}
	    }
	});
	add(lm.get(lp.PITCH) + ":", pitchIdTF);
    }

    private JTextField areaTf;
    private JTextArea characteristicsTa;
    private final Delegate<MapInformationListener> delegate = new Delegate<>(
	    MapInformationListener.class);
    private JTextArea deliveryPointTa;
    private final LanguageMgr lm = LanguageMgr.getInstance();
    private LanguageProperties lp;
    private int mapInfoComponentWidth;
    private JTextField pitchIdTF;

    private ShapePanel shapePnl;
}
