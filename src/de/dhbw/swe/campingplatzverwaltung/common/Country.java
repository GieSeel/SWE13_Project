package de.dhbw.swe.campingplatzverwaltung.common;

import java.util.*;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country {

    public Country() {
	super();
	this.id = 0;
	this.acronym = null;
	this.name = null;
    }

    public Country(final int id, final String acronym, final String name) {
	super();
	this.id = id;
	this.acronym = acronym;
	this.name = name;
    }

    public Country(final String acronym, final String name) {
	super();
	this.id = 0;
	this.acronym = acronym;
	this.name = name;
    }

    public String getAcronym() {
	return acronym;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("acronym", this.acronym);
	elements.put("name", this.name);
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "country_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "name", new String(this.name));
	objects.put(className + "acronym", new String(this.acronym));
	return objects;
    }

    public Country setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public Country setTableData(final HashMap<String, Object> objects) {
	final String className = "country_";
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
	this.acronym = (String) objects.get("acronym");
	this.name = (String) objects.get("name");
    }

    private String acronym;
    private int id;
    private String name;
}
