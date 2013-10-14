/**
 * Comments for file ObjectMgr.java
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
package de.dhbw.swe.camping_site_mgt.common;

import java.util.*;
import java.util.Map.Entry;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.CampingTable;

/**
 * Insert description for ObjectMgr
 * 
 * @author GieSeel
 * @version 1.0
 */
public abstract class BaseDataObjectMgr {
    /** The {@link DatabaseMgr}. */
    private final static DatabaseMgr db = DatabaseMgr.getInstance();

    protected BaseDataObjectMgr() {
	data = new HashMap<>();
	logger = getLogger();
	checkForSubObjects();
	load(); // Load all data from database
    }

    /**
     * Gets an {@link DataObject} from the data list.
     * 
     * @param id
     *            the {@link DataObject} id
     * @return the {@link DataObject}
     */
    public DataObject get(final int id) {
	if (data.containsKey(id)) {
	    return data.get(id);
	}
	return null;
    }

    /**
     * Gets the {@link DataObject}.
     * 
     * @param id
     *            the {@link DataObject} id
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     * @return the {@link DataObject}
     */
    public DataObject objectGet(final int id, final String parentTableName,
	    final int parentID) {
	final DataObject dataObject = get(id);
	if (parentTableName != null) {
	    dataObject.addUsage(parentTableName, parentID);
	}
	return dataObject;
    }

    /**
     * Inserts the {@link DataObject} into database.
     * 
     * @param dataObject
     *            the {@link DataObject}
     */
    public void objectInsert(final DataObject dataObject) {
	// Sub objects
	subObjectInsert(dataObject);

	// If object already exists just save that id
	int id = isObjectExisting(dataObject);
	if (id == 0) {
	    id = db.insertEntryInto(getTableName(), object2entry(dataObject));
	}

	// Add or replace object in object list
	add(id, dataObject);
    }

    /**
     * 
     * Deletes the {@link DataObject}.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return true if it was successful
     */
    public boolean objectRemove(final DataObject dataObject) {
	// Sub objects
	subObjectRemove(dataObject);

	final int id = dataObject.getId();
	if (isObjectInUse(dataObject)) {
	    // logger.error("Object is still in use!");
	    return false;
	}
	db.removeEntryFrom(getTableName(), object2entry(dataObject));
	return remove(id);
    }

    /**
     * Updates the {@link DataObject}.
     * 
     * @param object
     *            the current {@link DataObject}
     * @param newObject
     *            the new {@link DataObject}
     */
    public void objectUpdate(final DataObject object, final DataObject newObject) {
	// Sub objects
	subObjectUpdate(object, newObject);

	// Update object
	final int id = isObjectExisting(object);
	if (id == 0 || (!evenUpdateInUse() && isObjectInUse(object))) {
	    // If object is in use or it doesn't exists a new one is needed
	    objectInsert(newObject);
	    return;
	}
	// If newObject already exists the old object will be removed and
	// the existing object will be used
	final int newID = isObjectExisting(newObject);
	if (newID != 0) {
	    objectRemove(object);
	    add(newID, newObject);
	} else {
	    // Update object in object list and database
	    add(id, newObject);
	    db.updateEntryIn(getTableName(), object2entry(newObject));
	}
    }

    /**
     * Saves the display data for the {@link CampingTable}.
     * 
     * @param columns
     *            the columns that will be displayed
     * @return a {@link Vector} with all needed data
     */
    public Vector<HashMap<String, Object>> saveDisplayDataTo(
	    final HashMap<String, ColumnInfo> columns) {
	final Vector<HashMap<String, Object>> displayDataList = new Vector<>();
	String columnKey, className, fieldName;
	DataObject objectValue;
	HashMap<String, Object> displayData;

	for (final Entry<Integer, DataObject> objectEntry : data.entrySet()) {
	    objectValue = objectEntry.getValue();
	    displayData = new HashMap<>();
	    for (final Entry<String, ColumnInfo> columnEntry : columns.entrySet()) {
		columnKey = columnEntry.getKey();
		fieldName = columnEntry.getValue().getFieldName();
		className = columnKey.split("_")[0];
		if (className.equals(getTableName())) {
		    // Save object field value
		    displayData.put(columnKey,
			    ObjectFieldAccess.getValueOf(fieldName, objectValue));
		} else {
		    // Save sub object field value
		    displayData.put(columnKey,
			    subSaveDisplayData(className, fieldName, objectValue));
		}
	    }
	    if (displayData != null) {
		displayDataList.add(displayData);
	    }
	}
	return displayDataList;
    }

    /**
     * Adds or updates the {@link DataObject} to/in data list.
     * 
     * @param id
     *            the {@link DataObject} id
     * @param dataObject
     *            the {@link DataObject}
     */
    protected void add(final int id, final DataObject dataObject) {
	// Sub objects
	subObjectAdd(id, dataObject);

	dataObject.setId(id);
	data.put(id, dataObject);
    }

    /**
     * Parses a database entry to an {@link DataObject}.
     * 
     * TODO TODO
     * 
     * @param entry
     *            the entry of the database
     * @return the prepared {@link DataObject}
     */
    protected abstract DataObject entry2object(final HashMap<String, Object> entry);

