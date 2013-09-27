package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.Euro;

public class VisitorsTaxClass {

    public VisitorsTaxClass() {
	super();
	this.id = 0;
	this.labeling = null;
	this.price = null;
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

    public HashMap<String, Object> getDatabaseData() {
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

    // public List<Object> getData() {
    // final List<Object> data = new ArrayList<Object>();
    // data.add(this.labeling);
    // data.add(this.price.toString());
    // return data;
    // }

    public Euro getPrice() {
	return price;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "visitorstaxclass_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "labeling", new String(this.labeling));
	objects.put(className + "price", new Float(this.price.returnValue()));
	return objects;
    }

    public VisitorsTaxClass setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public VisitorsTaxClass setTableData(final HashMap<String, Object> objects) {
	final String className = "visitorstaxclass_";
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
	this.labeling = (String) objects.get("labeling");
	this.price = new Euro((float) objects.get("price"));
    }

    private int id;
    // private final VisitorsTaxClass_Labeling labeling;
    private String labeling;
    private Euro price;
}