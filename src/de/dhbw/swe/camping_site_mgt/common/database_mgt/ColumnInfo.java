package de.dhbw.swe.camping_site_mgt.common.database_mgt;

public class ColumnInfo {

    /**
     * Constructor.
     * 
     * @param dbName
     * @param dbType
     */
    public ColumnInfo(final String dbName, final Class<? extends Object> dbType) {
	this(dbName, dbType, null, dbName, null);
    }

    /**
     * Constructor.
     * 
     * @param dbName
     * @param dbType
     * @param releationToColumn
     */
    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final Class<? extends Object> releationToColumn) {
	this(dbName, dbType, null, releationToColumn.getSimpleName().toLowerCase(),
		releationToColumn);
    }

    /**
     * Constructor.
     * 
     * @param dbName
     * @param dbType
     * @param displayName
     */
    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final String displayName) {
	this(dbName, dbType, displayName, dbName, null);
    }

    /**
     * Constructor.
     * 
     * @param dbName
     * @param dbType
     * @param displayName
     * @param releationToColumn
     */
    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final String displayName,
	    final Class<? extends Object> releationToColumn) {
	this(dbName, dbType, displayName, dbName, releationToColumn);
    }

    /**
     * Constructor.
     * 
     * @param dbName
     * @param dbType
     * @param displayName
     * @param fieldName
     * @param releationToColumn
     */
    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final String displayName, final String fieldName,
	    final Class<? extends Object> releationToColumn) {
	this.dbName = dbName;
	this.fieldName = fieldName;
	this.dbType = dbType;
	this.displayName = displayName;
	this.releationToColumn = releationToColumn;
	this.className = null;
    }

    /**
     * Returns the className.
     * 
     * @return the className
     */
    public String getClassName() {
	return className;
    }

    /**
     * Returns the dbName.
     * 
     * @return the dbName
     */
    public String getDbName() {
	return dbName;
    }

    /**
     * Returns the dbType.
     * 
     * @return the dbType
     */
    public Class<? extends Object> getDbType() {
	return dbType;
    }

    /**
     * Returns the displayName.
     * 
     * @return the displayName
     */
    public String getDisplayName() {
	return displayName;
    }

    /**
     * Returns the fieldName.
     * 
     * @return the fieldName
     */
    public String getFieldName() {
	return fieldName;
    }

    /**
     * Returns the releationToColumn.
     * 
     * @return the releationToColumn
     */
    public Class<? extends Object> getReleationToColumn() {
	return releationToColumn;
    }

    /**
     * Returns the releationToColumn name.
     * 
     * @return the releationToColumn
     */
    public String getReleationToColumnName() {
	return releationToColumn.getSimpleName().toLowerCase();
    }

    /**
     * Sets the className.
     * 
     * @param className
     *            the className to set
     */
    public void setClassName(final String className) {
	this.className = className;
    }

    private String className;

    private final String dbName;

    private final Class<? extends Object> dbType;

    private final String displayName;

    private final String fieldName;

    private final Class<? extends Object> releationToColumn;

}
