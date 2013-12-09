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

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.DurationMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

/**
 * The manager class for the {@link ExtraBooking} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class BookingExtraBookingMgr extends BaseDataObjectMgr {

    /** The {@link SiteMgr}. */
    private ExtraBookingMgr extraBookingMgr;
    private DurationMgr durationMgr;

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theSiteMgr
     *            the {@link SiteMgr}
     */
    public BookingExtraBookingMgr(final AccessableDatabase db, final ExtraBookingMgr theExtraBookingMgr, DurationMgr theDurationMgr) {
	super(db);
	extraBookingMgr = theExtraBookingMgr;
	durationMgr = theDurationMgr;
	load();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new BookingExtraBooking().getTableName();
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
	subMgr.add(extraBookingMgr);
	subMgr.add(durationMgr);
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
	final ExtraBooking extraBooking = (ExtraBooking) map.get("extraBooking");
	final Duration duration = (Duration) map.get("duration");

	return new BookingExtraBooking(id, extraBooking, duration);
    }
}