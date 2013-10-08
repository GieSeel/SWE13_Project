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

import java.util.*;

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
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Person} object id
     * @return the {@link Person}
     */
    public Person get(final int id) {
	if (persons.containsKey(id)) {
	    return persons.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Person} object
     */
    public void insert(final Person object) {
	// Sub objects
	CountryMgr.getInstance().insert(object.getCountry());
	TownMgr.getInstance().insert(object.getTown());

	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Checks if the object is used by any {@link Person} object.
     * 
     * @param object
     *            the {@link Country} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Country object) {
	final List<Country> countries = new Vector<>();

	for (final Person person : persons.values()) {
	    countries.add(person.getCountry());
	}
	return countries.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Person} object.
     * 
     * @param country
     *            the {@link Town} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Town object) {
	final List<Town> towns = new Vector<>();

	for (final Person person : persons.values()) {
	    towns.add(person.getTown());
	}
	return towns.contains(object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Person} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// persons.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Person} object id
     * @param object
     *            the {@link Person} object
     */
    public void update(final Person oldObject, final Person object) {
	// Sub objects
	CountryMgr.getInstance().update(oldObject.getCountry(), object.getCountry());
	TownMgr.getInstance().update(oldObject.getTown(), object.getTown());

	final int id = isObjectExisting(oldObject);
	if (id == 0) {// || isObjectInUse(oldObject)) { // Person immer update
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
     *            the list with the {@link Person} objects
     * 
     */
    private void add(final HashMap<Integer, Person> objects) {
	persons.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link Person} object id
     * @param object
     *            the {@link Person} object
     */
    private void add(final int id, final Person object) {
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
	country = CountryMgr.getInstance().get((int) entry.get("country"));
	dateOfBirth = (Date) entry.get("dateOfBirth");
	firstName = (String) entry.get("firstName");
	houseNumber = (String) entry.get("houseNumber");
	identificationNumber = (String) entry.get("identificationNumber");
	name = (String) entry.get("name");
	street = (String) entry.get("street");
	town = TownMgr.getInstance().get((int) entry.get("town"));

	object.put(id, new Person(id, country, dateOfBirth, firstName, houseNumber,
		identificationNumber, name, street, town));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Person} object
     * @param object
     *            the {@link Person} object
     */
    private void insert(int id, final Person object) {
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
     *            the {@link Person} object
     * @return id of the object (0 if it doesn't exists)
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
    @Unfinished
    private boolean isObjectInUse(final Person object) {
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	return false;
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

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Person> persons;
    private final String tableName;
}
