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
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

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
	countries = new HashMap<>();
	tableName = "country";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link Country} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Country} object
     */
    public Country objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Country object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Country} object
     */
    public void objectInsert(final Country object) {
	// Sub objects

	// If object already exists just save that id
	int id = isObjectExisting(object);
	if (id == 0) {
	    id = db.insertEntryInto(tableName, object2entry(object));
	}

	// Add or replace object in object list
	add(id, object);
    }

    /**
     * 
     * Deletes the object.
     * 
     * @param object
     *            the {@link Country} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Country object) {
	return false;
	// // Sub objects
	// final int id = object.getId();
	//
	// if (isObjectInUse(object)) {
	// logger.error("Object is already in use!");
	// return false;
	// }
	// db.removeEntryFrom(tableName, id);
	// return remove(id);
    }

    /**
     * Updates the object.
     * 
     * @param object
     *            the old {@link Country} object
     * @param newObject
     *            the new {@link Country} object
     */
    public void objectUpdate(final Country object, final Country newObject) {
	// Sub objects
	int id = object.getId();

	id = isObjectExisting(object);
	if (id == 0 || isObjectInUse(object)) {
	    // If object is in use or it doesn't exists a new one is needed
	    objectInsert(newObject);
	    return;
	}
	// TODO if newObject already exists the old object should be removed and
	// the existing object should be used!

	// Update object in object list and database
	add(id, newObject);
	db.updateEntryIn(tableName, object2entry(newObject));
    }

    /**
     * Adds objects to object list.
     * 
     * @param objects
     *            the list with the {@link Country} objects
     * 
     */
    private void add(final HashMap<Integer, Country> objects) {
	countries.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link Country} object id
     * @param object
     *            the {@link Country} object
     */
    private void add(final int id, final Country object) {
	object.setId(id);
	countries.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Country} object
     */
    private HashMap<Integer, Country> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, Country> object = new HashMap<>();
	int id;
	String name;
	String acronym;

	id = (int) entry.get("id");
	acronym = (String) entry.get("acronym");
	name = (String) entry.get("name");

	object.put(id, new Country(id, acronym, name));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Country} object id
     * @return the {@link Country} object
     */
    private Country get(final int id) {
	if (countries.containsKey(id)) {
	    final Country object = countries.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Country} object
     * @return the id of the {@link Country} object
     */
    private int isObjectExisting(final Country object) {
	if (countries.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Country} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Country object) {
	return object.isInUse();
    }

    /**
     * Loads the objects from the database.
     */
    private void load() {
	for (final HashMap<String, Object> entry : db.getAllEntriesOf(tableName)) {
	    add(entry2object(entry));
	}
    }

    /**
     * Parses an object to a database entry.
     * 
     * @param id
     *            the {@link Country} object id
     * @param object
     *            the {@link Country} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Country object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("acronym", object.getAcronym());
	entry.put("name", object.getName());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Country} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (countries.containsKey(id)) {
	    countries.remove(id);
	    return true;
	}
	return false;
    }

    private final HashMap<Integer, Country> countries;
    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
}