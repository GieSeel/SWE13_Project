package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

public class Equipment {

    public Equipment() {
	super();
	this.id = 0;
	this.identification = null;
	this.size = null;
	this.type = null;
    }

    public Equipment(final int id, final String identification, final String size,
	    final String type) {
	super();
	this.id = id;
	this.identification = identification;
	this.size = size;
	this.type = type;
    }

    public Equipment(final String identification, final String size,
	    final String type) {
	super();
	this.id = 0;
	this.identification = identification;
	this.size = size;
	this.type = type;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("identification", this.identification);
	objects.put("size", this.size);
	objects.put("type", this.type);
	return objects;
    }

    public int getId() {
	return id;
    }

    public String getIdentification() {
	return identification;
    }

    public String getSize() {
	return size;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "equipment_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "identification", new String(this.identification));
	objects.put(className + "size", new String(this.size));
	objects.put(className + "type", new String(this.type));

	return objects;
    }

    public String getType() {
	return type;
    }

    public Equipment setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public Equipment setTableData(final HashMap<String, Object> objects) {
	final String className = "equipment_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    thisMap.put(key, val);
	}
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.identification = (String) objects.get("identification");
	this.size = (String) objects.get("size");
	this.type = (String) objects.get("type");

    }

    private int id;
    private String identification;
    private String size;
    private String type;
    // private final Equipment_Type type;
}
