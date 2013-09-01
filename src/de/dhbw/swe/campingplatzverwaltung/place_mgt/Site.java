package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import java.util.*;

public class Site {
    public Site() {
	super();
	this.id = 0;
	this.description = null;
	this.labeling = null;
	this.openingHours = null;
	this.type = null;
    }

    public Site(final int id, final String description, final String labeling,
	    final String openingHours, final String type) {
	super();
	this.id = id;
	this.description = description;
	this.labeling = labeling;
	this.openingHours = openingHours;
	this.type = type;
    }

    public Site(final String description, final String labeling,
	    final String openingHours, final String type) {
	super();
	this.id = 0;
	this.description = description;
	this.labeling = labeling;
	this.openingHours = openingHours;
	this.type = type;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("description", this.description);
	objects.put("labeling", this.labeling);
	objects.put("openingHours", this.openingHours);
	objects.put("type", this.type);
	return objects;
    }

    public String getDescription() {
	return description;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public String getOpeningHours() {
	return openingHours;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "site_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "description", new String(this.description));
	objects.put(className + "labeling", new String(this.labeling));
	objects.put(className + "openingHours", new String(this.openingHours));
	objects.put(className + "type", new String(this.type));

	return objects;
    }

    public String getType() {
	return type;
    }

    public Site setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public Site setTableData(final HashMap<String, Object> objects) {
	final String className = "site_";
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
	this.description = (String) objects.get("description");
	this.labeling = (String) objects.get("labeling");
	this.openingHours = (String) objects.get("openingHours");
	this.type = (String) objects.get("type");
    }

    private String description;
    private int id;
    private String labeling;
    private String openingHours;
    // private final Site_Type type;
    private String type;
}
