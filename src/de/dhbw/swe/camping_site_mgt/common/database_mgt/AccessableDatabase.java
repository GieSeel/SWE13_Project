package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.*;

/**
 * The interface for a accessable database.
 * 
 * @author GieSeel
 * @version 1.0
 */
public interface AccessableDatabase {
    /**
     * Gets all entries of the given table.
     * 
     * @param table
     *            the table
     * @return the database objects
     */
    List<HashMap<String, Object>> getAllEntriesOf(final String table);

    /**
     * Saves an entry into database.
     * 
     * @param string
     *            table where the entry will be saved
     * @param dbObject
     *            the prepared object
     * @return
     */
    int insertEntryInto(final String table, final HashMap<String, Object> dbObject);

    /**
     * Removes an entry from database.
     * 
     * @param table
     *            the table where the entry will be removed
     * @param dbObject
     *            the prepared object
     */
    void removeEntryFrom(final String table, final HashMap<String, Object> dbObject);

    /**
     * Saves an entry into database.
     * 
     * @param table
     *            table where the entry will be saved
     * @param dbObject
     *            the prepared object
     * @return
     */
    void updateEntryIn(final String table, final HashMap<String, Object> dbObject);
}
