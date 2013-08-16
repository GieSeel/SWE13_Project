package de.dhbw.swe.campingplatzverwaltung.common;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Address {
    public Address(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.street = (String) elements.get("street");
	this.houseNumber = (String) elements.get("houseNumber");
	this.town = DatabaseController.getInstance().querySelectTown(
		(int) elements.get("town_ID"));
    }

    public Address(final int id, final String street, final String houseNumber,
	    final Town town) {
	super();
	this.id = id;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
    }

    public Address(final String street, final String houseNumber, final Town town) {
	super();
	this.id = 0;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.street);
	data.add(this.houseNumber);
	data.addAll(this.town.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("street", this.street);
	elements.put("houseNumber", this.houseNumber);
	elements.put("town_ID",
		DatabaseController.getInstance().queryInsertUpdateTown(this.town));
	return elements;
    }

    public String getHouseNumber() {
	return houseNumber;
    }

    public int getId() {
	return id;
    }

    public String getStreet() {
	return street;
    }

    public Town getTown() {
	return town;
    }

    public void setId(final int id) {
	this.id = id;
    }

    private final String houseNumber;
    private int id;
    private final String street;
    private final Town town;
}
