package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Pitch implements PitchInterface {

    /**
     * Builds the {@link Pitch} shape.
     * 
     * @param xCoords
     *            the x coordinates in pattern: <code>[x1, x2, x3]</code>
     * @param yCoords
     *            the y coordinates in pattern: <code>[y1, y2, y3]</code>
     * @return the shape {@link Polygon}
     */
    private static Polygon buildShape(final String xCoords, final String yCoords) {
	String trimedXCords = xCoords.replaceAll(" ", "");
	String trimedYCords = yCoords.replaceAll(" ", "");
	trimedXCords = trimedXCords.substring(1, trimedXCords.length() - 1);
	trimedYCords = trimedYCords.substring(1, trimedYCords.length() - 1);

	final String[] xPointStr = trimedXCords.split(",");
	final int[] xPoints = new int[xPointStr.length];
	for (int i = 0; i < xPointStr.length; i++) {
	    xPoints[i] = Integer.parseInt(xPointStr[i]);
	}

	final String[] yPointStr = trimedYCords.split(",");
	final int[] yPoints = new int[yPointStr.length];
	for (int i = 0; i < yPointStr.length; i++) {
	    yPoints[i] = Integer.parseInt(yPointStr[i]);
	}

	return new Polygon(xPoints, yPoints, xPoints.length);
    }

    public Pitch() {
	this(0, null, null, null, 0, null, null, 0, null, null);
    }

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String district, final int length,
	    final String natureOfSoil, final Pitch_Type type, final int width,
	    final Polygon shape) {
	this.id = id;
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.area = district;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
	this.shape = shape;
    }

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String district, final int length,
	    final String natureOfSoil, final Pitch_Type type, final int width,
	    final String xCoords, final String yCoords) {
	this(id, characteristics, deliveryPoint, district, length, natureOfSoil,
		type, width, buildShape(xCoords, yCoords));
    }

    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String district, final int length, final String natureOfSoil,
	    final Pitch_Type type, final int width, final String xCoords,
	    final String yCoords) {
	this(0, characteristics, deliveryPoint, district, length, natureOfSoil,
		type, width, xCoords, yCoords);
    }

    @Override
    public String getArea() {
	return area;
    }

    @Override
    public String getCharacteristics() {
	return characteristics;
    }

    @Override
    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    @Override
    public int getId() {
	return id;
    }

    @Override
    public int getLength() {
	return length;
    }

    @Override
    public String getNatureOfSoil() {
	return natureOfSoil;
    }

    @Override
    public Pitch_Type getType() {
	return type;
    }

    @Override
    public int getWidth() {
	return width;
    }

    public Pitch setDatabaseData(final HashMap<String, Object> objects) {
	this.deliveryPoint = DatabaseController.getInstance().querySelectSite(
		(int) objects.get("deliveryPoint"));
	setData(objects);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.characteristics = (String) objects.get("characteristics");
	this.area = (String) objects.get("district");
	this.length = (int) objects.get("length");
	this.natureOfSoil = (String) objects.get("natureOfSoil");
	this.type = (Pitch_Type) objects.get("type");
	this.width = (int) objects.get("width");
	final String xCoords = (String) objects.get("xCoords");
	final String yCoords = (String) objects.get("yCoords");
	this.shape = buildShape(xCoords, yCoords);
    }

    private String area;
    private String characteristics;
    private Site deliveryPoint;
    private int id;
    private int length;
    // private final Pitch_NatureOfSoil natureOfSoil;
    private String natureOfSoil;
    private Polygon shape;
    private Pitch_Type type;
    private int width;
}
