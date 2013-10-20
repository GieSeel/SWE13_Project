package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar.StatusBarController;

/**
 * Controller for the database.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class DatabasController implements AccessableDatabase {

    /**
     * Constructor.
     */
    public DatabasController() {
	ObjectFieldAccess.setDb(this);
	dbConnector = new DatabaseConnector();
    }

    /**
     * Connects the database.
     * 
     * @param address
     *            the database address
     * @param user
     *            the user name
     * @param password
     *            the user password
     */
    public void connect(final String address, final String user,
	    final String password) {
	connectedWithDb = dbConnector.connect(address, user, password);

	if (!connectedWithDb) {
	    final StatusBarController statusBarCtrl = StatusBarController.getInstance();
	    final LanguageMgr lM = LanguageMgr.getInstance();
	    statusBarCtrl.setStatus(lM.get(LanguageProperties.DB_NOT_CONNECTED));
	}
    }

    /**
     * Disconnects the database.
     */
    public void disconnect() {
	if (isConnectedWithDb()) {
	    dbConnector.disconnect();
	}
    }

    @Override
    public List<HashMap<String, Object>> getAllEntriesOf(final String table) {
	return dbConnector.getAllEntriesOf(table);
    }

    @Override
    public int insertEntryInto(final String table,
	    final HashMap<String, Object> dbObject) {
	return dbConnector.insertEntryInto(table, dbObject);
    }

    /**
     * @return if the database is connected.
     */
    public boolean isConnectedWithDb() {
	return connectedWithDb;
    }

    @Override
    public void removeEntryFrom(final String table,
	    final HashMap<String, Object> dbObject) {
	dbConnector.removeEntryFrom(table, dbObject);
    }

    @Override
    public void updateEntryIn(final String table,
	    final HashMap<String, Object> dbObject) {
	dbConnector.updateEntryIn(table, dbObject);
    }

    /** The connection state. */
    private boolean connectedWithDb = false;

    /** The {@link DatabaseConnector}. */
    private final DatabaseConnector dbConnector;
}
