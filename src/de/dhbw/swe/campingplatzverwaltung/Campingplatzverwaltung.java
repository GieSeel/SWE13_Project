package de.dhbw.swe.campingplatzverwaltung;

import de.dhbw.swe.campingplatzverwaltung.booking_mgt.BookingMgr;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.common.logging.CampingLogger;
import de.dhbw.swe.campingplatzverwaltung.gui_mgt.Gui;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.EmployeeMgr;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.*;
import de.dhbw.swe.campingplatzverwaltung.service_mgt.ServiceMgr;

/**
 * Insert description for Campingplatzverwaltung
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Campingplatzverwaltung {

    /** The (default) database name. */
    private static String DATABASE_NAME = "camping";

    /** The (default) host name. */
    private static String HOST_NAME = "localhost";

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Campingplatzverwaltung.class);

    public static void main(final String[] args) {
	if (args != null && args.length > 0) {
	    if (args != null && args[0].length() > 0) {
		if (args[0].contains("?") || args[0].contains("help")) {
		    System.out.println("use as args:\t<host name> <database name>(optional)");
		    return;
		}
		HOST_NAME = args[0];
		logger.info("Database host name changed to:\t" + HOST_NAME);
		if (args.length > 1 && args[1].length() > 0) {
		    DATABASE_NAME = args[1];
		    logger.info("Database name changed to:\t" + DATABASE_NAME);
		}
	    }
	}
	new Campingplatzverwaltung();
    }

    /**
     * Constructor.
     * 
     */
    public Campingplatzverwaltung() {
	gui = Gui.getInstance();
	configDatabaseController();
	// TODO on delete comment change CampingplaceAdministrationTabbedPane
	gui.startupGui();
    }

    /**
     * Configuring the {@link DatabaseController}.
     */
    private void configDatabaseController() {
	dbController = DatabaseController.getInstance();
	gui = Gui.getInstance();
	dbController.connect("jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME,
		"willi", "bald");
	// final Guest test = dbController.querySelectGuest(1);
	// dbController.disconnect();
    }

    private BookingMgr bookingManager;

    /** The {@link DatabaseController}. */
    private DatabaseController dbController;
    private EmployeeMgr employeeManager;
    /** The {@link Gui}. */
    private Gui gui;
    private PitchMgr pitchManager;
    private ServiceMgr serviceManager;
    private SiteMgr siteManager;

}