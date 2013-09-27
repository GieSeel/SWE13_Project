package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class ExtraBooking {
    public ExtraBooking() {
	super();
	this.id = 0;
	this.labeling = null;
	this.name = null;
	this.site = null;
    }

    public ExtraBooking(final int id, final String labeling, final String name,
	    final Site site) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.name = name;
	this.site = site;
    }

    public ExtraBooking(final String labeling, final String name, final Site site) {
	super();
	this.id = 0;
	this.labeling = labeling;
	this.name = name;
	this.site = site;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("labeling", this.labeling);
	objects.put("name", this.name);
	objects.put("site_ID",
		DatabaseController.getInstance().queryInsertUpdateSite(this.site));
	return objects;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public String getName() {
	return name;
    }

    public Site getSite() {
	return site;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "extrabooking_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "labeling", new String(this.labeling));
	objects.put(className + "name", new String(this.name));

	objects.putAll(this.site.getTableData(className));

	return objects;
    }

    public ExtraBooking setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.site = db.querySelectSite((int) objects.get("site_ID"));
	setData(objects);
	return this;
    }

    public ExtraBooking setTableData(final HashMap<String, Object> objects) {
	final String className = "extrabooking_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), siteMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("site_")) {
		siteMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.site = new Site().setTableData(siteMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.labeling = (String) objects.get("labeling");
	this.name = (String) objects.get("name");

    }

    private int id;
    private String labeling;
    private String name;
    private Site site;
}
