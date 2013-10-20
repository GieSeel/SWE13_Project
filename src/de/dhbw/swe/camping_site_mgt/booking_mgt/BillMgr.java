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
 * Copyright � since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Bill} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class BillMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theBillItemMgr
     *            the {@link BillItemMgr}
     */
    public BillMgr(final AccessableDatabase db, final BillItemMgr theBillItemMgr) {
	super(db);
	billItemMgr = theBillItemMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Bill().getTableName();
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
	subMgr.add(billItemMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final int multiplier = (int) map.get("multiplier");
	final int number = (int) map.get("number");

	final BillItem billItem = (BillItem) map.get("billItem");

	return new Bill(id, number, billItem, multiplier);
    }

    private final BillItemMgr billItemMgr;
}