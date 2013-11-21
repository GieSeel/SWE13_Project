package de.dhbw.swe.camping_site_mgt;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabasController;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.data_mgt.DataMgr;
import de.dhbw.swe.camping_site_mgt.gui_mgt.*;

public class CampingSiteManagement {

    /** The (default) database name. */
    private static String DATABASE_NAME = "swe_gieselseel";

    /** The (default) host name. */
    private static String HOST_NAME = "localhost";

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(CampingSiteManagement.class);

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

	new CampingSiteManagement();
    }

    /**
     * Constructor.
     * 
     */
    public CampingSiteManagement() {
	dbCtrl = new DatabasController();
	final String dbAddress = "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME;
	dbCtrl.connect(dbAddress, "willi", "bald");

	final DataMgr dataMgr = new DataMgr(dbCtrl);

	guiCtrl = new GuiController(dataMgr.getDataObjectMgrs());
	addApplicationClosedListener();
    }

    private void addApplicationClosedListener() {
	guiCtrl.register(new ApplicationClosedListener() {

	    @Override
	    public void closedApplication() {
		dbCtrl.disconnect();
	    }
	});
    }

    /** The {@link DatabasController}. */
    private final DatabasController dbCtrl;
    /** The {@link GuiController}. */
    private final GuiController guiCtrl;

}