/**
 * Comments for file TownMgr.java
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
 * The manager class for the {@link Town} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class TownMgr {
    /** The singleton instance. */
    private static TownMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized TownMgr getInstance() {
	if (instance == null) {
	    instance = new TownMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private TownMgr() {
	towns = new HashMap<>();
	loadFromDatabase();
    }

    /**
     * Adds the object to the object list.
     * 
     * @param Town
     *            the {@link Town} object
     */
    public void add(final Town Town) {
	towns.put(Town.getId(), Town);
    }

    /**
     * Checks if the object already exists. <br />
     * If not, then it will be saved in the object list and the database.
     * 
     * @param town
     *            the {@link Town} object
     */
    public void checkIfObjectAlreadyExists(final Town town) {
	final String name = town.getName();
	final String postalCode = town.getPostalCode();
	final Country country = town.getCountry();
	CountryMgr.getInstance().checkIfObjectAlreadyExists(country);

	for (final Town tmpTown : towns.values()) {
	    if (name.equals(tmpTown.getName())
		    && postalCode.equals(tmpTown.getPostalCode())
		    && country.getId() == tmpTown.getCountry().getId()) {
		town.setId(tmpTown.getId());
		return;
	    }
	}
	// If Object is not existing then add to object list and database
	saveObjectInDatabase(town);
	add(town);
    }

    /**
     * Gets the object with the given id.
     * 
     * @param id
     *            the object id
     * @return the {@link Town}
     */
    public Town get(final int id) {
	if (towns.containsKey(id)) {
	    return towns.get(id);
	}
	return null;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param Town
     *            the {@link Town} object
     * @return
     */
    public boolean remove(final Town Town) {
	final int id = Town.getId();
	if (towns.containsKey(id)) {
	    towns.remove(id);
	    return true;
	}
	return false;
    }

    /**
     * Loads the objects from the database.
     */
    private void loadFromDatabase() {
	final DatabaseMgr db = DatabaseMgr.getInstance();

	for (final HashMap<String, Object> dbTown : db.getAllEntriesOf("town")) {
	    add(new Town((int) dbTown.get("id"), CountryMgr.getInstance().get(
		    (int) dbTown.get("country")), (String) dbTown.get("name"),
		    (String) dbTown.get("postalCode")));
	}
    }

    /**
     * Saves the object in database.
     * 
     * @param town
     *            the {@link Town} object
     */
    private void saveObjectInDatabase(final Town town) {
	final HashMap<String, Object> dbTown = new HashMap<>();
	dbTown.put("id", town.getId());
	dbTown.put("name", town.getName());
	dbTown.put("postalCode", town.getPostalCode());
	dbTown.put("country", town.getCountry().getId());

	final int id = DatabaseMgr.getInstance().saveEntryTo("town", dbTown);
	town.setId(id);
    }

    private final HashMap<Integer, Town> towns;
}
