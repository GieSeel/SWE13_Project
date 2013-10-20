package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

/**
 * The manager class for the {@link Service} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ServiceMgr extends BaseDataObjectMgr {
    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param thePitchMgr
     *            the {@link PitchMgr}
     * @param theSiteMgr
     *            the {@link SiteMgr}
     */
    public ServiceMgr(final AccessableDatabase db, final PitchMgr thePitchMgr,
	    final SiteMgr theSiteMgr) {
	super(db);
	pitchMgr = thePitchMgr;
	siteMgr = theSiteMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Service().getTableName();
    }

    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(employeeRoleMgr);
	subMgr.add(pitchMgr);
	subMgr.add(siteMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final Date creationDate = (Date) map.get("creationDate");
	final String description = (String) map.get("description");
	final Date doneDate = (Date) map.get("doneDate");
	final int priority = (int) map.get("priority");
	final int serviceNumber = (int) map.get("serviceNumber");

	final EmployeeRole employeeRole = (EmployeeRole) map.get("employeeRole");
	final Pitch pitch = (Pitch) map.get("pitch");
	final Site site = (Site) map.get("site");

	return new Service(id, creationDate, description, doneDate, employeeRole,
		pitch, priority, serviceNumber, site);
    }

    /** The {@link EmployeeRoleMgr} */
    private EmployeeRoleMgr employeeRoleMgr;

    /** The {@link PitchMgr} */
    private final PitchMgr pitchMgr;

    /** The {@link SiteMgr} */
    private final SiteMgr siteMgr;
}