/**
 * Comments for file PitchBookingMgr.java
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
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchMgr;

/**
 * The manager class for the {@link PitchBooking} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PitchBookingMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static PitchBookingMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PitchBookingMgr getInstance() {
	if (instance == null) {
	    instance = new PitchBookingMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PitchBookingMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link PitchBooking} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	boolean electricity;
	Pitch pitch;

	id = (int) entry.get("id");
	electricity = (boolean) entry.get("electricity");
	pitch = (Pitch) PitchMgr.getInstance().objectGet((int) entry.get("pitch"),
		tableName, id);

	return new PitchBooking(id, electricity, pitch);
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
	return new PitchBooking().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final PitchBooking object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getPitch().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final PitchBooking object = castObject(dataObject);

	PitchMgr.getInstance().objectInsert(object.getPitch());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final PitchBooking object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Pitch pitch = object.getPitch();
	pitch.delUsage(tableName, id);
	PitchMgr.getInstance().objectRemove(pitch);
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
	final PitchBooking object = castObject(dataObject);
	final PitchBooking newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Pitch pitch = object.getPitch();
	pitch.delUsage(tableName, id);
	PitchMgr.getInstance().objectUpdate(pitch, newObject.getPitch());
    }

    /**
     * Cast {@link DataObject} to {@link PitchBooking} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link PitchBooking} object
     */
    private PitchBooking castObject(final DataObject dataObject) {
	return (PitchBooking) dataObject;
    }
}