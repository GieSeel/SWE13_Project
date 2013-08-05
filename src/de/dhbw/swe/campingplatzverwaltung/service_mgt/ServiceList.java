package de.dhbw.swe.campingplatzverwaltung.service_mgt;

public class ServiceList {
    public ServiceList(final int id, final int number, final Service service) {
	super();
	this.id = id;
	this.number = number;
	this.service = service;
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
