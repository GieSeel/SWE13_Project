/**
 * Comments for file EmployeeRoleMgr.java
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

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.service_mgt.ServiceMgr;

/**
 * The manager class for the {@link EmployeeRole} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeRoleMgr {
    /** The singleton instance. */
    private static EmployeeRoleMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EmployeeRoleMgr getInstance() {
	if (instance == null) {
	    instance = new EmployeeRoleMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EmployeeRoleMgr() {
	employeeRoles = new HashMap<>();
	tableName = "employeeRole";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link EmployeeRole} object id
     * @return the {@link EmployeeRole}
     */
    public EmployeeRole get(final int id) {
	if (employeeRoles.containsKey(id)) {
	    return employeeRoles.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link EmployeeRole} object
     */
    public void insert(final EmployeeRole object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link EmployeeRole} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// employeeRoles.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link EmployeeRole} object id
     * @param object
     *            the {@link EmployeeRole} object
     */
    public void update(final EmployeeRole oldObject, final EmployeeRole object) {
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
     *            the list with the {@link EmployeeRole} objects
     * 
     */
    private void add(final HashMap<Integer, EmployeeRole> objects) {
	employeeRoles.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link EmployeeRole} object id
     * @param object
     *            the {@link EmployeeRole} object
     */
    private void add(final int id, final EmployeeRole object) {
	object.setId(id);
	employeeRoles.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link EmployeeRole} object
     */
    private HashMap<Integer, EmployeeRole> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, EmployeeRole> object = new HashMap<>();

	int id;
	String arrangement;
	String labeling;

	id = (int) entry.get("id");
	arrangement = (String) entry.get("arrangement");
	labeling = (String) entry.get("labeling");

	object.put(id, new EmployeeRole(id, arrangement, labeling));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link EmployeeRole} object
     * @param object
     *            the {@link EmployeeRole} object
     */
    private void insert(int id, final EmployeeRole object) {
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
     *            the {@link EmployeeRole} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final EmployeeRole object) {
	if (employeeRoles.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link EmployeeRole} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final EmployeeRole object) {
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	if (EmployeeMgr.getInstance().isSubObjectInUse(object)) {
	    return true;
	}
	return ServiceMgr.getInstance().isSubObjectInUse(object);
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
     *            the {@link EmployeeRole} object id
     * @param object
     *            the {@link EmployeeRole} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final EmployeeRole object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("arrangement", object.getArrangement());
	entry.put("labeling", object.getLabeling());
	return entry;
    }

    private final DatabaseMgr db;
    private final HashMap<Integer, EmployeeRole> employeeRoles;
    private final CampingLogger logger;
    private final String tableName;
}
