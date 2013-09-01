package de.dhbw.swe.campingplatzverwaltung.service_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class ServiceList {
    public ServiceList() {
	super();
	this.id = 0;
	this.number = 0;
	this.service = null;
    }

    public ServiceList(final int id, final int number, final Service service) {
	super();
	this.id = id;
	this.number = number;
	this.service = service;
    }

    public ServiceList(final int number, final Service service) {
	super();
	this.id = 0;
	this.number = number;
	this.service = service;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put(
		"service_ID",
		DatabaseController.getInstance().queryInsertUpdateService(
			this.service));
	return objects;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Service getService() {
	return service;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "servicelist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	objects.putAll(this.service.getTableData(className));

	return objects;
    }

    public ServiceList setDatabaseData(final HashMap<String, Object> objects) {
	this.service = DatabaseController.getInstance().querySelectService(
		(int) objects.get("service_ID"));
	setData(objects);
	return this;
    }

    public ServiceList setTableData(final HashMap<String, Object> objects) {
	final String className = "servicelist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), serviceMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("service_")) {
		serviceMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.service = new Service().setTableData(serviceMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private int id;
    private int number;
    private Service service;
}
