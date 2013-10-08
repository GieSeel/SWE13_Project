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
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeMgr;

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
	chipCard = new HashMap<>();
	tableName = "visitorsTaxClass";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @return the {@link ChipCard}
     */
    public ChipCard get(final int id) {
	if (chipCard.containsKey(id)) {
	    return chipCard.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link ChipCard} object
     */
    public void insert(final ChipCard object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// chipCard.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @param object
     *            the {@link ChipCard} object
     */
    public void update(final ChipCard oldObject, final ChipCard object) {
	final int id = isObjectExisting(oldObject);
	if (id == 0) { // || isObjectInUse(oldObject)) { // ChipCard immer
		       // update
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
     *            the list with the {@link ChipCard} objects
     * 
     */
    private void add(final HashMap<Integer, ChipCard> objects) {
	chipCard.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link ChipCard} object id
     * @param object
     *            the {@link ChipCard} object
     */
    private void add(final int id, final ChipCard object) {
	object.setId(id);
	chipCard.put(id, object);
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
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link ChipCard} object
     * @param object
     *            the {@link ChipCard} object
     */
    private void insert(int id, final ChipCard object) {
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
     *            the {@link ChipCard} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final ChipCard object) {
	if (chipCard.containsValue(object)) {
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
    @Unfinished
    private boolean isObjectInUse(final ChipCard object) {
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	return EmployeeMgr.getInstance().isSubObjectInUse(object);
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

    private final HashMap<Integer, ChipCard> chipCard;
    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
}
