package de.dhbw.swe.campingplatzverwaltung.common;

public class Town {
    public Town(final int id, final Country country, final String name,
	    final String postalCode) {
	super();
	this.id = id;
	this.country = country;
	this.name = name;
	this.postalCode = postalCode;
    }

    public Country getCountry() {
	return country;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String getPostalCode() {
	return postalCode;
    }

    private final Country country;
    private final int id;

    private final String name;
    private final String postalCode;
}
