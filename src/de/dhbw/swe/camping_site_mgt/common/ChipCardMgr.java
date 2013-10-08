/**
 * Comments for file ChipCardMgr.java
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

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link ChipCard} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ChipCardMgr {
    /** The singleton instance. */
    private static ChipCardMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized ChipCardMgr getInstance() {
	if (instance == null) {
	    instance = new ChipCardMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private ChipCardMgr() {
	chipCards = new HashMap<>();
	tableName = "chipCard";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link ChipCard} object
     */
    public ChipCard objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final ChipCard object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link ChipCard} object
     */
    public void objectInsert(final ChipCard object) {
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
     *            the {@link ChipCard} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final ChipCard object) {
	// Sub objects
	final int id = object.getId();

	if (isObjectInUse(object)) {
	    logger.error("Object is already in use!");
	    return false;
	}
	db.removeEntryFrom(tableName, object2entry(object));
	return remove(id);
    }

    /**
     * Updates the object.
     * 
     * @param object
     *            the old {@link ChipCard} object
     * @param newObject
     *            the new {@link ChipCard} object
     */
    public void objectUpdate(final ChipCard object, final ChipCard newObject) {
	// Sub objects
	int id = object.getId();

	id = isObjectExisting(object);
	if (id == 0 || isObjectInUse(object)) {
	    // If object is in use or it doesn't exists a new one is needed
	    objectInsert(newObject);
	    return;
	}
	// If newObject already exists the old object will be removed and
	// the existing object will be used!
	final int newID = isObjectExisting(newObject);
	if (newID != 0) {
	    objectRemove(object);
	    add(newID, newObject);
	} else {
	    // Update object in object list and database
	    add(id, newObject);
	    db.updateEntryIn(tableName, object2entry(newObject));
	}
    }

    /**
     * Adds objects to object list.
     * 
     * @param objects
     *            the list with the {@link ChipCard} objects
     * 
     */
    private void add(final HashMap<Integer, ChipCard> objects) {
	chipCards.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @param object
     *            the {@link ChipCard} object
     */
    private void add(final int id, final ChipCard object) {
	object.setId(id);
	chipCards.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link ChipCard} object
     */
    private HashMap<Integer, ChipCard> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, ChipCard> object = new HashMap<>();
	int id;
	Date validFrom;
	Date validTo;

	id = (int) entry.get("id");
	validFrom = (Date) entry.get("validFrom");
	validTo = (Date) entry.get("validTo");

	object.put(id, new ChipCard(id, validFrom, validTo));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @return the {@link ChipCard} object
     */
    private ChipCard get(final int id) {
	if (chipCards.containsKey(id)) {
	    final ChipCard object = chipCards.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link ChipCard} object
     * @return the id of the {@link ChipCard} object
     */
    private int isObjectExisting(final ChipCard object) {
	if (chipCards.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link ChipCard} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final ChipCard object) {
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
     *            the {@link ChipCard} object id
     * @param object
     *            the {@link ChipCard} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final ChipCard object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("validFrom", object.getValidFrom());
	entry.put("validTo", object.getValidTo());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (chipCards.containsKey(id)) {
	    chipCards.remove(id);
	    return true;
	}
	return false;
    }

    private final HashMap<Integer, ChipCard> chipCards;
    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
}