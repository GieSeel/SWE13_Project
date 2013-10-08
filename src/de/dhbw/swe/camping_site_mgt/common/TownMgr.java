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
     * Gets the object.
     * 
     * @param id
     *            the {@link Town} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Town} object
     */
    public Town objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Town object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Town} object
     */
    public void objectInsert(final Town object) {
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
     *            the {@link Town} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Town object) {
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
     *            the old {@link Town} object
     * @param newObject
     *            the new {@link Town} object
     */
    public void objectUpdate(final Town object, final Town newObject) {
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
     *            the list with the {@link Town} objects
     * 
     */
    private void add(final HashMap<Integer, Town> objects) {
	towns.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
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
	postalCode = (String) entry.get("postalCode");
	name = (String) entry.get("name");

	object.put(id, new Town(id, name, postalCode));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Town} object id
     * @return the {@link Town} object
     */
    private Town get(final int id) {
	if (towns.containsKey(id)) {
	    final Town object = towns.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Town} object
     * @return the id of the {@link Town} object
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

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Town} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (towns.containsKey(id)) {
	    towns.remove(id);
	    return true;
	}
	return false;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
    private final HashMap<Integer, Town> towns;
}