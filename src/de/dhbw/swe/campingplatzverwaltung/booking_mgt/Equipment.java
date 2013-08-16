package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

public class Equipment {

    public Equipment(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.identification = (String) elements.get("identification");
	this.size = (String) elements.get("size");
	this.type = (String) elements.get("type");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.identification);
	data.add(this.size);
	data.add(this.type);
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("identification", this.identification);
	elements.put("size", this.size);
	elements.put("type", this.type);
	return elements;
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

    public String getType() {
	return type;
    }

    private final int id;
    private final String identification;
    private final String size;
    private final String type;
    // private final Equipment_Type type;
}
