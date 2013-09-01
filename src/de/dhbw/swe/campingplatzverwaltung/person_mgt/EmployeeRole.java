package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

public class EmployeeRole {

    public EmployeeRole() {
	super();
	this.id = 0;
	this.arrangement = null;
	this.labeling = null;
    }

    public EmployeeRole(final int id, final String arrangement,
	    final String labeling) {
	super();
	this.id = id;
	this.arrangement = arrangement;
	this.labeling = labeling;
    }

    public EmployeeRole(final String arrangement, final String labeling) {
	super();
	this.id = 0;
	this.arrangement = arrangement;
	this.labeling = labeling;
    }

    public String getArrangement() {
	return arrangement;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("arrangement", this.arrangement);
	elements.put("labeling", this.labeling);
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "employeerole_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "arrangement", new String(this.arrangement));
	objects.put(className + "labeling", new String(this.labeling));
	return objects;
    }

    public EmployeeRole setDatabaseData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	setData(objects);
	return this;
    }

    public EmployeeRole setTableData(final HashMap<String, Object> objects) {
	final String className = "employeerole_";
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
	this.arrangement = (String) objects.get("arrangement");
	this.labeling = (String) objects.get("labeling");
    }

    // private final EmployeeRole_Arrangement arrangement;
    private String arrangement;
    private int id;
    // private final EmployeeRole_Labeling labeling;
    private String labeling;
}
