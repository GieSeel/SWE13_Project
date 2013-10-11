package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

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
	final Polygon polygon = new Polygon(xCoords, yCoords, xCoords.length);
	return polygon;
    }

    public Pitch() {
	this(0, null, null, null, 0, null, null, 0, null, null);
    }

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String area, final int length,
	    final Pitch_NatureOfSoil natureOfSoil, final Pitch_Type type,
	    final int width, final int[] xCoords, final int[] yCoords) {
	this(id, characteristics, deliveryPoint, area, length, natureOfSoil, type,
		width, buildShape(xCoords, yCoords));
    }

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String area, final int length,
	    final Pitch_NatureOfSoil natureOfSoil, final Pitch_Type type,
	    final int width, final Polygon shape) {
	super(id);
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.area = area;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
	this.shape = shape;
    }

    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String area, final int length,
	    final Pitch_NatureOfSoil natureOfSoil, final Pitch_Type type,
	    final int width, final int[] xCoords, final int[] yCoords) {
	this(0, characteristics, deliveryPoint, area, length, natureOfSoil, type,
		width, xCoords, yCoords);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Pitch object = (Pitch) dataObject;
	if (this.area.equals(object.getArea())
		&& this.characteristics.equals(object.getCharacteristics())
		&& this.deliveryPoint.equals(object.getDeliveryPoint())
		&& this.length == object.getLength()
		&& this.natureOfSoil.equals(object.getNatureOfSoil())
		&& this.shape.equals(object.getShape())
		&& this.type.equals(object.getType())
		&& this.width == object.getWidth()) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the area.
     * 
     * @return the area
     */
    @Override
    public String getArea() {
	return area;
    }

    /**
     * Returns the characteristics.
     * 
     * @return the characteristics
     */
    @Override
    public String getCharacteristics() {
	return characteristics;
    }

    /**
     * Returns the deliveryPoint.
     * 
     * @return the deliveryPoint
     */
    @Override
    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    /**
     * Returns the length.
     * 
     * @return the length
     */
    @Override
    public int getLength() {
	return length;
    }

    /**
     * Returns the natureOfSoil.
     * 
     * @return the natureOfSoil
     */
    @Override
    public Pitch_NatureOfSoil getNatureOfSoil() {
	return natureOfSoil;
    }

    /**
     * Returns the shape.
     * 
     * @return the shape
     */
    @Override
    public Polygon getShape() {
	return shape;
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
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

    private final String area;
    private final String characteristics;
    private final Site deliveryPoint;
    private final int length;
    private final Pitch_NatureOfSoil natureOfSoil;
    private final Polygon shape;
    private final Pitch_Type type;
    private final int width;
    private String xCoords;
    private String yCoords;
}
