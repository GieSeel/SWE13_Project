package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;
import java.util.*;

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
	    final String natureOfSoil, final String type, final int width,
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
	    final String natureOfSoil, final String type, final int width,
	    final String xCoords, final String yCoords) {
	this(id, characteristics, deliveryPoint, district, length, natureOfSoil,
		type, width, buildShape(xCoords, yCoords));
    }

    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String district, final int length, final String natureOfSoil,
	    final String type, final int width, final String xCoords,
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
    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("characteristics", this.characteristics);
	objects.put(
		"deliveryPoint",
		DatabaseController.getInstance().queryInsertUpdateSite(
			this.deliveryPoint));
	objects.put("district", this.area);
	objects.put("length", this.length);
	objects.put("natureOfSoil", this.natureOfSoil);
	objects.put("type", this.type);
	objects.put("width", this.width);
	objects.put("xCoords", Arrays.toString(shape.xpoints));
	objects.put("yCoords", Arrays.toString(shape.ypoints));
	return objects;
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
    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "pitch_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "characteristics", new String(this.characteristics));
	objects.put(className + "district", new String(this.area));
	objects.put(className + "length", new Integer(this.length));
	objects.put(className + "natureOfSoil", new String(this.natureOfSoil));
	objects.put(className + "type", new String(this.type));
	objects.put(className + "width", new Integer(this.width));
	objects.put(className + "xCoords", Arrays.toString(shape.xpoints));
	objects.put(className + "yCoords", Arrays.toString(shape.ypoints));

	objects.putAll(this.deliveryPoint.getTableData(className));

	return objects;
    }

    @Override
    public String getType() {
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

    public Pitch setTableData(final HashMap<String, Object> objects) {
	final String className = "pitch_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), siteMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("site_")) {
		siteMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.deliveryPoint = new Site().setTableData(siteMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.characteristics = (String) objects.get("characteristics");
	this.area = (String) objects.get("district");
	this.length = (int) objects.get("length");
	this.natureOfSoil = (String) objects.get("natureOfSoil");
	this.type = (String) objects.get("type");
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
    // private final Pitch_Type type;
    private String type;
    private int width;
}
