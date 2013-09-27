package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.Euro;

public class BillItem {
    public BillItem() {
	super();
	this.id = 0;
	this.labeling = null;
	this.priceBusySeason = null;
	this.priceLowSeason = null;
    }

    public BillItem(final int id, final String labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
    }

    public BillItem(final String labeling, final Euro priceBusySeason,
	    final Euro priceLowSeason) {
	super();
	this.id = 0;
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("labeling", this.labeling);
	objects.put("priceBusySeason", this.priceBusySeason.returnValue());
	objects.put("priceLowSeason", this.priceLowSeason.returnValue());
	return objects;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public Euro getPriceBusySeason() {
	return priceBusySeason;
    }

    public Euro getPriceLowSeason() {
	return priceLowSeason;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "billitem_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "labeling", new String(this.labeling));
	objects.put(className + "priceBusySeason",
		new Float(this.priceBusySeason.returnValue()));
	objects.put(className + "priceLowSeason",
		new Float(this.priceLowSeason.returnValue()));

	return objects;
    }

    public BillItem setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public BillItem setTableData(final HashMap<String, Object> objects) {
	final String className = "billitem_";
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
	this.priceBusySeason = new Euro((float) objects.get("priceBusySeason"));
	this.priceLowSeason = new Euro((float) objects.get("priceLowSeason"));
    }

    private int id;
    // private final BillItem_Labeling labeling;
    private String labeling;
    private Euro priceBusySeason;
    private Euro priceLowSeason;
}
