package de.dhbw.swe.camping_site_mgt.common;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Address {
    public Address() {
	super();
	this.id = 0;
	this.street = null;
	this.houseNumber = null;
	this.town = null;
    }

    public Address(final int id, final String street, final String houseNumber,
	    final Town town) {
	super();
	this.id = id;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
    }

    public Address(final String street, final String houseNumber, final Town town) {
	super();
	this.id = 0;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("street", this.street);
	elements.put("houseNumber", this.houseNumber);
	elements.put("town_ID",
		DatabaseController.getInstance().queryInsertUpdateTown(this.town));
	return elements;
    }

    public String getHouseNumber() {
	return houseNumber;
    }

    public int getId() {
	return id;
    }

    public String getStreet() {
	return street;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "address_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "street", new String(this.street));
	objects.put(className + "houseNumber", new String(this.houseNumber));
	objects.putAll(this.town.getTableData(className));
	return objects;
    }

    public Town getTown() {
	return town;
    }

    public Address setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.town = db.querySelectTown((int) objects.get("town_ID"));
	setData(objects);
	return this;
    }

    public void setId(final int id) {
	this.id = id;
    }

    public Address setTableData(final HashMap<String, Object> objects) {
	final String className = "address_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), townMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("town_")) {
		townMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.town = new Town().setTableData(townMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.street = (String) objects.get("street");
	this.houseNumber = (String) objects.get("houseNumber");
    }

    private String houseNumber;
    private int id;
    private String street;
    private Town town;
}
