package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class ExtraBookingList {
    public ExtraBookingList() {
	super();
	this.id = 0;
	this.number = 0;
	this.site = null;
    }

    public ExtraBookingList(final int id, final int number, final Site site) {
	super();
	this.id = id;
	this.number = number;
	this.site = site;
    }

    public ExtraBookingList(final int number, final Site site) {
	super();
	this.id = 0;
	this.number = number;
	this.site = site;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put("site_ID",
		DatabaseController.getInstance().queryInsertUpdateSite(this.site));
	return objects;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Site getSite() {
	return site;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "extrabookinglist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	objects.putAll(this.site.getTableData(className));

	return objects;
    }

    public ExtraBookingList setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.site = db.querySelectSite((int) objects.get("site_ID"));
	setData(objects);
	return this;
    }

    public ExtraBookingList setTableData(final HashMap<String, Object> objects) {
	final String className = "extrabookinglist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), extrabookingMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("extrabooking_")) {
		extrabookingMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.site = new Site().setTableData(extrabookingMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private int id;
    private int number;
    private Site site;
}
