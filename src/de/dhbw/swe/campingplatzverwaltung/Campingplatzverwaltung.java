package de.dhbw.swe.campingplatzverwaltung;

import de.dhbw.swe.campingplatzverwaltung.booking_mgt.BookingMgr;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;
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

    /**   */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    private static LanguageProperties lp;

    public static void main(final String[] args) {
	new Campingplatzverwaltung();

    }

    /**
     * Constructor.
     * 
     */
    public Campingplatzverwaltung() {
	gui = Gui.getInstance();
	configDatabaseController();
	gui.startupGui();
    }

    /**
     * Configuring the {@link DatabaseController}.
     */
    private void configDatabaseController() {
	dbController = DatabaseController.getInstance();

	gui = Gui.getInstance();

	dbController.connect("jdbc:mysql://localhost/camping", "willi", "bald");
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