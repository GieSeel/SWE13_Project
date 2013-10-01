package de.dhbw.swe.camping_site_mgt.common.database_mgt;

public class ColumnInfo {
private String columnName;
private Class<? extends Object> type;
private String displayName;
private String releationToColumn;

public ColumnInfo(String columnName, Class<? extends Object> type, String displayName,
		String releationToColumn) {
	super();
	this.columnName = columnName;
	this.type = type;
	this.displayName = displayName;
	this.releationToColumn = releationToColumn;
}

/**
 * @return the columnName.
 */
public String getColumnName() {
	return columnName;
}

/**
 * @return the type.
 */
public Class<? extends Object> getType() {
	return type;
}

/**
 * @return the displayName.
 */
public String getDisplayName() {
	return displayName;
}

/**
 * @return the releationToColumn.
 */
public String getReleationToColumn() {
	return releationToColumn;
}

}
