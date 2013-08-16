package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.Euro;

public class VisitorsTaxClass {

    public VisitorsTaxClass(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.labeling = (String) elements.get("labeling");
	this.price = new Euro((float) elements.get("price"));
    }

    public VisitorsTaxClass(final int id, final String labeling, final Euro price) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.price = price;
    }

    public VisitorsTaxClass(final String labeling, final String price) {
	super();
	this.id = 0;
	this.labeling = labeling;
	this.price = new Euro(price);
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.labeling);
	data.add(this.price.toString());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("labeling", this.labeling);
	elements.put("price", this.price.returnValue());
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public Euro getPrice() {
	return price;
    }

    private final int id;
    // private final VisitorsTaxClass_Labeling labeling;
    private final String labeling;
    private final Euro price;
}
