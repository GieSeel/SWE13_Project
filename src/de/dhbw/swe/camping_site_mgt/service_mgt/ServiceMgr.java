package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRoleMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

/**
 * The manager class for the {@link Service} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ServiceMgr {
    /** The singleton instance. */
    private static ServiceMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized ServiceMgr getInstance() {
	if (instance == null) {
	    instance = new ServiceMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private ServiceMgr() {
	services = new HashMap<>();
	tableName = "service";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link Service} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Service} object
     */
    public Service objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Service object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Service} object
     */
    public void objectInsert(final Service object) {
	// Sub objects
	EmployeeRoleMgr.getInstance().objectInsert(object.getEmployeeRole());
	PitchMgr.getInstance().objectInsert(object.getPitch());
	SiteMgr.getInstance().objectInsert(object.getSite());

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
     *            the {@link Service} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Service object) {
	// Sub objects
	final int id = object.getId();
	final EmployeeRole employeeRole = object.getEmployeeRole();
	final Pitch pitch = object.getPitch();
	final Site site = object.getSite();
	employeeRole.delUsage(tableName, id);
	pitch.delUsage(tableName, id);
	site.delUsage(tableName, id);
	EmployeeRoleMgr.getInstance().objectRemove(employeeRole);
	PitchMgr.getInstance().objectRemove(pitch);
	SiteMgr.getInstance().objectRemove(site);

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
     *            the old {@link Service} object
     * @param newObject
     *            the new {@link Service} object
     */
    public void objectUpdate(final Service object, final Service newObject) {
	// Sub objects
	int id = object.getId();
	final EmployeeRole employeeRole = object.getEmployeeRole();
	final Pitch pitch = object.getPitch();
	final Site site = object.getSite();
	employeeRole.delUsage(tableName, id);
	pitch.delUsage(tableName, id);
	site.delUsage(tableName, id);
	EmployeeRoleMgr.getInstance().objectUpdate(employeeRole,
		newObject.getEmployeeRole());
	PitchMgr.getInstance().objectUpdate(pitch, newObject.getPitch());
	SiteMgr.getInstance().objectUpdate(site, newObject.getSite());

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
     *            the list with the {@link Service} objects
     * 
     */
    private void add(final HashMap<Integer, Service> objects) {
	services.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link Service} object id
     * @param object
     *            the {@link Service} object
     */
    private void add(final int id, final Service object) {
	object.getEmployeeRole().addUsage(tableName, id);
	object.getPitch().addUsage(tableName, id);
	object.getSite().addUsage(tableName, id);
	object.setId(id);
	services.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Service} object
     */
    private HashMap<Integer, Service> entry2object(
	    final HashMap<String, Object> entry) {
	final HashMap<Integer, Service> object = new HashMap<>();
	int id;
	Date creationDate;
	String description;
	Date doneDate;
	EmployeeRole employeeRole;
	Pitch pitch;
	int priority;
	int serviceNumber;
	Site site;

	id = (int) entry.get("id");
	creationDate = (Date) entry.get("creationDate");
	description = (String) entry.get("description");
	doneDate = (Date) entry.get("doneDate");
	employeeRole = EmployeeRoleMgr.getInstance().objectGet(
		(int) entry.get("employeeRole"), tableName, id);
	pitch = PitchMgr.getInstance().objectGet((int) entry.get("pitch"),
		tableName, id);
	priority = (int) entry.get("priority");
	serviceNumber = (int) entry.get("serviceNumber");
	site = SiteMgr.getInstance().objectGet((int) entry.get("site"), tableName,
		id);

	object.put(id, new Service(id, creationDate, description, doneDate,
		employeeRole, pitch, priority, serviceNumber, site));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Service} object id
     * @return the {@link Service} object
     */
    private Service get(final int id) {
	if (services.containsKey(id)) {
	    final Service object = services.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Service} object
     * @return the id of the {@link Service} object
     */
    private int isObjectExisting(final Service object) {
	if (services.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Service} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Service object) {
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
     *            the {@link Service} object id
     * @param object
     *            the {@link Service} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Service object) {
	final HashMap<String, Object> entry = new HashMap<>();
	entry.put("id", object.getId());
	entry.put("creationDate", object.getCreationDate());
	entry.put("description", object.getDescription());
	entry.put("doneDate", object.getDoneDate());
	entry.put("employeeRole", object.getEmployeeRole().getId());
	entry.put("pitch", object.getPitch().getId());
	entry.put("priority", object.getPriority());
	entry.put("serviceNumber", object.getServiceNumber());
	entry.put("site", object.getSite().getId());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Service} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (services.containsKey(id)) {
	    services.remove(id);
	    return true;
	}
	return false;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Service> services;
    private final String tableName;
}