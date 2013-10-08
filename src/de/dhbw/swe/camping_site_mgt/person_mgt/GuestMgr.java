package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Guest} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class GuestMgr {
    /** The singleton instance. */
    private static GuestMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized GuestMgr getInstance() {
	if (instance == null) {
	    instance = new GuestMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private GuestMgr() {
	guests = new HashMap<>();
	tableName = "guest";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Guest} object id
     * @return the {@link Guest}
     */
    public Guest get(final int id) {
	if (guests.containsKey(id)) {
	    return guests.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Guest} object
     */
    public void insert(final Guest object) {
	// Sub objects
	PersonMgr.getInstance().insert(object.getPerson());
	VisitorsTaxClassMgr.getInstance().insert(object.getVisitorsTaxClass());

	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Checks if the object is used by any {@link Guest} object.
     * 
     * @param object
     *            the {@link Person} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Person object) {
	final List<Person> persons = new Vector<>();

	for (final Guest guest : guests.values()) {
	    persons.add(guest.getPerson());
	}
	return persons.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Guest} object.
     * 
     * @param country
     *            the {@link VisitorsTaxClass} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final VisitorsTaxClass object) {
	final List<VisitorsTaxClass> visitorsTaxClasses = new Vector<>();

	for (final Guest guest : guests.values()) {
	    visitorsTaxClasses.add(guest.getVisitorsTaxClass());
	}
	return visitorsTaxClasses.contains(object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Guest} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// guests.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Guest} object id
     * @param object
     *            the {@link Guest} object
     */
    public void update(final Guest oldObject, final Guest object) {
	// Sub objects
	PersonMgr.getInstance().update(oldObject.getPerson(), object.getPerson());
	VisitorsTaxClassMgr.getInstance().update(oldObject.getVisitorsTaxClass(),
		object.getVisitorsTaxClass());

	final int id = isObjectExisting(oldObject);
	if (id == 0) {// || isObjectInUse(oldObject)) { // Guest immer update
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
     *            the list with the {@link Guest} objects
     * 
     */
    private void add(final HashMap<Integer, Guest> objects) {
	guests.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link Guest} object id
     * @param object
     *            the {@link Guest} object
     */
    private void add(final int id, final Guest object) {
	object.setId(id);
	guests.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Guest} object
     */
    private HashMap<Integer, Guest> entry2object(final HashMap<String, Object> entry) {
	final HashMap<Integer, Guest> object = new HashMap<>();

	int id;
	Person person;
	VisitorsTaxClass visitorsTaxClass;

	id = (int) entry.get("id");
	person = PersonMgr.getInstance().get((int) entry.get("person"));
	visitorsTaxClass = VisitorsTaxClassMgr.getInstance().get(
		(int) entry.get("visitorsTaxClass"));

	object.put(id, new Guest(id, person, visitorsTaxClass));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Guest} object
     * @param object
     *            the {@link Guest} object
     */
    private void insert(int id, final Guest object) {
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
     *            the {@link Guest} object
     * @return id of the object (0 if it doesn't exists)
     */
    private int isObjectExisting(final Guest object) {
	if (guests.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Guest} object
     * @return true if object is still in use
     */
    @Unfinished
    private boolean isObjectInUse(final Guest object) {
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
     *            the {@link Guest} object id
     * @param object
     *            the {@link Guest} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Guest object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("person", object.getPerson().getId());
	entry.put("visitorsTaxClass", object.getVisitorsTaxClass().getId());
	return entry;
    }

    private final DatabaseMgr db;
    private final HashMap<Integer, Guest> guests;
    private final CampingLogger logger;
    private final String tableName;
}
