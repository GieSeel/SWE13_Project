package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Employee} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeMgr {
    /** The singleton instance. */
    private static EmployeeMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EmployeeMgr getInstance() {
	if (instance == null) {
	    instance = new EmployeeMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EmployeeMgr() {
	employees = new HashMap<>();
	tableName = "employee";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Employee} object id
     * @return the {@link Employee}
     */
    public Employee get(final int id) {
	if (employees.containsKey(id)) {
	    return employees.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Employee} object
     */
    public void insert(final Employee object) {
	// Sub objects
	ChipCardMgr.getInstance().insert(object.getChipCard());
	PersonMgr.getInstance().insert(object.getPerson());
	EmployeeRoleMgr.getInstance().insert(object.getRole());

	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Checks if the object is used by any {@link Employee} object.
     * 
     * @param object
     *            the {@link ChipCard} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final ChipCard object) {
	final List<ChipCard> chipCards = new Vector<>();

	for (final Employee employee : employees.values()) {
	    chipCards.add(employee.getChipCard());
	}
	return chipCards.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Employee} object.
     * 
     * @param country
     *            the {@link EmployeeRole} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final EmployeeRole object) {
	final List<EmployeeRole> employeeRoles = new Vector<>();

	for (final Employee employee : employees.values()) {
	    employeeRoles.add(employee.getRole());
	}
	return employeeRoles.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Employee} object.
     * 
     * @param country
     *            the {@link Person} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Person object) {
	final List<Person> persons = new Vector<>();

	for (final Employee employee : employees.values()) {
	    persons.add(employee.getPerson());
	}
	return persons.contains(object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Employee} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// employees.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Employee} object id
     * @param object
     *            the {@link Employee} object
     */
    public void update(final Employee oldObject, final Employee object) {
	// Sub objects
	PersonMgr.getInstance().update(oldObject.getPerson(), object.getPerson());
	ChipCardMgr.getInstance().update(oldObject.getChipCard(),
		object.getChipCard());
	EmployeeRoleMgr.getInstance().update(oldObject.getRole(), object.getRole());

	final int id = isObjectExisting(oldObject);
	if (id == 0) {// || isObjectInUse(oldObject)) { // Employee immer update
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
     *            the list with the {@link Employee} objects
     * 
     */
    private void add(final HashMap<Integer, Employee> objects) {
	employees.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link Employee} object id
     * @param object
     *            the {@link Employee} object
     */
    private void add(final int id, final Employee object) {
	object.setId(id);
	employees.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Employee} object
     */
    private HashMap<Integer, Employee> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, Employee> object = new HashMap<>();

	int id;
	boolean blocked;
	ChipCard chipCard;
	String password;
	Person person;
	EmployeeRole role;
	String userName;

	id = (int) entry.get("id");
	blocked = ((int) entry.get("blocked") == 1 ? true : false);
	chipCard = ChipCardMgr.getInstance().get((int) entry.get("chipCard"));
	password = (String) entry.get("password");
	person = PersonMgr.getInstance().get((int) entry.get("person"));
	role = EmployeeRoleMgr.getInstance().get((int) entry.get("role"));
	userName = (String) entry.get("userName");

	object.put(id, new Employee(id, blocked, chipCard, password, person, role,
		userName));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Employee} object
     * @param object
     *            the {@link Employee} object
     */
    private void insert(int id, final Employee object) {
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
     *            the {@link Employee} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final Employee object) {
	if (employees.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Employee} object
     * @return true if object is still in use
     */
    @Unfinished
    private boolean isObjectInUse(final Employee object) {
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
     *            the {@link Employee} object id
     * @param object
     *            the {@link Employee} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Employee object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("blocked", (object.isBlocked() ? 1 : 0));
	entry.put("blocked", object.getChipCard().getId());
	entry.put("password", object.getPassword());
	entry.put("person", object.getPerson().getId());
	entry.put("role", object.getRole().getId());
	entry.put("userName", object.getUserName());
	return entry;
    }

    private final DatabaseMgr db;
    private final HashMap<Integer, Employee> employees;
    private final CampingLogger logger;
    private final String tableName;
}
