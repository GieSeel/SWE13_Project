/**
 * Comments for file ChipCardMgr.java
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
package de.dhbw.swe.camping_site_mgt.common;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Chipcard} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ChipcardMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     */
    public ChipcardMgr(final AccessableDatabase db, DurationMgr theDurationMgr) {
	super(db);
	durationMgr = theDurationMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Chipcard().getTableName();
    }

    @Override
    protected boolean evenUpdateInUse() {
	return true;
    }

    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
    	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
    	subMgr.add(durationMgr);
    	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final Duration duration = (Duration) map.get("duration");

	return new Chipcard(id, duration);
    }
    
    private DurationMgr durationMgr;
}