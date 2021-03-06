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
 * Copyright � since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.DurationMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
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

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     */
    public PitchBookingMgr(final AccessableDatabase db, PitchMgr thePitchMgr, DurationMgr theDurationMgr) {
	super(db);
	pitchMgr = thePitchMgr;
	durationMgr = theDurationMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new PitchBooking().getTableName();
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
	subMgr.add(pitchMgr);
	subMgr.add(durationMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
//	final boolean electricity = (boolean) map.get("electricity"); // TODO
	final Pitch pitch = (Pitch) map.get("pitch");
	final Duration duration = (Duration) map.get("duration");

	return new PitchBooking(id, pitch, duration);
    }

    /** The {@link PitchMgr}. */
    private PitchMgr pitchMgr;
    private DurationMgr durationMgr;
}