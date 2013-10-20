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

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * Insert description for DatabaseMgr
 * 
 * @author GieSeel
 * @version 1.0
 */
public class DatabaseConnector implements AccessableDatabase {
    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(DatabaseConnector.class);

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
	logger.info("Connecting with database...");
	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException | IllegalAccessException
		| ClassNotFoundException e) {
	    logger.error("There is an error while trying to connect with database!");
	    return false;
	}

	try {
	    conncetion = DriverManager.getConnection(url, user, password);
	    logger.info("Connected with Database.");
	    // TODO del -- Tests v
	    // final VisitorsTaxClassMgr test =
	    // VisitorsTaxClassMgr.getInstance();
	    // test.objectInsert(new VisitorsTaxClass(
	    // VisitorsTaxClass_Labeling.BUSYSEASON, new Euro(9, 8)));
	    //
	    // final PersonMgr test = PersonMgr.getInstance();
	    // final Country country = new Country("DE", "Deutschland");
	    // final Town town = new Town("Pforzheim", "75177");
	    // final Calendar cal = new GregorianCalendar(1992, 4, 27);
	    // final Date datum = cal.getTime();
	    // final Person person = new Person("0123456789D", "Florian",
	    // "Seel",
	    // datum, "Ebersteinstr.", "3", town, country);
	    // test.objectInsert(person);
	    //
	    // final Town town2 = new Town("Haigerloch", "72401");
	    // test.objectUpdate(person, new Person("0123456789D", "Florian",
	    // "Seel",
	    // datum, "Buchenweg", "22/2", town2, country));

	    // TODO del -- Tests ^
	} catch (final SQLException e) {
	    logger.error("Error in SQL: " + e.getMessage());
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
	    logger.info("Disconnecting from Database...");
	    conncetion.close();
	    logger.info("Disconnected from Database!");
	} catch (final SQLException e) {
	    logger.error("Error in SQL: " + e.getMessage());
	    return false;
	}
	return true;
    }

    @Override
    public List<HashMap<String, Object>> getAllEntriesOf(final String table) {
	PreparedStatement statement;
	final String query = "SELECT * FROM " + table + ";";
	final Vector<HashMap<String, Object>> entries = new Vector<>();
	if (conncetion == null) {
	    logger.error("Not connected with database");
	    return entries;
	}
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    HashMap<String, Object> entry;

	    Class<? extends Object> dbTyp;
	    String fieldName, dbName;

	    while (result.next()) {
		entry = new HashMap<String, Object>();
		for (final ColumnInfo column : DataStructure.getStructureFor(table)) {
		    dbTyp = column.getDbType();
		    fieldName = column.getFieldName();
		    dbName = column.getDbName();

		    if (dbTyp.equals(Boolean.class)) {
			// Boolean
			entry.put(fieldName, (result.getInt(dbName) == 1 ? true
				: false));
		    } else if (dbTyp.equals(Integer.class)) {
			// Integer
			entry.put(fieldName, result.getInt(dbName));
		    } else if (dbTyp.equals(Float.class)) {
			// Float
			entry.put(fieldName, result.getFloat(dbName));
		    } else if (dbTyp.equals(Euro.class)) {
			// Euro
			entry.put(fieldName, new Euro(result.getFloat(dbName)));
		    } else if (dbTyp.equals(String.class)) {
			// String
			entry.put(fieldName, result.getString(dbName));
		    } else if (dbTyp.equals(Array.class)) {
			// Array
			entry.put(fieldName,
				IntArrayParser.parseArray(result.getString(dbName)));
		    } else if (dbTyp.equals(Date.class)) {
			// Date
			entry.put(fieldName, new Date(
				result.getTimestamp(dbName).getTime()));
		    } else if (dbTyp.equals(Enum.class)) {
			// Enum
			entry.put(fieldName, result.getInt(dbName));
		    } else {
			logger.error("Unexpected typ in database result analysis!");
		    }
		}
		entries.add(entry);
	    }
	} catch (final SQLException e) {
	    logger.error("SQL-Exception: " + e.getMessage());
	}
	return entries;
    }

    @Override
    public int insertEntryInto(final String table,
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
	    fillStatement(statement, dbObject, columnInfos);
	    statement.executeUpdate();

	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt(1);
	    }
	} catch (final SQLException e) {
	    logger.error("SQL-Exception: " + e.getMessage());
	}
	return 0;
    }

    @Override
    public void removeEntryFrom(final String table,
	    final HashMap<String, Object> dbObject) {
	final String query = "DELETE FROM " + table + " WHERE id = ?";
	try {
	    final PreparedStatement statement = conncetion.prepareStatement(query);
	    statement.setInt(1, (int) dbObject.get("id"));
	    statement.executeUpdate();
	} catch (final SQLException e) {
	    logger.error("SQL-Exception while removing object" + e.getMessage());
	}

    }

    @Override
    public void updateEntryIn(final String table,
	    final HashMap<String, Object> dbObject) {
	final ColumnInfo[] columnInfos = DataStructure.getStructureFor(table);
	PreparedStatement statement;
	String query;
	// Prepare UPDATE query
	query = "UPDATE " + table + " SET ";
	for (int i = 1; i < columnInfos.length; i++) {
	    if (i > 1) {
		query += ", ";
	    }
	    query += columnInfos[i].getDbName() + "=?";
	}
	query += " WHERE id=?;";

	try {
	    statement = conncetion.prepareStatement(query);
	    statement.setInt(fillStatement(statement, dbObject, columnInfos),
		    (int) dbObject.get("id"));
	    statement.executeUpdate();
	} catch (final SQLException e) {
	    logger.error("SQL-Exception: " + e.getMessage());
	}
    }

    /**
     * 
     * @param statement
     * @param dbObject
     * @param columnInfos
     * @return
     */
    private int fillStatement(final PreparedStatement statement,
	    final HashMap<String, Object> dbObject, final ColumnInfo[] columnInfos) {
	Class<? extends Object> dbTyp;
	String fieldName;
	int i = 1;
	for (; i < columnInfos.length; i++) {
	    dbTyp = columnInfos[i].getDbType();
	    fieldName = columnInfos[i].getFieldName();

	    if (dbTyp.equals(Boolean.class)) {
		// Boolean
		try {
		    statement.setInt(i, ((boolean) dbObject.get(fieldName) ? 1 : 0));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Boolean)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Integer.class)) {
		// Integer
		try {
		    statement.setInt(i, (Integer) dbObject.get(fieldName));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Integer)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Float.class)) {
		// Float
		try {
		    statement.setFloat(i, (Float) dbObject.get(fieldName));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Float)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Euro.class)) {
		// Euro
		try {
		    statement.setFloat(i,
			    ((Euro) dbObject.get(fieldName)).returnValue());
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Euro)" + e.getMessage());
		}
	    } else if (dbTyp.equals(String.class)) {
		// String
		try {
		    statement.setString(i, (String) dbObject.get(fieldName));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill String)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Array.class)) {
		// Array
		try {
		    statement.setString(
			    i,
			    IntArrayParser.parseArray((int[]) dbObject.get(fieldName)));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Array)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Date.class)) {
		// Date
		try {
		    statement.setTimestamp(
			    i,
			    new Timestamp(
				    ((Date) dbObject.get(fieldName)).getTime()));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Date)" + e.getMessage());
		}
	    } else if (dbTyp.equals(Enum.class)) {
		// Enum
		try {
		    statement.setInt(i, (Integer) dbObject.get(fieldName));
		} catch (final SQLException e) {
		    logger.error("SQL-Exception (fill Enum)" + e.getMessage());
		}
	    } else {
		logger.error("Unexpected typ in database INSERT!");
	    }
	}
	return i;
    }

    private Connection conncetion;
}
