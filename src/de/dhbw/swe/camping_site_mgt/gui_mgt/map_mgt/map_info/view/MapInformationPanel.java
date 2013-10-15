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
	initTAType();
	initPnlShape();
	initTASize();
	initTANatureOfSoil();
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
	final StringBuilder size = new StringBuilder();
	size.append(width / 10 + "," + width % (width / 10) + " m");
	size.append(" x ");
	size.append(height / 10 + "," + height % (height / 10) + " m");
	pitchSizeTa.setText(size.toString());
    }

    @Override
    public void setPitchName(final String name) {
	pitchNameTf.setText(name);
    }

    @Override
    public void setPitchShape(final Polygon shape) {
	shapePnl.setPolygon(shape);
	shapePnl.setLabel(pitchNameTf.getText());
    }

    @Override
    public void setPitchSoil(final Pitch_NatureOfSoil natureOfSoil) {
	shapePnl.setNature(getColor(natureOfSoil));
	natureOfSoilTa.setText(natureOfSoil.getDisplayName());
    }

    @Override
    public void setPitchType(final Pitch_Type type) {
	pitchTypeTa.setText(type.getDisplayName());
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
	deliveryPointTa.setBackground(defaultBgColor);
	add(lm.get(lp.DELIVERY_POINT) + ":", deliveryPointTa);
    }

    private void initPnlShape() {
	shapePnl = new ShapePanel();
	shapePnl.setPreferredSize(new Dimension(100, 230));
	add("", shapePnl);
    }

    private void initTACharacteristics() {
	characteristicsTa = new JTextArea();
	characteristicsTa.setBackground(defaultBgColor);
	add(lm.get(lp.DM_CHARACTERISTICS) + ":", characteristicsTa);
    }

    private void initTANatureOfSoil() {
	natureOfSoilTa = new JTextArea();
	natureOfSoilTa.setBackground(defaultBgColor);
	add(lm.get(lp.DM_NATURE_OF_SOIL) + ":", natureOfSoilTa);
    }

    private void initTASize() {
	pitchSizeTa = new JTextArea();
	pitchSizeTa.setBackground(defaultBgColor);
	add(lm.get(lp.SIZE) + ":", pitchSizeTa);
    }

    private void initTAType() {
	pitchTypeTa = new JTextArea();
	pitchTypeTa.setBackground(defaultBgColor);
	add(lm.get(lp.DM_TYPE) + ":", pitchTypeTa);
    }

    private void initTFArea() {
	areaTf = new JTextField();
	areaTf.setBackground(highlitedBgColor);
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
	pitchNameTf = new JTextField();
	pitchNameTf.setBackground(highlitedBgColor);
	pitchNameTf.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    delegate.getDelegator().pitchChangedTo(pitchNameTf.getText());
		}
	    }
	});
	add(lm.get(lp.PITCH) + ":", pitchNameTf);
    }

    private JTextField areaTf;

    private JTextArea characteristicsTa;

    private final Color defaultBgColor = new Color(238, 238, 238);
    private final Delegate<MapInformationListener> delegate = new Delegate<>(
	    MapInformationListener.class);
    private JTextArea deliveryPointTa;
    private final Color highlitedBgColor = new Color(230, 230, 230);
    private final LanguageMgr lm = LanguageMgr.getInstance();
    private LanguageProperties lp;
    private int mapInfoComponentWidth;
    private JTextArea natureOfSoilTa;
    private JTextField pitchNameTf;
    private JTextArea pitchSizeTa;
    private JTextArea pitchTypeTa;

    private ShapePanel shapePnl;
}
