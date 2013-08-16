package de.dhbw.swe.campingplatzverwaltung.common;

import java.util.*;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country {
    public Country(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.acronym = (String) elements.get("acronym");
	this.name = (String) elements.get("name");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.name);
	data.add(this.acronym);
	return data;
    }

    public HashMap<String, Object> getHashMap() {
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

    private final String acronym;
    private final int id;
    private final String name;
}
