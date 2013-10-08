package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.*;

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
	tableName = "visitorsTaxClass";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object from the object list.
     * 
     * @param id
     *            the {@link Service} object id
     * @return the {@link Service}
     */
    public Service get(final int id) {
	if (services.containsKey(id)) {
	    return services.get(id);
	}
	return null;
    }

    /**
     * Inserts object into database.
     * 
     * @param object
     *            the {@link Service} object
     */
    public void insert(final Service object) {
	// If object already exists just save this id
	insert(isObjectExisting(object), object);
    }

    /**
     * Checks if the object is used by any {@link Service} object.
     * 
     * @param object
     *            the {@link EmployeeRole} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final EmployeeRole object) {
	final List<EmployeeRole> employeeRoles = new Vector<>();

	for (final Service service : services.values()) {
	    employeeRoles.add(service.getEmployeeRole());
	}
	return employeeRoles.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Service} object.
     * 
     * @param object
     *            the {@link Pitch} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Pitch object) {
	final List<Pitch> pitches = new Vector<>();

	for (final Service service : services.values()) {
	    pitches.add(service.getPitch());
	}
	return pitches.contains(object);
    }

    /**
     * Checks if the object is used by any {@link Service} object.
     * 
     * @param object
     *            the {@link Site} object
     * @return true if object is still in use
     */
    public boolean isSubObjectInUse(final Site object) {
	final List<Site> sites = new Vector<>();

	for (final Service service : services.values()) {
	    sites.add(service.getSite());
	}
	return sites.contains(object);
    }

    /**
     * Removes object from object list.
     * 
     * @param id
     *            the {@link Service} object id
     */
    @Unfinished
    public void remove(final int id) {
	// Sub objects

	// service.remove(id);
	// TODO remove from database. Check if object is still in use (from
	// parents)!!
    }

    /**
     * Updates object in database.
     * 
     * @param id
     *            the {@link Service} object id
     * @param object
     *            the {@link Service} object
     */
    public void update(final Service oldObject, final Service object) {
	final int id = isObjectExisting(oldObject);
	if (id == 0) { // || isObjectInUse(oldObject)) { // Service immer
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
     *            the list with the {@link Service} objects
     * 
     */
    private void add(final HashMap<Integer, Service> objects) {
	services.putAll(objects);
    }

    /**
     * Adds object to object list.
     * 
     * @param id
     *            the {@link Service} object id
     * @param object
     *            the {@link Service} object
     */
    private void add(final int id, final Service object) {
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
	employeeRole = EmployeeRoleMgr.getInstance().get(
		(int) entry.get("employeeRole"));
	pitch = PitchMgr.getInstance().get(
		(int) entry.get((int) entry.get("pitch")));
	priority = (int) entry.get("priority");
	serviceNumber = (int) entry.get("serviceNumber");
	site = SiteMgr.getInstance().get((int) entry.get((int) entry.get("site")));

	object.put(id, new Service(id, creationDate, description, doneDate,
		employeeRole, pitch, priority, serviceNumber, site));
	return object;
    }

    /**
     * Inserts object into database.
     * 
     * @param id
     *            the id of the {@link Service} object
     * @param object
     *            the {@link Service} object
     */
    private void insert(int id, final Service object) {
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
     *            the {@link Service} object
     * @return id of the object (0 if it doesn't exists)
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
    @Unfinished
    private boolean isObjectInUse(final Service object) {
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

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Service> services;
    private final String tableName;
}
