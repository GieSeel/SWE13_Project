package de.dhbw.swe.campingplatzverwaltung.service_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class ServiceList {
    public ServiceList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.service = DatabaseController.getInstance().querySelectService(
		(int) elements.get("service_ID"));
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.service.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put(
		"service_ID",
		DatabaseController.getInstance().queryInsertUpdateService(
			this.service));
	return elements;
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

    private final int id;
    private final int number;
    private final Service service;
}
