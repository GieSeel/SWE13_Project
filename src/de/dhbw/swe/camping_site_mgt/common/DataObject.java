package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;

/**
 * Interface for the data objects. <br />
 * Add two constructors:
 * <ol>
 * <li>With all attributes</li>
 * <li>Without the ID</li>
 * <ol>
 * <br />
 * 
 * @author Benny
 * 
 */
public interface DataObject {

    int getId();

    String getName();

    /**
     * Fills the object with the given database data.
     * 
     * @param elements
     */
    void setDatabaseData(final HashMap<String, Object> elements);

    /**
     * Fills the object with the data from the display table.
     * 
     * @param elements
     */
    void setDisplayData(final HashMap<String, Object> elements);

    // Address setDatabaseData(final HashMap<String, Object> objects); ... ohne
    // return? - oder nur ID
    // Address setTableData(final HashMap<String, Object> objects); ... ohne
    // return? - oder nur ID
}
