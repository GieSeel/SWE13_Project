package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Site} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class SiteMgr {
    /** The singleton instance. */
    private static SiteMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized SiteMgr getInstance() {
	if (instance == null) {
	    instance = new SiteMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private SiteMgr() {
	sites = new HashMap<>();
	tableName = "site";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link Site} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Site} object
     */
    public Site objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Site object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Site} object
     */
    public void objectInsert(final Site object) {
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
     *            the {@link Site} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Site object) {
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
     *            the old {@link Site} object
     * @param newObject
     *            the new {@link Site} object
     */
    public void objectUpdate(final Site object, final Site newObject) {
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
     *            the list with the {@link Site} objects
     * 
     */
    private void add(final HashMap<Integer, Site> objects) {
	sites.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link Site} object id
     * @param object
     *            the {@link Site} object
     */
    private void add(final int id, final Site object) {
	object.setId(id);
	sites.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Site} object
     */
    private HashMap<Integer, Site> entry2object(final HashMap<String, Object> entry) {
	final HashMap<Integer, Site> object = new HashMap<>();
	int id;
	String description;
	String labeling;
	String openingHours;
	String type;

	id = (int) entry.get("id");
	description = (String) entry.get("description");
	labeling = (String) entry.get("labeling");
	openingHours = (String) entry.get("openingHours");
	type = (String) entry.get("type");

	object.put(id, new Site(id, description, labeling, openingHours, type));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Site} object id
     * @return the {@link Site} object
     */
    private Site get(final int id) {
	if (sites.containsKey(id)) {
	    final Site object = sites.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Site} object
     * @return the id of the {@link Site} object
     */
    private int isObjectExisting(final Site object) {
	if (sites.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Site} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Site object) {
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
     *            the {@link Site} object id
     * @param object
     *            the {@link Site} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Site object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("description", object.getDescription());
	entry.put("labeling", object.getLabeling());
	entry.put("openingHours", object.getOpeningHours());
	entry.put("type", object.getType());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Site} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (sites.containsKey(id)) {
	    sites.remove(id);
	    return true;
	}
	return false;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Site> sites;
    private final String tableName;
}