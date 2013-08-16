package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import java.util.*;

public class Site {
    public Site(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.description = (String) elements.get("description");
	this.labeling = (String) elements.get("labeling");
	this.openingHours = (String) elements.get("openingHours");
	this.type = (String) elements.get("type");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.description);
	data.add(this.labeling);
	data.add(this.openingHours);
	data.add(this.type);
	return data;
    }

    public String getDescription() {
	return description;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("description", this.description);
	elements.put("labeling", this.labeling);
	elements.put("openingHours", this.openingHours);
	elements.put("type", this.type);
	return elements;
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

    public String getType() {
	return type;
    }

    private final String description;
    private final int id;
    private final String labeling;
    private final String openingHours;
    // private final Site_Type type;
    private final String type;
}
