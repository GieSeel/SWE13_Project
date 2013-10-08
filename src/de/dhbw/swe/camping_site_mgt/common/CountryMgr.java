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
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;

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
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Country} object id
     * @return the {@link Country}
     */
    public Country get(final int id) {
	if (countries.containsKey(id)) {
	    return countries.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Country} object
     */
    public void insert(final Country object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Country} object id
     */
    @Unfinished
    public void remove(final int id) {
	// countries.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Country} object id
     * @param object
     *            the {@link Country} object
     */
    public void update(final Country oldObject, final Country object) {
	final int id = isObjectExisting(oldObject);
	if (id == 0 || isObjectInUse(oldObject)) {
	    // If object doesn't exists or if it's still in use insert a new one
	    insert(object);
	} else {
	    add(id, object);
	    db.updateEntryIn(tableName, object2entry(object));
	}
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
     * Adds object to object list.
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
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Country} object
     * @param object
     *            the {@link Country} object
     */
    private void insert(int id, final Country object) {
	// TODO evtl. unnötige -> alles in "insert(Town object)"
	if (id == 0) {
	    id = db.insertEntryInto(tableName, object2entry(object));
	}
	// Add or replace object in object list
	add(id, object);
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Country} object
     * @return id of the object (0 if it doesn't exists)
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
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	return PersonMgr.getInstance().isSubObjectInUse(object);
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

    private final HashMap<Integer, Country> countries;
    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
}