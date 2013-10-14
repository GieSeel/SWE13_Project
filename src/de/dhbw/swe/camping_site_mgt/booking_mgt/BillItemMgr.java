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

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link BillItem} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class BillItemMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static BillItemMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized BillItemMgr getInstance() {
	if (instance == null) {
	    instance = new BillItemMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private BillItemMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link BillItem} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	BillItem_Labeling labeling;
	Euro priceBusySeason;
	Euro priceLowSeason;

	id = (int) entry.get("id");
	labeling = BillItem_Labeling.values()[(int) entry.get("labeling")];
	priceBusySeason = (Euro) entry.get("priceBusySeason");
	priceLowSeason = (Euro) entry.get("priceLowSeason");

	return new BillItem(id, labeling, priceBusySeason, priceLowSeason);
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
	return new BillItem().getTableName();
    }
}