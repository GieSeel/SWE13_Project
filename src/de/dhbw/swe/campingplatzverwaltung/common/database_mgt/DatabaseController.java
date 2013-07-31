package de.dhbw.swe.campingplatzverwaltung.common.database_mgt;

import java.sql.*;

public class DatabaseController {

    public boolean connect(final String url, final String user,
	    final String password) {

	// url = "jdbc:mysql://http://gieseel.funpic.de/mysql1157678";
	// user = "mysql1157678";
	// password = "blubber1bis3";

	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException | IllegalAccessException
		| ClassNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    return false;
	}

	try {

	    conncetion = DriverManager.getConnection(url, user, password);

	    System.out.println("Connected with Database.");

	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean disconnect() {
	try {
	    conncetion.close();
	    System.out.println("Disconnected from Database.");
	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean doQueryTest() {

	final String query = "SELECT * FROM person";
	Statement st;
	try {
	    st = conncetion.createStatement();
	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	ResultSet rs;
	try {
	    rs = st.executeQuery(query);
	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	try {
	    while (rs.next()) {
		final int id = rs.getInt("identificationNumber");
		final String name = rs.getString("name");
		final String fName = rs.getString("firstName");
		final Date bDate = rs.getDate("dateOfBirth");
		System.out.println(id + " " + fName + " " + name + " " + bDate);
	    }
	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    private Connection conncetion;
}
