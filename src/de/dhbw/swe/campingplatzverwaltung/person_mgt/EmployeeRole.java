package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

public class EmployeeRole {

    public EmployeeRole(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.arrangement = (String) elements.get("arrangement");
	this.labeling = (String) elements.get("labeling");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.arrangement);
	data.add(this.labeling);
	return data;
    }

    public HashMap<String, Object> getHashMap() {
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

    // private final EmployeeRole_Arrangement arrangement;
    private final String arrangement;
    private final int id;
    // private final EmployeeRole_Labeling labeling;
    private final String labeling;
}
