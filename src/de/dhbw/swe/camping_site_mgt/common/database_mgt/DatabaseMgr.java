/**
 * Comments for file DatabaseMgr.java
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
package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.sql.*;
import java.util.*;
import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.AddressMgr;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * Insert description for DatabaseMgr
 * 
 * @author GieSeel
 * @version 1.0
 */
public class DatabaseMgr {
    /** The singleton instance. */
    private static DatabaseMgr instance;
    private static CampingLogger logger;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized DatabaseMgr getInstance() {
	if (instance == null) {
	    instance = new DatabaseMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private DatabaseMgr() {
    }

    /**
     * Connects to database.
     * 
     * @param url
     *            address of database
     * @param user
     *            login name
     * @param password
     *            login password
     * @return
     */
    public boolean connect(final String url, final String user,
	    final String password) {
	logger = CampingLogger.getLogger(this.getClass());
	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException | IllegalAccessException
		| ClassNotFoundException e1) {
	    logger.error("There is an error while trying to connect with database!");
	    // e1.printStackTrace();
	    return false;
	}

	try {
	    conncetion = DriverManager.getConnection(url, user, password);
	    logger.info("Connected with Database.");
	    // TODO del -- Tests v
	    final AddressMgr test = AddressMgr.getInstance();
	    // TODO del -- Tests ^
	} catch (final SQLException e) {
	    logger.error("Error in SQL..." + e.getMessage());
	    return false;
	}
	return true;
    }

    /**
     * Disconnect from database.
     * 
     * @return
     */
    public boolean disconnect() {
	try {
	    conncetion.close();
	    System.out.println("Disconnected from Database.");
	} catch (final SQLException e) {
	    logger.error("Error in SQL..." + e.getMessage());
	    return false;
	}
	return true;
    }

    /**
     * Gets all entries of the given table.
     * 
     * @param table
     *            the table
     * @return the database objects
     */
    public List<HashMap<String, Object>> getAllEntriesOf(final String table) {
	PreparedStatement statement;
	final String query = "SELECT * FROM " + table + ";";
	final List<HashMap<String, Object>> entries = new ArrayList<HashMap<String, Object>>();
	if (conncetion == null) {
	    logger.error("Not connected with database");
	    return entries;
	}
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    HashMap<String, Object> entry;

	    while (result.next()) {
		entry = new HashMap<String, Object>();
		for (final ColumnInfo column : DataStructure.getStructureFor(table)) {
		    if (column.getDbType().equals(String.class)) {
			entry.put(column.getFieldName(),
				result.getString(column.getDbName()));
		    } else if (column.getDbType().equals(Integer.class)) {
			entry.put(column.getFieldName(),
				result.getInt(column.getDbName()));
		    } else if (column.getDbType().equals(Float.class)) {
			entry.put(column.getFieldName(),
				result.getFloat(column.getDbName()));
		    } else if (column.getDbType().equals(Date.class)) {
			entry.put(column.getFieldName(), new Date(
				result.getTimestamp(column.getDbName()).getTime()));
		    } else {
			logger.error("Unexpected typ in database result analysis!");
		    }
		}
		entries.add(entry);
	    }
	    return entries;
	} catch (final SQLException e1) {
	    logger.error("SQL-Exception..." + e1.getMessage());
	}
	return null;
    }

    /**
     * Saves an entry into database.
     * 
     * @param string
     *            table where the entry will be saved
     * @param dbObject
     *            the prepared object
     * @return
     */
    public int saveEntryTo(final String table,
	    final HashMap<String, Object> dbObject) {
	final ColumnInfo[] columnInfos = DataStructure.getStructureFor(table);
	PreparedStatement statement;
	String query;
	// Prepare INSERT query
	query = "INSERT INTO " + table + "(";

	String tmpVal = "";
	for (int i = 1; i < columnInfos.length; i++) {
	    if (!tmpVal.isEmpty()) {
		query += ", ";
		tmpVal += ", ";
	    }
	    query += columnInfos[i].getDbName();
	    tmpVal += "?";
	}
	query += ") VALUES (" + tmpVal + ");";

	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    for (int i = 1; i < columnInfos.length; i++) {
		if (columnInfos[i].getDbType().equals(String.class)) {
		    statement.setString(i,
			    (String) dbObject.get(columnInfos[i].getFieldName()));
		} else if (columnInfos[i].getDbType().equals(Integer.class)) {
		    statement.setInt(i,
			    (Integer) dbObject.get(columnInfos[i].getFieldName()));
		} else if (columnInfos[i].getDbType().equals(Float.class)) {
		    statement.setFloat(i,
			    (Float) dbObject.get(columnInfos[i].getFieldName()));
		} else if (columnInfos[i].getDbType().equals(Date.class)) {
		    statement.setTimestamp(
			    i,
			    new Timestamp(
				    ((Date) dbObject.get(columnInfos[i].getFieldName())).getTime()));
		} else {
		    logger.error("Unexpected typ in database INSERT!");
		}
	    }
	    statement.executeUpdate();

	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt(1);
	    }
	} catch (final SQLException e1) {
	    logger.error("SQL-Exception..." + e1.getMessage());
	}
	return 0;
    }

    private Connection conncetion;
}
