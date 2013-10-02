package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Pitch {

    public Pitch() {
	super();
	this.id = 0;
	this.characteristics = null;
	this.deliveryPoint = null;
	this.district = null;
	this.length = 0;
	this.natureOfSoil = null;
	this.type = null;
	this.xCoords = null;
	this.yCoords = null;
	this.width = 0;
    }

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String district, final int length,
	    final String natureOfSoil, final String type, final int width,
	    final String xCoords, final String yCoords) {
	super();
	this.id = id;
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.district = district;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
	this.xCoords = xCoords;
	this.yCoords = yCoords;
    }

    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String district, final int length, final String natureOfSoil,
	    final String type, final int width, final String xCoords,
	    final String yCoords) {
	super();
	this.id = 0;
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.district = district;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
	this.xCoords = xCoords;
	this.yCoords = yCoords;
    }

    public String getCharacteristics() {
	return characteristics;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("characteristics", this.characteristics);
	objects.put(
		"deliveryPoint",
		DatabaseController.getInstance().queryInsertUpdateSite(
			this.deliveryPoint));
	objects.put("district", this.district);
	objects.put("length", this.length);
	objects.put("natureOfSoil", this.natureOfSoil);
	objects.put("type", this.type);
	objects.put("width", this.width);
	objects.put("xCoords", this.xCoords);
	objects.put("yCoords", this.yCoords);
	return objects;
    }

    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    public String getDistrict() {
	return district;
    }

    public int getId() {
	return id;
    }

    public int getLength() {
	return length;
    }

    public String getNatureOfSoil() {
	return natureOfSoil;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "pitch_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "characteristics", new String(this.characteristics));
	objects.put(className + "district", new String(this.district));
	objects.put(className + "length", new Integer(this.length));
	objects.put(className + "natureOfSoil", new String(this.natureOfSoil));
	objects.put(className + "type", new String(this.type));
	objects.put(className + "width", new Integer(this.width));
	objects.put(className + "xCoords", new String(this.xCoords));
	objects.put(className + "yCoords", new String(this.yCoords));

	objects.putAll(this.deliveryPoint.getTableData(className));

	return objects;
    }

    public String getType() {
	return type;
    }

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
	this.district = (String) objects.get("district");
	this.length = (int) objects.get("length");
	this.natureOfSoil = (String) objects.get("natureOfSoil");
	this.type = (String) objects.get("type");
	this.width = (int) objects.get("width");
	this.xCoords = (String) objects.get("xCoords");
	this.yCoords = (String) objects.get("yCoords");
    }

    private String characteristics;
    private Site deliveryPoint;
    private String district;
    private int id;
    private int length;
    // private final Pitch_NatureOfSoil natureOfSoil;
    private String natureOfSoil;
    // private final Pitch_Type type;
    private String type;
    private int width;
    private String xCoords;
    private String yCoords;
}
