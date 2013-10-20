/**
 * Comments for file BillItemMgr.java
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

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link BillItem} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class BillItemMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     */
    public BillItemMgr(final AccessableDatabase db) {
	super(db);
	load();
    }

    @Override
    public String getTableName() {
	return new BillItem().getTableName();
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
	return null;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	// TODO Array vs. ordinal
	final BillItem_Labeling labeling;
	final int labelingOrdinal = (int) map.get("labeling");
	labeling = BillItem_Labeling.values()[labelingOrdinal - 1];

	final Euro priceBusySeason = (Euro) map.get("priceBusySeason");
	final Euro priceLowSeason = (Euro) map.get("priceLowSeason");

	return new BillItem(id, labeling, priceBusySeason, priceLowSeason);
    }
}