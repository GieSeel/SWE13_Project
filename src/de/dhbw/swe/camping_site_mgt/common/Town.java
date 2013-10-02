package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Town {
    public Town() {
	super();
	this.id = 0;
	this.country = null;
	this.name = null;
	this.postalCode = null;
    }

    public Town(final Country country, final String name, final String postalCode) {
	super();
	this.id = 0;
	this.country = country;
	this.name = name;
	this.postalCode = postalCode;
    }

    public Town(final int id, final Country country, final String name,
	    final String postalCode) {
	super();
	this.id = id;
	this.country = country;
	this.name = name;
	this.postalCode = postalCode;
    }

    public Country getCountry() {
	return country;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put(
		"country_ID",
		DatabaseController.getInstance().queryInsertUpdateCountry(
			this.country));
	elements.put("name", this.name);
	elements.put("postalCode", this.postalCode);
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "town_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "postalCode", new String(this.postalCode));
	objects.put(className + "name", new String(this.name));
	objects.putAll(this.country.getTableData(className));
	return objects;
    }

    public Town setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.country = db.querySelectCountry((int) objects.get("country_ID"));
	setData(objects);
	return this;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    public Town setTableData(final HashMap<String, Object> objects) {
	final String className = "town_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), countryMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("country_")) {
		countryMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.country = new Country().setTableData(countryMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.name = (String) objects.get("name");
	this.postalCode = (String) objects.get("postalCode");

    }

    private Country country;
    private int id;
    private String name;
    private String postalCode;
}
