package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.DurationMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRoleMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.Deliverypoint;
import de.dhbw.swe.camping_site_mgt.place_mgt.DeliverypointMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;
import de.dhbw.swe.camping_site_mgt.place_mgt.SiteMgr;

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
    public ServiceMgr(final AccessableDatabase db, EmployeeRoleMgr theEmployeeRoleMgr, final PitchMgr thePitchMgr,
	    final SiteMgr theSiteMgr, DeliverypointMgr theDeliverypointMgr, DurationMgr theDurationMgr) {
	super(db);
	employeeRoleMgr = theEmployeeRoleMgr;
	pitchMgr = thePitchMgr;
	siteMgr = theSiteMgr;
	deliverypointMgr = theDeliverypointMgr;
	durationMgr = theDurationMgr;
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
	subMgr.add(deliverypointMgr);
	subMgr.add(durationMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final String description = (String) map.get("description");
	final int priority = (int) map.get("priority");
//	final int serviceNumber = (int) map.get("serviceNumber");

	final Duration duration = (Duration) map.get("duration");
	final EmployeeRole employee_role = (EmployeeRole) map.get("employee_role");
	final Pitch pitch = (Pitch) map.get("pitch");
	final Site site = (Site) map.get("site");
	final Deliverypoint deliverypoint = (Deliverypoint) map.get("deliverypoint");

	return new Service(id, pitch, site, deliverypoint, employee_role, duration, description, priority);
//	return new Service(id, creationDate, description, duration, employeeRole,
//		pitch, priority, serviceNumber, site);
    }

    /** The {@link EmployeeRoleMgr} */
    private EmployeeRoleMgr employeeRoleMgr;

    /** The {@link PitchMgr} */
    private final PitchMgr pitchMgr;

    /** The {@link SiteMgr} */
    private final SiteMgr siteMgr;

    /** The {@link DeliverypointMgr} */
    private final DeliverypointMgr deliverypointMgr;
    
    private DurationMgr durationMgr;
}