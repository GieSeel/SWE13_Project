package de.dhbw.swe.camping_site_mgt.common.database_mgt;

public class ColumnInfo {

    public ColumnInfo(final String dbName, final Class<? extends Object> dbType) {
	this(dbName, dbType, null, dbName, null);
    }

    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final Class<? extends Object> releationToColumn) {
	this(dbName, dbType, null, releationToColumn.getSimpleName().toLowerCase(),
		releationToColumn);
    }

    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final String displayName) {
	this(dbName, dbType, displayName, dbName, null);
    }

    public ColumnInfo(final String dbName, final Class<? extends Object> dbType,
	    final String displayName, final String fieldName,
	    final Class<? extends Object> releationToColumn) {
	this.dbName = dbName;
	this.fieldName = fieldName;
	this.dbType = dbType;
	this.displayName = displayName;
	this.releationToColumn = releationToColumn;
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

    private final String dbName;

    private final Class<? extends Object> dbType;

    private final String displayName;

    private final String fieldName;

    private final Class<? extends Object> releationToColumn;
}