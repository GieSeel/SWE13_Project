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

import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt.CampingTable;

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
	tableName = getTableName();
	subMgr = getSubMgr();
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
     * @return the table name.
     */
    abstract public String getTableName();

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
	    id = db.insertEntryInto(tableName, object2entry(dataObject));
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
	db.removeEntryFrom(tableName, object2entry(dataObject));
	return remove(id);
    }

    /**
     * Saves an object for the display data for the {@link CampingTable}.
     * 
     * @param columns
     *            the columns that will be displayed
     * @param dataObject
     *            the {@link DataObject}
     * @param displayData
     *            the return list item
     */
    public void objects2DisplayIn(final HashMap<Integer, Object> displayData,
	    final HashMap<Integer, ColumnInfo> columns, final DataObject dataObject) {
	int columnKey;
	ColumnInfo columnValue;
	Class<? extends Object> dbTyp;
	Object object;
	for (final Entry<Integer, ColumnInfo> columnEntry : columns.entrySet()) {
	    columnKey = columnEntry.getKey();
	    columnValue = columnEntry.getValue();
	    if (columnValue.getClassName().equals(tableName)) {
		dbTyp = columnValue.getDbType();
		object = ObjectFieldAccess.getValueOf(columnValue.getFieldName(),
			dataObject);

		if (dbTyp.equals(Boolean.class)) {
		    // Boolean
		    displayData.put(columnKey, object);
		} else if (dbTyp.equals(Integer.class)) {
		    // Integer
		    displayData.put(columnKey, object);
		} else if (dbTyp.equals(Float.class)) {
		    // Float
		    displayData.put(columnKey, object);
		} else if (dbTyp.equals(Euro.class)) {
		    // Euro
		    displayData.put(columnKey, ((Euro) object).returnValue());
		} else if (dbTyp.equals(String.class)) {
		    // String
		    displayData.put(columnKey, object);
		} else if (dbTyp.equals(Array.class)) {
		    // Array
		    logger.error("Unexpected typ while parsing object data to display data (Array)");
		} else if (dbTyp.equals(Date.class)) {
		    // Date
		    // TODO DATE
		    displayData.put(columnKey,
			    new SimpleDateFormat("dd. MM yyyy").format(object));
		} else if (dbTyp.equals(Enum.class)) {
		    // Enum
		    displayData.put(columnKey, object.toString());
		} else {
		    logger.error("Unexpected typ while parsing object data to display data!");
		}

	    }
	}
	// Save the sub objects
	subObjects2DisplayIn(displayData, columns, dataObject);

	// Save id of the object
	displayData.put(displayData.size() * -1,
		tableName + "_" + dataObject.getId());

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
	    db.updateEntryIn(tableName, object2entry(newObject));
	}
    }

    /**
     * Saves an object from display data from the {@link CampingTable}.
     * 
     * @param columns
     *            the columns that were displayed
     * @param displayData
     *            the data from the table
     */
    public DataObject saveDisplay2Object(
	    final HashMap<Integer, ColumnInfo> columns,
	    final HashMap<Integer, Object> values) {

	String val;
	int valueKey;
	ColumnInfo columnInfo;
	Class<? extends Object> dbTyp;
	final HashMap<String, Object> objects = new HashMap<>();
	for (final Entry<Integer, Object> value : values.entrySet()) {
	    valueKey = value.getKey();
	    val = value.getValue().toString();

	    if (valueKey < 0) {
		// Get id of the object
		final String[] classId = val.split("_");
		if (classId[0].equals(tableName)) {
		    objects.put("id", new Integer(classId[1]));
		}
	    } else {
		// Get values of the object
		columnInfo = columns.get(valueKey);
		if (columnInfo.getClassName().equals(tableName)) {
		    dbTyp = columns.get(valueKey).getDbType();

		    // Parse value and save it
		    if (dbTyp.equals(Boolean.class)) {
			// Boolean
			objects.put(columnInfo.getFieldName(), new Boolean(val));
		    } else if (dbTyp.equals(Integer.class)) {
			// Integer
			objects.put(columnInfo.getFieldName(), new Integer(val));
		    } else if (dbTyp.equals(Float.class)) {
			// Float
			objects.put(columnInfo.getFieldName(), new Float(val));
		    } else if (dbTyp.equals(Euro.class)) {
			// Euro
			objects.put(columnInfo.getFieldName(), new Euro(val));
		    } else if (dbTyp.equals(String.class)) {
			// String
			objects.put(columnInfo.getFieldName(), val);
		    } else if (dbTyp.equals(Array.class)) {
			// Array
			logger.error("Unexpected typ while parsing display data to object data (Array)");
		    } else if (dbTyp.equals(Date.class)) {
			// Date
			try {
			    objects.put(columnInfo.getFieldName(),
				    new SimpleDateFormat("dd. MM yyyy").parse(val));
			} catch (final ParseException e) {
			    logger.error("Date-Parse exception while parsing display data to object data!");
			}
		    } else if (dbTyp.equals(Enum.class)) {
			// Enum
			objects.put(columnInfo.getFieldName(), new Integer(val));
		    } else {
			logger.error("Unexpected typ while parsing display data to object data!");
		    }
		}
	    }
	}
	// Sub objects
	subDisplay2ObjectIn(objects, columns, values);

	// Create object
	return display2Object(objects);
    }

    /**
     * Saves a list of objects for the display data in the {@link CampingTable}.
     * 
     * @param displayDataList
     *            return list
     * @param columns
     *            the columns that will be displayed
     * @return a {@link Vector} with all needed data
     */
    public void saveObjects2DisplayIn(
	    final Vector<HashMap<Integer, Object>> displayDataList,
	    final HashMap<Integer, ColumnInfo> columns) {
	HashMap<Integer, Object> displayData;
	for (final DataObject dataObject : data.values()) {
	    displayData = new HashMap<>();
	    objects2DisplayIn(displayData, columns, dataObject);
	    displayDataList.add(displayData);
	}
    }

    /**
     * Parses a database entry to an {@link DataObject}.
     * 
     * @param entry
     *            the entry of the database
     * @return the prepared {@link DataObject}
     */
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	subEntry2ObjectIn(entry);
	return map2DataObject(entry);
    }

    /**
     * @return true if the object should update even it is still in use.
     */
    abstract protected boolean evenUpdateInUse();

    /**
     * @return the {@link CampingLogger}.
     */
    abstract protected CampingLogger getLogger();

    /**
     * @return the manager of the sub objects.
     */
    abstract protected Vector<BaseDataObjectMgr> getSubMgr();

    /**
     * Converts the map to an {@link DataObject}. TODO
     * 
     * @param map
     *            the map
     * @return the object
     */
    abstract protected DataObject map2DataObject(final HashMap<String, Object> map);

    /**
     * Adds or updates the {@link DataObject} to/in data list.
     * 
     * @param id
     *            the {@link DataObject} id
     * @param dataObject
     *            the {@link DataObject}
     */
    private void add(final int id, final DataObject dataObject) {
	// Sub objects
	subObjectAdd(id, dataObject);

	dataObject.setId(id);
	data.put(id, dataObject);
    }

    /**
     * Returns an object for the display data for the {@link CampingTable}.
     * 
     * @param objects
     *            the map of all object entries
     * @return the object
     */
    private DataObject display2Object(final HashMap<String, Object> objects) {
	return map2DataObject(objects);
    }

    /**
     * Checks if the DataObject already exists.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the id of the {@link DataObject}
     */
    private int isObjectExisting(final DataObject dataObject) {
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
    private boolean isObjectInUse(final DataObject dataObject) {
	return dataObject.isInUse();
    }

    /**
     * Loads the {@link DataObject}s from the database.
     */
    private void load() {
	DataObject dataObject;
	for (final HashMap<String, Object> entry : db.getAllEntriesOf(tableName)) {
	    dataObject = entry2object(entry);
	    add(dataObject.getId(), dataObject);
	}
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
    private HashMap<String, Object> object2entry(final DataObject dataObject) {
	final HashMap<String, Object> entry = new HashMap<>();
	Object tmpObj;
	for (final ColumnInfo column : DataStructure.getStructureFor(tableName)) {
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
    private boolean remove(final int id) {
	if (data.containsKey(id)) {
	    data.remove(id);
	    return true;
	}
	return false;
    }

    /**
     * Saves sub objects from display data from the {@link CampingTable}.
     * 
     * @param objects
     *            the return list
     * @param columns
     *            the displayed columns
     * @param values
     *            the displayed data
     */
    private void subDisplay2ObjectIn(final HashMap<String, Object> objects,
	    final HashMap<Integer, ColumnInfo> columns,
	    final HashMap<Integer, Object> values) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		objects.put(subManager.getTableName(),
			subManager.saveDisplay2Object(columns, values));
	    }
	}
    }

    /**
     * Parses a database entry to an {@link DataObject}.
     * 
     * @param entry
     *            the entry of the database
     */
    private void subEntry2ObjectIn(final HashMap<String, Object> entry) {
	String subTableName;
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		subTableName = subManager.getTableName();
		entry.put(subTableName, subManager.objectGet(
			(int) entry.get(subTableName), getTableName(),
			(int) entry.get("id")));
	    }
	}
    }

    /**
     * Updates sub objects of {@link DataObject} for data list.
     * 
     * @param id
     *            the id of the {@link DataObject}
     * @param dataObject
     *            the {@link DataObject}
     */
    private void subObjectAdd(final int id, final DataObject dataObject) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		((DataObject) ObjectFieldAccess.getValueOf(
			subManager.getTableName(), dataObject)).addUsage(tableName,
			id);
	    }
	}
    }

    /**
     * Inserts the sub objects of {@link DataObject} into database.
     * 
     * @param dataObject
     *            the {@link DataObject}
     */
    private void subObjectInsert(final DataObject dataObject) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		subManager.objectInsert((DataObject) ObjectFieldAccess.getValueOf(
			subManager.getTableName(), dataObject));
	    }
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
    private void subObjectRemove(final DataObject dataObject) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		final DataObject subObject = (DataObject) ObjectFieldAccess.getValueOf(
			subManager.getTableName(), dataObject);
		((BaseDataObject) subObject).delUsage(tableName, dataObject.getId());
		subManager.objectRemove(subObject);
	    }
	}
    }

    /**
     * Saves the sub objects for the display data for the {@link CampingTable}.
     * 
     * @param displayData
     *            return list
     * @param columns
     *            the columns that will be displayed
     * @param dataObject
     */
    private void subObjects2DisplayIn(final HashMap<Integer, Object> displayData,
	    final HashMap<Integer, ColumnInfo> columns, final DataObject dataObject) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		subManager.objects2DisplayIn(
			displayData,
			columns,
			(DataObject) ObjectFieldAccess.getValueOf(
				subManager.getTableName(), dataObject));
	    }
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
    private void subObjectUpdate(final DataObject dataObject,
	    final DataObject newDataObject) {
	if (subMgr != null) {
	    for (final BaseDataObjectMgr subManager : subMgr) {
		final DataObject subObject = (DataObject) ObjectFieldAccess.getValueOf(
			subManager.getTableName(), dataObject);
		((BaseDataObject) subObject).delUsage(tableName, dataObject.getId());
		subManager.objectUpdate(
			subObject,
			(DataObject) ObjectFieldAccess.getValueOf(
				subManager.getTableName(), newDataObject));
	    }
	}
    }

    /** The {@link CampingLogger}. */
    protected final CampingLogger logger;

    /** All {@link DataObject} of one table. */
    private final HashMap<Integer, DataObject> data;

    /** The manager of the sub objects (null if there are no sub objects). */
    private final Vector<BaseDataObjectMgr> subMgr;

    /** The table name. */
    private final String tableName;
}
