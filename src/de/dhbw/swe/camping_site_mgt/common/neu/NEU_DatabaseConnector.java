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
package de.dhbw.swe.camping_site_mgt.common.neu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.IntArrayParser;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataStructure;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_NatureOfSoil;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_Type;

/**
 * Insert description for DatabaseMgr
 * 
 * @author GieSeel
 * @version 1.0
 */
public class NEU_DatabaseConnector {

	private static NEU_DatabaseConnector instance;

	public static synchronized NEU_DatabaseConnector getInstance() {
		if (instance == null) {
			new NEU_DatabaseConnector();
		}
		return instance;
	}

	public NEU_DatabaseConnector() {
		instance = this;
	}

	/** The {@link CampingLogger}. */
	private static CampingLogger logger = CampingLogger
			.getLogger(NEU_DatabaseConnector.class);

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

			loadGuests();
			loadPitches();
			loadBookings();
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

	private void loadGuests() {
		String query = "SELECT * FROM guest;";
		PreparedStatement statement;

		if (conncetion == null) {
			logger.error("Not connected with database");
		}
		try {
			statement = conncetion.prepareStatement(query);
			final ResultSet result = statement.executeQuery();
			guests = new HashMap<>();
			while (result.next()) {
				guests.put(
						result.getInt("id"),
						new NEU_Guest(result.getInt("id"), result
								.getString("person_identification_number"),
								result.getString("person_name"), result
										.getString("person_first_name"),
								new Date(result.getTimestamp(
										"person_date_of_birth").getTime()),
								result.getString("address_street"), result
										.getString("address_house_number"),
								result.getString("town_name"), result
										.getString("town_postal_code"), result
										.getString("country_name"), result
										.getString("country_acronym")));
			}
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	private void loadPitches() {
		String query = "SELECT * FROM pitch;";
		PreparedStatement statement;

		if (conncetion == null) {
			logger.error("Not connected with database");
		}
		try {
			statement = conncetion.prepareStatement(query);
			final ResultSet result = statement.executeQuery();
			pitches = new HashMap<>();
			while (result.next()) {
				pitches.put(
						result.getInt("id"),
						new NEU_Pitch(result.getInt("id"), result
								.getString("area"), Pitch_Type.values()[result
								.getInt("type")], result.getInt("height"),
								result.getInt("width"), Pitch_NatureOfSoil
										.values()[result.getInt("type")],
								result.getString("characteristics"), result
										.getString("x_coords"), result
										.getString("y_coords")));
			}
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	private void loadBookings() {
		String query = "SELECT * FROM booking;";
		PreparedStatement statement;

		if (conncetion == null) {
			logger.error("Not connected with database");
		}
		try {
			statement = conncetion.prepareStatement(query);
			final ResultSet result = statement.executeQuery();
			bookings = new HashMap<>();
			while (result.next()) {
				bookings.put(
						result.getInt("id"),
						new NEU_Booking(result.getInt("id"), new Date(result
								.getTimestamp("duration_from").getTime()),
								new Date(result.getTimestamp("duration_until")
										.getTime()), getGuest(result
										.getInt("guest_id")), result
										.getBoolean("checked_in"),
								getPitch(result.getInt("pitch_id"))));
			}
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	public Collection<NEU_Guest> getGuests() {
		return guests.values();
	}

	public Collection<NEU_Pitch> getPitches() {
		return pitches.values();
	}

	public Collection<NEU_Booking> getBookings() {
		return bookings.values();
	}

	public void saveGuest(NEU_Guest guest) {
		if(guests.containsValue(guest)) {
			logger.info("Gast ist schon vorhanden!");
			return;
		}
		PreparedStatement statement;
		String query;
		// Prepare INSERT query
		query = "INSERT INTO guest(person_identification_number, person_name, person_first_name, person_date_of_birth, address_street, address_house_number, town_name, town_postal_code, country_name, country_acronym) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			statement = conncetion.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, guest.getPerson_identification_number());
			statement.setString(2, guest.getPerson_name());
			statement.setString(3, guest.getPerson_first_name());
			statement.setTimestamp(4,
					new Timestamp((guest.getPerson_date_of_birth()).getTime()));
			statement.setString(5, guest.getAddress_street());
			statement.setString(6, guest.getAddress_house_number());
			statement.setString(7, guest.getTown_name());
			statement.setString(8, guest.getTown_postal_code());
			statement.setString(9, guest.getCountry_name());
			statement.setString(10, guest.getCountry_acronym());

			statement.executeUpdate();

			final ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				guest.setId(result.getInt(1));
			}

			// Save in object list
			guests.put(guest.getId(), guest);
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	public void savePitch(NEU_Pitch pitch) {
		PreparedStatement statement;
		String query;
		// Prepare INSERT query
		query = "INSERT INTO pitch(id, area, type, height, width, nature_of_soil, characteristics, x_coords, y_coords) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			statement = conncetion.prepareStatement(query);

			statement.setInt(1, pitch.getId());
			statement.setString(2, pitch.getArea());
			statement.setInt(3, pitch.getType().ordinal());
			statement.setInt(4, pitch.getHeight());
			statement.setInt(5, pitch.getWidth());
			statement.setInt(6, pitch.getNatureOfSoil().ordinal());
			statement.setString(7, pitch.getCharacteristics());
			statement.setString(8, pitch.getX_coords());
			statement.setString(9, pitch.getY_coords());

			statement.executeUpdate();

			// Save in object list
			pitches.put(pitch.getId(), pitch);
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	public void saveBooking(NEU_Booking booking) {
		PreparedStatement statement;
		String query;
		// Prepare INSERT query
		query = "INSERT INTO booking(duration_from, duration_until, guest_id, checked_in, pitch_id) VALUES (?, ?, ?, ?, ?);";

		try {
			statement = conncetion.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			statement.setTimestamp(1, new Timestamp(
					(booking.getDuration_from()).getTime()));
			statement.setTimestamp(2,
					new Timestamp((booking.getDuration_until()).getTime()));
			statement.setInt(3, booking.getGuest().getId());
			statement.setBoolean(4, booking.isChecked_in());
			statement.setInt(5, booking.getPitch().getId());

			statement.executeUpdate();

			final ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				booking.setId(result.getInt(1));
			}

			// Save in object list
			bookings.put(booking.getId(), booking);
		} catch (final SQLException e) {
			logger.error("SQL-Exception: " + e.getMessage());
		}
	}

	private HashMap<Integer, NEU_Guest> guests;
	private HashMap<Integer, NEU_Pitch> pitches;
	private HashMap<Integer, NEU_Booking> bookings;

	public NEU_Guest getGuest(Integer id) {
		if (guests.containsKey(id)) {
			return guests.get(id);
		}
		logger.error("Guest Objekt " + id + " nicht enthalten!");
		return null;
	}

	public NEU_Pitch getPitch(Integer id) {
		if (pitches.containsKey(id)) {
			return pitches.get(id);
		}
		logger.error("Pitches Objekt " + id + " nicht enthalten!");
		return null;

	}

	public NEU_Booking getBooking(Integer id) {
		if (bookings.containsKey(id)) {
			return bookings.get(id);
		}
		logger.error("Bookings Objekt " + id + " nicht enthalten!");
		return null;

	}

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
				for (final ColumnInfo column : DataStructure
						.getStructureFor(table)) {
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
					} else if (dbTyp.equals(java.lang.reflect.Array.class)) {
						// Array
						entry.put(fieldName, IntArrayParser.parseArray(result
								.getString(dbName)));
					} else if (dbTyp.equals(Date.class)) {
						// Date
						entry.put(fieldName,
								new Date(result.getTimestamp(dbName).getTime()));
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

	public void removeEntryFrom(final String table,
			final HashMap<String, Object> dbObject) {
		final String query = "DELETE FROM " + table + " WHERE id = ?";
		try {
			final PreparedStatement statement = conncetion
					.prepareStatement(query);
			statement.setInt(1, (int) dbObject.get("id"));
			statement.executeUpdate();
		} catch (final SQLException e) {
			logger.error("SQL-Exception while removing object" + e.getMessage());
		}

	}

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
			final HashMap<String, Object> dbObject,
			final ColumnInfo[] columnInfos) {
		Class<? extends Object> dbTyp;
		String fieldName;
		int i = 1;
		for (; i < columnInfos.length; i++) {
			dbTyp = columnInfos[i].getDbType();
			fieldName = columnInfos[i].getFieldName();

			if (dbTyp.equals(Boolean.class)) {
				// Boolean
				try {
					statement.setInt(i, ((boolean) dbObject.get(fieldName) ? 1
							: 0));
				} catch (final SQLException e) {
					logger.error("SQL-Exception (fill Boolean)"
							+ e.getMessage());
				}
			} else if (dbTyp.equals(Integer.class)) {
				// Integer
				try {
					statement.setInt(i, (Integer) dbObject.get(fieldName));
				} catch (final SQLException e) {
					logger.error("SQL-Exception (fill Integer)"
							+ e.getMessage());
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
			} else if (dbTyp.equals(java.lang.reflect.Array.class)) {
				// Array
				try {
					statement.setString(i, IntArrayParser
							.parseArray((int[]) dbObject.get(fieldName)));
				} catch (final SQLException e) {
					logger.error("SQL-Exception (fill Array)" + e.getMessage());
				}
			} else if (dbTyp.equals(Date.class)) {
				// Date
				try {
					statement.setTimestamp(
							i,
							new Timestamp(((Date) dbObject.get(fieldName))
									.getTime()));
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