    /**
     * @return true if the object should update even it is still in use.
     */
    protected abstract boolean evenUpdateInUse();

    /**
     * @return the {@link CampingLogger}.
     */
    abstract protected CampingLogger getLogger();

    /**
     * @return the table name.
     */
    abstract protected String getTableName();

    /**
     * Checks if the DataObject already exists.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the id of the {@link DataObject}
     */
    protected int isObjectExisting(final DataObject dataObject) {
	if (data.containsValue(dataObject)) {
	    return dataObject.getId();
	}
	return 0;
    }

    /**
     * Checks if the {@link DataObject} is still in use.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return true if object is still in use
     */
    protected boolean isObjectInUse(final DataObject dataObject) {
	return dataObject.isInUse();
    }

    /**
     * Parses a {@link DataObject} to a database entry.
     * 
     * @param id
     *            the {@link DataObject} id
     * @param dataObject
     *            the {@link DataObject}
     * @return the prepared entry
     */
    protected HashMap<String, Object> object2entry(final DataObject dataObject) {
	final HashMap<String, Object> entry = new HashMap<>();
	Object tmpObj;
	for (final ColumnInfo column : DataStructure.getStructureFor(getTableName())) {
	    tmpObj = ObjectFieldAccess.getValueOf(column.getFieldName(), dataObject);
	    if (column.getReleationToColumn() == null) {
		entry.put(column.getFieldName(), tmpObj);
	    } else if (column.getDbType().equals(Enum.class)) {
		entry.put(column.getFieldName(), ((Enum) tmpObj).ordinal());
	    } else {
		entry.put(column.getFieldName(), ((DataObject) tmpObj).getId());
	    }
	}
	return entry;
    }

    /**
     * Removes the {@link DataObject} from the data list.
     * 
     * @param id
     *            the {@link DataObject} id
     * @return true if it was successful
     */
    protected boolean remove(final int id) {
	if (data.containsKey(id)) {
	    data.remove(id);
	    return true;
	}
	return false;
    }

    /**
     * Updates sub objects of {@link DataObject} for data list.
     * 
     * @param id
     *            the id of the {@link DataObject}
     * @param dataObject
     *            the {@link DataObject}
     */
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	// Initially left empty
	if (hasSubObjects) {
	    logger.error("Forgott to implement 'subObjectAdd' methode");
	}
    }

    /**
     * Inserts the sub objects of {@link DataObject} into database.
     * 
     * @param dataObject
     *            the {@link DataObject}
     */
    protected void subObjectInsert(final DataObject dataObject) {
	// Initially left empty
	if (hasSubObjects) {
	    logger.error("Forgott to implement 'subObjectInsert' methode");
	}
    }

    /**
     * 
     * Deletes the sub object of {@link DataObject}.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return true if it was successful
     */
    protected void subObjectRemove(final DataObject dataObject) {
	// Initially left empty
	if (hasSubObjects) {
	    logger.error("Forgott to implement 'subObjectRemove' methode");
	}
    }

    /**
     * Updates the sub objects of the {@link DataObject}.
     * 
     * @param dataObject
     *            the current {@link DataObject}
     * @param newDataObject
     *            the new {@link DataObject}
     */
    protected void subObjectUpdate(final DataObject dataObject,
	    final DataObject newDataObject) {
	// Initially left empty
	if (hasSubObjects) {
	    logger.error("Forgott to implement 'subObjectUpdate' methode");
	}
    }

    /**
     * Saves the value of the field of one of the sub objects.
     * 
     * @param className
     *            the name of the subclass
     * @param fieldName
     *            the name of the field
     * @param object
     *            the object that is parent of the other objects
     * @return
     */
    protected Object subSaveDisplayData(final String className,
	    final String fieldName, final DataObject object) {
	// Initially left empty
	if (hasSubObjects) {
	    logger.error("Forgott to implement 'subSaveDisplayData' methode");
	}
	return null;
    }

    /**
     * Checks if the object has sub objects.
     */
    private void checkForSubObjects() {
	hasSubObjects = false;
	for (final ColumnInfo column : DataStructure.getStructureFor(getTableName())) {
	    if (column.getReleationToColumn() != null
		    && column.getDbType() != Enum.class) {
		hasSubObjects = true;
	    }
	}
    }

    /**
     * Loads the {@link DataObject}s from the database.
     */
    private void load() {
	DataObject dataObject;
	for (final HashMap<String, Object> entry : db.getAllEntriesOf(getTableName())) {
	    dataObject = entry2object(entry);
	    add(dataObject.getId(), dataObject);
	}
    }

    // Sub objects
    final Vector<HashMap<String, Object>> subDataList = new Vector<>();
    // subSaveDisplayDataTo(subDataList, subColumns);

    /** The {@link CampingLogger}. */
    protected final CampingLogger logger;
    /** All {@link DataObject} of one table. */
    private final HashMap<Integer, DataObject> data;
    /** If the {@link DataObject} has sub objects. */
    private boolean hasSubObjects;
}
