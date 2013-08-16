package de.dhbw.swe.campingplatzverwaltung.common;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Town {
    public Town(final Country country, final String name, final String postalCode) {
	super();
	this.id = 0;
	this.country = country;
	this.name = name;
	this.postalCode = postalCode;
    }

    public Town(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.country = DatabaseController.getInstance().querySelectCountry(
		(int) elements.get("country_ID"));
	this.name = (String) elements.get("name");
	this.postalCode = (String) elements.get("postalCode");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.postalCode);
	data.add(this.name);
	data.addAll(this.country.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
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

    private final Country country;
    private final int id;
    private final String name;
    private final String postalCode;
}
