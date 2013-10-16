package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRoleMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

/**
 * The manager class for the {@link Service} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ServiceMgr extends BaseDataObjectMgr {
    /** The {@link EmployeeRoleMgr} */
    private static EmployeeRoleMgr employeeRoleMgr = EmployeeRoleMgr.getInstance();

    /** The singleton instance. */
    private static ServiceMgr instance;

    /** The {@link PitchMgr} */
    private static PitchMgr pitchMgr = PitchMgr.getInstance();

    /** The {@link SiteMgr} */
    private static SiteMgr siteMgr = SiteMgr.getInstance();

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized ServiceMgr getInstance() {
	if (instance == null) {
	    instance = new ServiceMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private ServiceMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Service().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#evenUpdateInUse()
     */
    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getLogger()
     */
    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getSubMgr()
     */
    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(employeeRoleMgr);
	subMgr.add(pitchMgr);
	subMgr.add(siteMgr);
	return subMgr;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#map2DataObject(java.util.HashMap)
     */
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
}