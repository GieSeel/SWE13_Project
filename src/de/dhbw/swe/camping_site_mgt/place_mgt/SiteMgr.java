package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;

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
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Site} object id
     * @return the {@link Site}
     */
    public Site get(final int id) {
	if (sites.containsKey(id)) {
	    return sites.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Site} object
     */
    public void insert(final Site object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Site} object id
     */
    @Unfinished
    public void remove(final int id) {
	// sites.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Site} object id
     * @param object
     *            the {@link Site} object
     */
    public void update(final Site oldObject, final Site object) {
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
     *            the list with the {@link Site} objects
     * 
     */
    private void add(final HashMap<Integer, Site> objects) {
	sites.putAll(objects);
    }

    /**
     * Adds object to object list.
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
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Site} object
     * @param object
     *            the {@link Site} object
     */
    private void insert(int id, final Site object) {
	// TODO evtl. unnötige -> alles in "insert(Site object)"
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
     *            the {@link Site} object
     * @return id of the object (0 if it doesn't exists)
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
	// TODO -- -1 weil es momentan noch benutzt wird
	// Ask all parent manager classes if they use the object
	return PersonMgr.getInstance().isSubObjectInUse(object);
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

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Site> sites;
    private final String tableName;
}