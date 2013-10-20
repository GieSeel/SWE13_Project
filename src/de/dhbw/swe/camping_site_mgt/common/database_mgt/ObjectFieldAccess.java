package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.lang.reflect.*;

import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * Insert description for ObjectFieldAccess
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ObjectFieldAccess {

    private static AccessableDatabase db;

    private static CampingLogger logger;

    /**
     * Returns the value of the given column of the object.
     * 
     * @param fieldName
     * @param object
     * @return
     */
    static public Object getValueOf(final String fieldName, final DataObject object) {
	logger = CampingLogger.getLogger(object.getClass());
	final String methodName = "get" + fieldName;

	for (final Method method : object.getClass().getMethods()) {
	    if (method.getName().toLowerCase().equals(methodName.toLowerCase())) {
		try {
		    return method.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
		    logger.error("Could not determine method: " + method.getName());
		    return null;
		}
	    }
	}
	logger.error("Class doesn't contain method: " + methodName);
	return null;
    }

    /**
     * Selects the database object with the given id.
     * 
     * @param id
     * @param object
     */
    @Deprecated
    public static void querySelect(final int id, final Object object) {
	logger = CampingLogger.getLogger(object.getClass());

	final String methodName = "querySelect" + object.getClass();

	for (final Method method : object.getClass().getMethods()) {
	    if (method.getName().toLowerCase().equals(methodName.toLowerCase())) {
		try {
		    method.invoke(db, id, object);
		    return;
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
		    logger.error("Could not determine method: " + method.getName());
		    return;
		}
	    }
	}
	logger.error("Class doesn't contain method: " + methodName);
    }

    public static void setDb(final AccessableDatabase theDb) {
	db = theDb;
    }

    /**
     * Sets the value of the given column of the object.
     * 
     * @param columnName
     * @param value
     * @param object
     */
    @Deprecated
    public static void setValueOf(final String columnName, final Object value,
	    final Object object) {
	logger = CampingLogger.getLogger(object.getClass());
	final String methodName = "set" + columnName;

	for (final Method method : object.getClass().getMethods()) {
	    if (method.getName().toLowerCase().equals(methodName.toLowerCase())) {
		try {
		    method.invoke(object, value);
		    return;
		} catch (IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e) {
		    logger.error("Could not determine method: " + method.getName());
		    return;
		}
	    }
	}
	logger.error("Class doesn't contain method: " + methodName);
    }
}
