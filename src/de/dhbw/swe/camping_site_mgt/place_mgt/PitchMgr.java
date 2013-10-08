package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.awt.Polygon;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.Unfinished;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Pitch} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PitchMgr {
    /** The singleton instance. */
    private static PitchMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PitchMgr getInstance() {
	if (instance == null) {
	    instance = new PitchMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PitchMgr() {
	pitches = new HashMap<>();
	tableName = "pitch";
	logger = CampingLogger.getLogger(this.getClass());
	db = DatabaseMgr.getInstance();
	load(); // Load all data from database
    }

    /**
     * Gets the object.
     * 
     * @param id
     *            the {@link Pitch} object id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link Pitch} object
     */
    public Pitch objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final Pitch object = get(id);
	if (parentTableName != null) {
	    object.addUsage(parentTableName, parentID);
	}
	return object;
    }

    /**
     * Inserts the object in database.
     * 
     * @param object
     *            the {@link Pitch} object
     */
    public void objectInsert(final Pitch object) {
	// Sub objects
	SiteMgr.getInstance().objectInsert(object.getDeliveryPoint());

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
     *            the {@link Pitch} object
     * @return true if it was successful
     */
    @Unfinished
    public boolean objectRemove(final Pitch object) {
	// Sub objects
	final int id = object.getId();
	object.getDeliveryPoint().delUsage(tableName, id);

	SiteMgr.getInstance().objectRemove(object.getDeliveryPoint());

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
     *            the old {@link Pitch} object
     * @param newObject
     *            the new {@link Pitch} object
     */
    public void objectUpdate(final Pitch object, final Pitch newObject) {
	// Sub objects
	int id = object.getId();

	object.getDeliveryPoint().delUsage(tableName, id);

	SiteMgr.getInstance().objectUpdate(object.getDeliveryPoint(),
		newObject.getDeliveryPoint());

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
     *            the list with the {@link Pitch} objects
     * 
     */
    private void add(final HashMap<Integer, Pitch> objects) {
	pitches.putAll(objects);
    }

    /**
     * Adds or updates the object to object list.
     * 
     * @param id
     *            the {@link Pitch} object id
     * @param object
     *            the {@link Pitch} object
     */
    private void add(final int id, final Pitch object) {
	object.getDeliveryPoint().addUsage(tableName, id);
	object.setId(id);
	pitches.put(id, object);
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Pitch} object
     */
    private HashMap<Integer, Pitch> entry2object(final HashMap<String, Object> entry) {
	final HashMap<Integer, Pitch> object = new HashMap<>();
	int id;
	String area;
	String characteristics;
	Site deliveryPoint;
	int length;
	// Pitch_NatureOfSoil natureOfSoil;
	String natureOfSoil;
	final Polygon shape;
	final Pitch_Type type;
	int width;
	String xCoords;
	String yCoords;

	id = (int) entry.get("id");
	area = (String) entry.get("area");
	characteristics = (String) entry.get("characteristics");
	deliveryPoint = SiteMgr.getInstance().objectGet(
		(int) entry.get("deliveryPoint"), tableName, id);
	length = (int) entry.get("length");
	natureOfSoil = (String) entry.get("natureOfSoil");
	xCoords = (String) entry.get("xCoords");
	yCoords = (String) entry.get("yCoords");
	// Pitch_Type type; TODO
	width = (int) entry.get("width");

	object.put(id, new Pitch(id, characteristics, deliveryPoint, area, length,
		natureOfSoil, type, width, xCoords, yCoords));
	return object;
    }

    /**
     * Gets an object from the object list.
     * 
     * @param id
     *            the {@link Pitch} object id
     * @return the {@link Pitch} object
     */
    private Pitch get(final int id) {
	if (pitches.containsKey(id)) {
	    final Pitch object = pitches.get(id);
	    return object;
	}
	return null;
    }

    /**
     * Checks if the object already exists.
     * 
     * @param object
     *            the {@link Pitch} object
     * @return the id of the {@link Pitch} object
     */
    private int isObjectExisting(final Pitch object) {
	if (pitches.containsValue(object)) {
	    return object.getId();
	}
	return 0;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @param object
     *            the {@link Pitch} object
     * @return true if object is still in use
     */
    private boolean isObjectInUse(final Pitch object) {
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
     *            the {@link Pitch} object id
     * @param object
     *            the {@link Pitch} object
     * @return the prepared entry
     */
    private HashMap<String, Object> object2entry(final Pitch object) {
	final HashMap<String, Object> entry = new HashMap<>();
	// TODO FELDER ANPASSEN
	entry.put("id", object.getId());
	entry.put("area", object.getArea());
	entry.put("characteristics", object.getCharacteristics());
	entry.put("deliveryPoint", object.getDeliveryPoint().getId());
	entry.put("length", object.getLength());
	entry.put("natureOfSoil", object.getNatureOfSoil());
	entry.put("yCoords", object.getyCoords());
	entry.put("xCoords", object.getxCoords());
	entry.put("type", object.getType());
	entry.put("width", object.getWidth());
	return entry;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param id
     *            the {@link Pitch} object id
     * @return true if it was successful
     */
    @Unfinished
    private boolean remove(final int id) {
	if (pitches.containsKey(id)) {
	    pitches.remove(id);
	    return true;
	}
	return false;
    }

    private final DatabaseMgr db;
    private final CampingLogger logger;
    private final HashMap<Integer, Pitch> pitches;
    private final String tableName;
}