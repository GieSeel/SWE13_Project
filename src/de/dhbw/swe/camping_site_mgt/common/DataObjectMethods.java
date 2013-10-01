package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataStructure;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ObjectFieldAccess;

public class DataObjectMethods {
	/**
	 * Prepares the data of the object for the database.
	 * 
	 * @param obj
	 *            is the object
	 * @return
	 */
	public static HashMap<String, Object> getDatabaseDataOf(Object obj) {
		if (!(obj instanceof DataObject)) {
			// logger: object is not an instance of...
			return null;
		}
		final HashMap<String, Object> elements = new HashMap<String, Object>();
		Object tmpObj;
		for (ColumnInfo column : DataStructure
				.getStructureFor(((DataObject) obj).getName())) {
			tmpObj = ObjectFieldAccess.getValueOf(column.getColumnName(), obj);
			elements.put(column.getColumnName(), tmpObj);
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
	public static HashMap<String, Object> getDisplayDataOf(Object obj) {
		if (!(obj instanceof DataObject)) {
			// logger: object is not an instance of...
			return null;
		}
		final HashMap<String, Object> elements = new HashMap<String, Object>();
		final String className = obj.getClass().toString().toLowerCase() + "_";
		Object tmpObj;
		for (ColumnInfo column : DataStructure
				.getStructureFor(((DataObject) obj).getName())) {
			tmpObj = ObjectFieldAccess.getValueOf(column.getColumnName(), obj);
			if (column.getReleationToColumn() != null) {
				elements.putAll(getDisplayDataOf(tmpObj));
			} else {
				elements.put(className + column.getColumnName(), tmpObj);
			}
		}
		return elements;
	}
}
