package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.Area;

public class Pitch extends BaseDataObject implements PitchInterface {

    /**
     * Builds the {@link Pitch} shape.
     * 
     * @param xCoords
     *            the x coordinates
     * @param yCoords
     *            the y coordinates
     * @return the shape {@link Polygon}
     */
    private static Polygon buildShape(final int[] xCoords, final int[] yCoords) {
	if (xCoords == null || yCoords == null) {
	    return new Polygon();
	}

	final Polygon polygon = new Polygon(xCoords, yCoords, xCoords.length);
	return polygon;
    }

    public Pitch() {
	this(0, null, null, null, null, null, 0, 0, null, null);
    }

    public Pitch(final int id, final Pitch_Type type, final String area,
	    final Site deliveryPoint, final String characteristics,
	    final Pitch_NatureOfSoil natureOfSoil, final int width,
	    final int height, final int[] xCoords, final int[] yCoords) {
	this(id, type, area, deliveryPoint, characteristics, natureOfSoil, width,
		height, buildShape(xCoords, yCoords));
    }

    public Pitch(final int id, final Pitch_Type type, final String area,
	    final Site deliveryPoint, final String characteristics,
	    final Pitch_NatureOfSoil natureOfSoil, final int width,
	    final int height, final Polygon shape) {
	super(id);
	this.type = type;
	this.areaName = area;
	this.site = deliveryPoint;
	this.characteristics = characteristics;
	this.natureOfSoil = natureOfSoil;
	this.width = width;
	this.height = height;
	this.shape = shape;
    }

    public Pitch(final Pitch_Type type, final String area,
	    final Site deliveryPoint, final String characteristics,
	    final Pitch_NatureOfSoil natureOfSoil, final int width,
	    final int height, final int[] xCoords, final int[] yCoords) {
	this(0, type, area, deliveryPoint, characteristics, natureOfSoil, width,
		height, xCoords, yCoords);
    }

    @Override
    public boolean equals(final DataObject dataObject) {
	final Pitch object = (Pitch) dataObject;
	if (this.areaName.equals(object.getArea())
		&& this.characteristics.equals(object.getCharacteristics())
		&& this.site.equals(object.getDeliveryPoint())
		&& this.height == object.getHeight()
		&& this.natureOfSoil.equals(object.getNatureOfSoil())
		&& this.shape.equals(object.getShape())
		&& this.type.equals(object.getType())
		&& this.width == object.getWidth()) {
	    return true;
	}
	return false;
    }

    @Override
    public String getArea() {
	return areaName;
    }

    @Override
    public String getCharacteristics() {
	return characteristics;
    }

    @Override
    public Site getDeliveryPoint() {
	return getSite();
    }

    @Override
    public int getHeight() {
	return height;
    }

    @Override
    public String getName() {
	final StringBuilder name = new StringBuilder();
	name.append(areaName);
	for (int i = 4 - (getId() + "").length(); i > 0; i--) {
	    name.append("0");
	}
	name.append(getId());
	return name.toString();
    }

    @Override
    public Pitch_NatureOfSoil getNatureOfSoil() {
	return natureOfSoil;
    }

    @Override
    public Polygon getShape() {
	return shape;
    }

    @Override
    public Polygon getShape(final int xShift, final int yShift) {
	final int[] xPoints = shape.xpoints;
	final int[] yPoints = shape.ypoints;
	final int[] shiftedXPoints = new int[xPoints.length];
	final int[] shiftedYPoints = new int[yPoints.length];
	for (int i = 0; i < xPoints.length; i++) {
	    shiftedXPoints[i] = xPoints[i] - xShift;
	    shiftedYPoints[i] = yPoints[i] - yShift;
	}
	return new Polygon(shiftedXPoints, shiftedYPoints, shiftedXPoints.length);
    }

    public Site getSite() {
	return site;
    }

    @Override
    public String getTableName() {
	return "pitch";
    }

    @Override
    public Pitch_Type getType() {
	return type;
    }

    /**
     * Returns the width.
     * 
     * @return the width
     */
    @Override
    public int getWidth() {
	return width;
    }

    /**
     * Returns the xCoords.
     * 
     * @return the xCoords
     */
    @Override
    public String getxCoords() {
	return xCoords;
    }

    /**
     * Returns the yCoords.
     * 
     * @return the yCoords
     */
    @Override
    public String getyCoords() {
	return yCoords;
    }

    /** The {@link Area} name. */
    private final String areaName;
    /** The characteristics. */
    private final String characteristics;
    /** The height/depth of the pitch. */
    private final int height;
    /** The nature of the pitch soil. */
    private final Pitch_NatureOfSoil natureOfSoil;
    /** The shape of the pitch area. */
    private final Polygon shape;
    /** The delivery point {@link Site} */
    private final Site site;
    /** The type of the pitch. */
    private final Pitch_Type type;
    /** The width of the pitch. */
    private final int width;
    /** The x coordinates */
    private String xCoords;
    /** the */
    private String yCoords;
}
