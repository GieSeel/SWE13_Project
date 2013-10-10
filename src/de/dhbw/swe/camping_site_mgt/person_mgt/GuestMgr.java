package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Guest} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class GuestMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static GuestMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized GuestMgr getInstance() {
	if (instance == null) {
	    instance = new GuestMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private GuestMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	Person person;
	VisitorsTaxClass visitorsTaxClass;

	id = (int) entry.get("id");
	person = (Person) PersonMgr.getInstance().get((int) entry.get("person"));
	visitorsTaxClass = (VisitorsTaxClass) VisitorsTaxClassMgr.getInstance().get(
		(int) entry.get("visitorsTaxClass"));

	return new Guest(id, person, visitorsTaxClass);
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
	return new Guest().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Guest object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getPerson().addUsage(tableName, id);
	object.getVisitorsTaxClass().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Guest object = castObject(dataObject);

	PersonMgr.getInstance().objectInsert(object.getPerson());
	VisitorsTaxClassMgr.getInstance().objectInsert(object.getVisitorsTaxClass());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Guest object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Person person = object.getPerson();
	final VisitorsTaxClass visitorsTaxClass = object.getVisitorsTaxClass();
	person.delUsage(tableName, id);
	visitorsTaxClass.delUsage(tableName, id);
	PersonMgr.getInstance().objectRemove(person);
	VisitorsTaxClassMgr.getInstance().objectRemove(visitorsTaxClass);
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
	final Guest object = castObject(dataObject);
	final Guest newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Person person = object.getPerson();
	final VisitorsTaxClass visitorsTaxClass = object.getVisitorsTaxClass();
	person.delUsage(tableName, id);
	visitorsTaxClass.delUsage(tableName, id);
	PersonMgr.getInstance().objectUpdate(person, newObject.getPerson());
	VisitorsTaxClassMgr.getInstance().objectUpdate(visitorsTaxClass,
		newObject.getVisitorsTaxClass());
    }

    /**
     * Cast {@link DataObject} to {@link Guest} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link Guest} object
     */
    private Guest castObject(final DataObject dataObject) {
	return (Guest) dataObject;
    }
}
