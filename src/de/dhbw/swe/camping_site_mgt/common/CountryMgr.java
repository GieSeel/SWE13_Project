/**
 * Comments for file CountryMgr.java
 *
 * @author   GieSeel
 *
 * Project:    Campingplatz Verwaltung
 * Company:    GieSeel
 * $Revision:  $
 *
 * Unclassified
 *
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;

/**
 * The manager class for the {@link Country} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class CountryMgr {
    /** The singleton instance. */
    private static CountryMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized CountryMgr getInstance() {
	if (instance == null) {
	    instance = new CountryMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private CountryMgr() {
	countrys = new HashMap<>();
	loadFromDatabase();
    }

    /**
     * Adds the object to the object list.
     * 
     * @param Country
     *            the {@link Country} object
     */
    public void add(final Country Country) {
	countrys.put(Country.getId(), Country);
    }

    /**
     * Checks if the object already exists. <br />
     * If not, then it will be saved in the object list and the database.
     * 
     * @param country
     *            the {@link Country} object
     */
    public void checkIfObjectAlreadyExists(final Country country) {
	final String name = country.getName();
	final String acronym = country.getAcronym();

	for (final Country tmpCountry : countrys.values()) {
	    if (name.equals(tmpCountry.getName())
		    && acronym.equals(tmpCountry.getAcronym())) {
		country.setId(tmpCountry.getId());
		return;
	    }
	}
	// If Object is not existing then add to object list and database
	saveObjectInDatabase(country);
	add(country);
    }

    /**
     * Gets the object with the given id.
     * 
     * @param id
     *            the object id
     * @return the {@link Country}
     */
    public Country get(final int id) {
	if (countrys.containsKey(id)) {
	    return countrys.get(id);
	}
	return null;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param Country
     *            the {@link Country} object
     * @return
     */
    public boolean remove(final Country Country) {
	final int id = Country.getId();
	if (countrys.containsKey(id)) {
	    countrys.remove(id);
	    return true;
	}
	return false;
    }

    /**
     * Loads the objects from the database.
     */
    private void loadFromDatabase() {
	final DatabaseMgr db = DatabaseMgr.getInstance();

	for (final HashMap<String, Object> dbCountry : db.getAllEntriesOf("country")) {
	    add(new Country((int) dbCountry.get("id"),
		    (String) dbCountry.get("acronym"),
		    (String) dbCountry.get("name")));
	}
    }

    /**
     * Saves the object in database.
     * 
     * @param country
     *            the {@link Country} object
     */
    private void saveObjectInDatabase(final Country country) {
	final HashMap<String, Object> dbCountry = new HashMap<>();
	dbCountry.put("id", country.getId());
	dbCountry.put("name", country.getName());
	dbCountry.put("acronym", country.getAcronym());

	final int id = DatabaseMgr.getInstance().saveEntryTo("country", dbCountry);
	country.setId(id);
    }

    private final HashMap<Integer, Country> countrys;
}
