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
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;

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
	tableName = "town";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Town} object id
     * @return the {@link Town}
     */
    public Town get(final int id) {
	if (towns.containsKey(id)) {
	    return towns.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Town} object
     */
    public void insert(final Town object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Town} object id
     */
    @Unfinished
    public void remove(final int id) {
	// towns.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Town} object id
     * @param object
     *            the {@link Town} object
     */
    public void update(final Town oldObject, final Town object) {
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
     *            the list with the {@link Town} objects
     * 
     */
    private void add(final HashMap<Integer, Town> objects) {
	towns.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link Town} object id
     * @param object
     *            the {@link Town} object
     */
    private void add(final int id, final Town object) {
	object.setId(id);
	towns.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Town} object
     */
    private HashMap<Integer, Town> entry2object(final HashMap<String, Object> entry) {
	final HashMap<Integer, Town> object = new HashMap<>();
	int id;
	String name;
	String postalCode;

	id = (int) entry.get("id");
	name = (String) entry.get("name");
	postalCode = (String) entry.get("postalCode");

	object.put(id, new Town(id, name, postalCode));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Town} object
     * @param object
     *            the {@link Town} object
     */
    private void insert(int id, final Town object) {
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
     *            the {@link Town} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final Town object) {
	if (towns.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Town} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Town object) {
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
     *            the {@link Town} object id
     * @param object
     *            the {@link Town} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Town object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("name", object.getName());
	entry.put("postalCode", object.getPostalCode());
	return entry;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
    private final HashMap<Integer, Town> towns;
}