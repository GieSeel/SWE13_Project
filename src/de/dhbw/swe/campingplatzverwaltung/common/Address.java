package de.dhbw.swe.campingplatzverwaltung.common;

public class Address {
    public Address(final int id, final String street, final String houseNumber,
	    final Town town) {
	this.id = id;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
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

    private final String houseNumber;
    private final int id;
    private final String street;
    private final Town town;
}
