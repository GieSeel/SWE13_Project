package de.dhbw.swe.campingplatzverwaltung.common;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country {
    public Country(final int id, final String acronym, final String name) {
	super();
	this.id = id;
	this.acronym = acronym;
	this.name = name;
    }

    public String getAcronym() {
	return acronym;
    }

    public int getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    private final String acronym;
    private final int id;
    private final String name;
}
