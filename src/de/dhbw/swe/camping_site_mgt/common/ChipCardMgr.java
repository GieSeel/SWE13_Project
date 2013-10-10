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

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link ChipCard} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class ChipCardMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static ChipCardMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized ChipCardMgr getInstance() {
	if (instance == null) {
	    instance = new ChipCardMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private ChipCardMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	Date validFrom;
	Date validTo;

	id = (int) entry.get("id");
	validFrom = (Date) entry.get("validFrom");
	validTo = (Date) entry.get("validTo");

	return new ChipCard(id, validFrom, validTo);
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
	return new ChipCard().getTableName();
    }

}