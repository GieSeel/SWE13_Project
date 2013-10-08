/**
 * Comments for file VisitorsTaxClassMgr.java
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
package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link VisitorsTaxClass} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class VisitorsTaxClassMgr {
    /** The singleton instance. */
    private static VisitorsTaxClassMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized VisitorsTaxClassMgr getInstance() {
	if (instance == null) {
	    instance = new VisitorsTaxClassMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private VisitorsTaxClassMgr() {
	visitorsTaxClasss = new HashMap<>();
	tableName = "visitorsTaxClass";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link VisitorsTaxClass} object id
     * @return the {@link VisitorsTaxClass}
     */
    public VisitorsTaxClass get(final int id) {
	if (visitorsTaxClasss.containsKey(id)) {
	    return visitorsTaxClasss.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link VisitorsTaxClass} object
     */
    public void insert(final VisitorsTaxClass object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link VisitorsTaxClass} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// visitorsTaxClasss.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link VisitorsTaxClass} object id
     * @param object
     *            the {@link VisitorsTaxClass} object
     */
    public void update(final VisitorsTaxClass oldObject,
	    final VisitorsTaxClass object) {
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
     *            the list with the {@link VisitorsTaxClass} objects
     * 
     */
    private void add(final HashMap<Integer, VisitorsTaxClass> objects) {
	visitorsTaxClasss.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link VisitorsTaxClass} object id
     * @param object
     *            the {@link VisitorsTaxClass} object
     */
    private void add(final int id, final VisitorsTaxClass object) {
	object.setId(id);
	visitorsTaxClasss.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link VisitorsTaxClass} object
     */
    private HashMap<Integer, VisitorsTaxClass> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, VisitorsTaxClass> object = new HashMap<>();

	int id;
	String labeling;
	Euro price;

	id = (int) entry.get("id");
	labeling = (String) entry.get("labeling");
	price = (Euro) entry.get("price");

	object.put(id, new VisitorsTaxClass(id, labeling, price));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link VisitorsTaxClass} object
     * @param object
     *            the {@link VisitorsTaxClass} object
     */
    private void insert(int id, final VisitorsTaxClass object) {
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
     *            the {@link VisitorsTaxClass} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final VisitorsTaxClass object) {
	if (visitorsTaxClasss.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link VisitorsTaxClass} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final VisitorsTaxClass object) {
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	return GuestMgr.getInstance().isSubObjectInUse(object);
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
     *            the {@link VisitorsTaxClass} object id
     * @param object
     *            the {@link VisitorsTaxClass} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final VisitorsTaxClass object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("labeling", object.getLabeling());
	entry.put("price", object.getPrice());
	return entry;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final String tableName;
    private final HashMap<Integer, VisitorsTaxClass> visitorsTaxClasss;
}
