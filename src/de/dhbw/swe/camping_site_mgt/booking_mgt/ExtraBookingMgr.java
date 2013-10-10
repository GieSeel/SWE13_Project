/**
 * Comments for file ExtraBookingMgr.java
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
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;
import de.dhbw.swe.camping_site_mgt.place_mgt.SiteMgr;

/**
 * The manager class for the {@link ExtraBooking} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ExtraBookingMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static ExtraBookingMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized ExtraBookingMgr getInstance() {
	if (instance == null) {
	    instance = new ExtraBookingMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private ExtraBookingMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link ExtraBooking} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	String labeling;
	String name;
	Site site;

	id = (int) entry.get("id");
	labeling = (String) entry.get("labeling");
	name = (String) entry.get("name");
	site = (Site) SiteMgr.getInstance().objectGet((int) entry.get("site"),
		tableName, id);

	return new ExtraBooking(id, labeling, name, site);
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
	return new ExtraBooking().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final ExtraBooking object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getSite().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final ExtraBooking object = castObject(dataObject);

	SiteMgr.getInstance().objectInsert(object.getSite());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final ExtraBooking object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Site site = object.getSite();
	site.delUsage(tableName, id);
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
	final ExtraBooking object = castObject(dataObject);
	final ExtraBooking newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Site site = object.getSite();
	site.delUsage(tableName, id);
	SiteMgr.getInstance().objectUpdate(site, newObject.getSite());
    }

    /**
     * Cast {@link DataObject} to {@link ExtraBooking} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link ExtraBooking} object
     */
    private ExtraBooking castObject(final DataObject dataObject) {
	return (ExtraBooking) dataObject;
    }
}