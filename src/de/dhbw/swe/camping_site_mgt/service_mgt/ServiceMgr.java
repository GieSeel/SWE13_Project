package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.Date;
import java.util.HashMap;

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
    /** The singleton instance. */
    private static ServiceMgr instance;

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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	Date creationDate;
	String description;
	Date doneDate;
	EmployeeRole employeeRole;
	Pitch pitch;
	int priority;
	int serviceNumber;
	Site site;

	id = (int) entry.get("id");
	creationDate = (Date) entry.get("creationDate");
	description = (String) entry.get("description");
	doneDate = (Date) entry.get("doneDate");
	employeeRole = (EmployeeRole) EmployeeRoleMgr.getInstance().objectGet(
		(int) entry.get("employeeRole"), tableName, id);
	pitch = (Pitch) PitchMgr.getInstance().objectGet((int) entry.get("pitch"),
		tableName, id);
	priority = (int) entry.get("priority");
	serviceNumber = (int) entry.get("serviceNumber");
	site = (Site) SiteMgr.getInstance().objectGet((int) entry.get("site"),
		tableName, id);

	return new Service(id, creationDate, description, doneDate, employeeRole,
		pitch, priority, serviceNumber, site);
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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    protected String getTableName() {
	return new Service().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Service object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getEmployeeRole().addUsage(tableName, id);
	object.getPitch().addUsage(tableName, id);
	object.getSite().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Service object = castObject(dataObject);

	EmployeeRoleMgr.getInstance().objectInsert(object.getEmployeeRole());
	PitchMgr.getInstance().objectInsert(object.getPitch());
	SiteMgr.getInstance().objectInsert(object.getSite());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Service object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final EmployeeRole employeeRole = object.getEmployeeRole();
	final Pitch pitch = object.getPitch();
	final Site site = object.getSite();
	employeeRole.delUsage(tableName, id);
	pitch.delUsage(tableName, id);
	site.delUsage(tableName, id);
	EmployeeRoleMgr.getInstance().objectRemove(employeeRole);
	PitchMgr.getInstance().objectRemove(pitch);
	SiteMgr.getInstance().objectRemove(site);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectUpdate(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectUpdate(final DataObject dataObject,
	    final DataObject newDataObject) {
	final Service object = castObject(dataObject);
	final Service newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final EmployeeRole employeeRole = object.getEmployeeRole();
	final Pitch pitch = object.getPitch();
	final Site site = object.getSite();
	employeeRole.delUsage(tableName, id);
	pitch.delUsage(tableName, id);
	site.delUsage(tableName, id);
	EmployeeRoleMgr.getInstance().objectUpdate(employeeRole,
		newObject.getEmployeeRole());
	PitchMgr.getInstance().objectUpdate(pitch, newObject.getPitch());
	SiteMgr.getInstance().objectUpdate(site, newObject.getSite());
    }

    /**
     * 
     * @param dataObject
     * @return
     */
    private Service castObject(final DataObject dataObject) {
	return (Service) dataObject;
    }
}