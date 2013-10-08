/**
 * Comments for file PersonMgr.java
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

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Person} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PersonMgr {
    /** The singleton instance. */
    private static PersonMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PersonMgr getInstance() {
	if (instance == null) {
	    instance = new PersonMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PersonMgr() {
	persons = new HashMap<>();
	tableName = "person";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link Person} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Person} object
     */
    public Person objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Person object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Person} object
     */
    public void objectInsert(final Person object) {
	// Sub objects
	CountryMgr.getInstance().objectInsert(object.getCountry());
	TownMgr.getInstance().objectInsert(object.getTown());

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
     *            the {@link Person} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Person object) {
	// Sub objects
	final int id = object.getId();
	object.getCountry().delUsage(tableName, id);
	object.getTown().delUsage(tableName, id);

	CountryMgr.getInstance().objectRemove(object.getCountry());
	TownMgr.getInstance().objectRemove(object.getTown());

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
     *            the old {@link Person} object
     * @param newObject
     *            the new {@link Person} object
     */
    public void objectUpdate(final Person object, final Person newObject) {
	// Sub objects
	int id = object.getId();

	object.getCountry().delUsage(tableName, id);
	object.getTown().delUsage(tableName, id);

	CountryMgr.getInstance().objectUpdate(object.getCountry(),
		newObject.getCountry());
	TownMgr.getInstance().objectUpdate(object.getTown(), newObject.getTown());

	// Update object
	id = isObjectExisting(object);
	if (id == 0) { // TODO || isObjectInUse(object)) {
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
     *            the list with the {@link Person} objects
     * 
     */
    private void add(final HashMap<Integer, Person> objects) {
	persons.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link Person} object id
     * @param object
     *            the {@link Person} object
     */
    private void add(final int id, final Person object) {
	object.getCountry().addUsage(tableName, id);
	object.getTown().addUsage(tableName, id);
	object.setId(id);
	persons.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Person} object
     */
    private HashMap<Integer, Person> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, Person> object = new HashMap<>();
	int id;
	Country country;
	Date dateOfBirth;
	String firstName;
	String houseNumber;
	String identificationNumber;
	String name;
	String street;
	Town town;

	id = (int) entry.get("id");
	country = CountryMgr.getInstance().objectGet((int) entry.get("country"),
		tableName, id);
	dateOfBirth = (Date) entry.get("dateOfBirth");
	firstName = (String) entry.get("firstName");
	houseNumber = (String) entry.get("houseNumber");
	identificationNumber = (String) entry.get("identificationNumber");
	name = (String) entry.get("name");
	street = (String) entry.get("street");
	town = TownMgr.getInstance().objectGet((int) entry.get("town"), tableName,
		id);

	object.put(id, new Person(id, country, dateOfBirth, firstName, houseNumber,
		identificationNumber, name, street, town));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Person} object id
     * @return the {@link Person} object
     */
    private Person get(final int id) {
	if (persons.containsKey(id)) {
	    final Person object = persons.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Person} object
     * @return the id of the {@link Person} object
     */
    private int isObjectExisting(final Person object) {
	if (persons.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Person} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Person object) {
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
     *            the {@link Person} object id
     * @param object
     *            the {@link Person} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Person object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("country", object.getCountry().getId());
	entry.put("dateOfBirth", object.getDateOfBirth());
	entry.put("firstName", object.getFirstName());
	entry.put("houseNumber", object.getHouseNumber());
	entry.put("identificationNumber", object.getIdentificationNumber());
	entry.put("name", object.getName());
	entry.put("street", object.getStreet());
	entry.put("town", object.getTown().getId());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Person} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (persons.containsKey(id)) {
	    persons.remove(id);
	    return true;
	}
	return false;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Person> persons;
    private final String tableName;
}