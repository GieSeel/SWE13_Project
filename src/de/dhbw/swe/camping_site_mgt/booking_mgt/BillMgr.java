/**
 * Comments for file BillMgr.java
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
package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Bill} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class BillMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static BillMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized BillMgr getInstance() {
	if (instance == null) {
	    instance = new BillMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private BillMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Bill} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	BillItem billItem;
	int multiplier;
	int number;

	id = (int) entry.get("id");
	billItem = (BillItem) BillItemMgr.getInstance().objectGet(
		(int) entry.get("billItem"), tableName, id);
	multiplier = (int) entry.get("multiplier");
	number = (int) entry.get("number");

	return new Bill(id, number, billItem, multiplier);
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
	return new Bill().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Bill object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getBillItem().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Bill object = castObject(dataObject);

	BillItemMgr.getInstance().objectInsert(object.getBillItem());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Bill object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final BillItem billItem = object.getBillItem();
	billItem.delUsage(tableName, id);
	BillItemMgr.getInstance().objectRemove(billItem);
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
	final Bill object = castObject(dataObject);
	final Bill newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final BillItem billItem = object.getBillItem();
	billItem.delUsage(tableName, id);
	BillItemMgr.getInstance().objectUpdate(billItem, newObject.getBillItem());
    }

    /**
     * Cast {@link DataObject} to {@link Bill} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link Bill} object
     */
    private Bill castObject(final DataObject dataObject) {
	return (Bill) dataObject;
    }
}