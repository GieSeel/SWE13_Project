package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Employee} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static EmployeeMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EmployeeMgr getInstance() {
	if (instance == null) {
	    instance = new EmployeeMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EmployeeMgr() {
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	boolean blocked;
	ChipCard chipCard;
	String password;
	Person person;
	EmployeeRole role;
	String userName;

	id = (int) entry.get("id");
	blocked = ((int) entry.get("blocked") == 1 ? true : false);
	chipCard = (ChipCard) ChipCardMgr.getInstance().get(
		(int) entry.get("chipCard"));
	password = (String) entry.get("password");
	person = (Person) PersonMgr.getInstance().get((int) entry.get("person"));
	role = (EmployeeRole) EmployeeRoleMgr.getInstance().get(
		(int) entry.get("role"));
	userName = (String) entry.get("userName");

	return new Employee(id, blocked, chipCard, password, person, role, userName);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#evenUpdateInUse()
     */
    @Override
    protected boolean evenUpdateInUse() {
	return true;
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
	return new Employee().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Employee object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getChipCard().addUsage(tableName, id);
	object.getPerson().addUsage(tableName, id);
	object.getRole().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Employee object = castObject(dataObject);

	ChipCardMgr.getInstance().objectInsert(object.getChipCard());
	PersonMgr.getInstance().objectInsert(object.getPerson());
	EmployeeRoleMgr.getInstance().objectInsert(object.getRole());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Employee object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final ChipCard chipCard = object.getChipCard();
	final Person person = object.getPerson();
	final EmployeeRole employeeRole = object.getRole();
	chipCard.delUsage(tableName, id);
	person.delUsage(tableName, id);
	employeeRole.delUsage(tableName, id);
	ChipCardMgr.getInstance().objectRemove(chipCard);
	PersonMgr.getInstance().objectRemove(person);
	EmployeeRoleMgr.getInstance().objectRemove(employeeRole);
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
	final Employee object = castObject(dataObject);
	final Employee newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final ChipCard chipCard = object.getChipCard();
	final Person person = object.getPerson();
	final EmployeeRole employeeRole = object.getRole();
	chipCard.delUsage(tableName, id);
	person.delUsage(tableName, id);
	employeeRole.delUsage(tableName, id);
	ChipCardMgr.getInstance().objectUpdate(chipCard, newObject.getChipCard());
	PersonMgr.getInstance().objectUpdate(person, newObject.getPerson());
	EmployeeRoleMgr.getInstance().objectUpdate(employeeRole,
		newObject.getRole());
    }

    /**
     * Cast {@link DataObject} to {@link Employee} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link Employee} object
     */
    private Employee castObject(final DataObject dataObject) {
	return (Employee) dataObject;
    }
}
