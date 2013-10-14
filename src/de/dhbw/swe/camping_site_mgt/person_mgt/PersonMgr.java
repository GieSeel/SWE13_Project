/**
 * Comments for file PersonMgr.java
 *
 * @author   GieSeel
 *
 * Project:    Campingplatz Verwaltung
 * Company:    GieSeel
 * $Revision:  $
 *
 * Unclassified
 *
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ObjectFieldAccess;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Person} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PersonMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static PersonMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PersonMgr getInstance() {
	if (instance == null) {
	    instance = new PersonMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PersonMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Person} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	Country country;
	Date dateOfBirth;
	String firstName;
	String houseNumber;
	String identificationNumber;
	String name;
	String street;
	Town town;

	id = (int) entry.get("id");
	country = (Country) CountryMgr.getInstance().objectGet(
		(int) entry.get("country"), tableName, id);
	dateOfBirth = (Date) entry.get("dateOfBirth");
	firstName = (String) entry.get("firstName");
	houseNumber = (String) entry.get("houseNumber");
	identificationNumber = (String) entry.get("identificationNumber");
	name = (String) entry.get("name");
	street = (String) entry.get("street");
	town = (Town) TownMgr.getInstance().objectGet((int) entry.get("town"),
		tableName, id);

	return new Person(id, identificationNumber, firstName, name, dateOfBirth,
		street, houseNumber, town, country);
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
	return new Person().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Person object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getCountry().addUsage(tableName, id);
	object.getTown().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Person object = castObject(dataObject);

	CountryMgr.getInstance().objectInsert(object.getCountry());
	TownMgr.getInstance().objectInsert(object.getTown());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Person object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Country country = object.getCountry();
	final Town town = object.getTown();
	country.delUsage(tableName, id);
	town.delUsage(tableName, id);
	CountryMgr.getInstance().objectRemove(country);
	TownMgr.getInstance().objectRemove(town);
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
	final Person object = castObject(dataObject);
	final Person newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Country country = object.getCountry();
	final Town town = object.getTown();
	country.delUsage(tableName, id);
	town.delUsage(tableName, id);
	CountryMgr.getInstance().objectUpdate(country, newObject.getCountry());
	TownMgr.getInstance().objectUpdate(town, newObject.getTown());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subSaveDisplayData(java.lang.String,
     *      java.lang.String,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected Object subSaveDisplayData(final String className,
	    final String fieldName, final DataObject object) {
	final Person person = (Person) object;
	if (className.equals("country")) {
	    return ObjectFieldAccess.getValueOf(fieldName, person.getCountry());
	} else if (className.equals("town")) {
	    return ObjectFieldAccess.getValueOf(fieldName, person.getTown());
	} else {
	    return null;
	}
    }

    /**
     * Cast {@link DataObject} to {@link Person} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link Person} object
     */
    private Person castObject(final DataObject dataObject) {
	return (Person) dataObject;
    }
}