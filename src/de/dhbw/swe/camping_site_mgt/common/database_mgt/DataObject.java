package de.dhbw.swe.camping_site_mgt.common.database_mgt;

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

    // boolean allreadyExists(HashMap<String, Object> elements);

    /**
     * 
     * @param parentTableName
     * @param parentID
     */
    void addUsage(String parentTableName, int parentID);

    /**
     * @return the id of the {@link DataObject}.
     */
    int getId();

    String getTableName();

    /**
     * @return true if {@link DataObject} is still in use.
     */
    boolean isInUse();

    /**
     * Sets the id of the {@link DataObject}.
     * 
     * @param id
     *            the id
     */
    void setId(int id);

    // /**
    // * Fills the object with the given database data.
    // *
    // * @param elements
    // */
    // void setDatabaseData(final HashMap<String, Object> elements);

    /**
     * Fills the object with the data from the display table.
     * 
     * @param elements
     */
    // void setDisplayData(final HashMap<String, Object> elements);

    // Address setDatabaseData(final HashMap<String, Object> objects); ... ohne
    // return? - oder nur ID
    // Address setTableData(final HashMap<String, Object> objects); ... ohne
    // return? - oder nur ID
}
