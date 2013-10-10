package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

@Deprecated
public class DataObjectMethods {
    private static CampingLogger logger;

    /**
     * Prepares the data of the object for the database.
     * 
     * @param dataObject
     *            is the object
     * @return
     */
    public static HashMap<String, Object> getDatabaseDataOf(
	    final DataObject dataObject) {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	Object tmpObj;
	for (final ColumnInfo column : DataStructure.getStructureFor(dataObject.getTableName())) {
	    tmpObj = ObjectFieldAccess.getValueOf(column.getFieldName(), dataObject);
	    elements.put(column.getFieldName(), tmpObj);
	}
	return elements;
    }

    /**
     * Prepares the data for the display table.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public static HashMap<String, Object> getDisplayDataOf(final Object obj) {
	return null;
	// if (!(obj instanceof DataObject)) {
	// // logger: object is not an instance of...
	// return null;
	// }
	// final HashMap<String, Object> elements = new HashMap<String,
	// Object>();
	// final String className = obj.getClass().toString().toLowerCase() +
	// "_";
	// Object tmpObj;
	// for (final ColumnInfo column :
	// DataStructure.getStructureFor(((DataObject) obj).getName())) {
	// tmpObj = ObjectFieldAccess.getValueOf(column.getFieldName(), obj);
	// if (column.getReleationToColumn() == null) {
	// elements.put(className + column.getFieldName(), tmpObj);
	// } else {
	// elements.putAll(getDisplayDataOf(tmpObj));
	// }
	// }
	// return elements;
    }

    public static boolean insertOrUpdateDatabase(final Object object) {
	logger = CampingLogger.getLogger(object.getClass());
	if (!(object instanceof DataObject)) {
	    logger.error("Object is not an instance of 'DataObject'");
	    return false;
	}

	final DataObject dataObject = (DataObject) object;

	final DatabaseController db = DatabaseController.getInstance();
	// If id=0 the object is new
	if (dataObject.getId() == 0) {
	    // Check if object already exists
	    // for (final DataObject dataListObject :
	    // db.querySelectObjects(dataObject.getName())) {
	    // if (dataObject.allreadyExists(dataListObject)) {
	    // // All ids will have been updated
	    // return true;
	    // }
	    // }
	}
	// Insert or update the actual object into database
	// return db.insertOrUpdateObject(getDatabaseDataOf(dataObject));
	return false;
    }
}
