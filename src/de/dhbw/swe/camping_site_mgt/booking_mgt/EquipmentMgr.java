/**
 * Comments for file EquipmentMgr.java
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
 * The manager class for the {@link Equipment} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EquipmentMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static EquipmentMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EquipmentMgr getInstance() {
	if (instance == null) {
	    instance = new EquipmentMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EquipmentMgr() {
	super();
    }

    /**
     * Parses a database entry to an object.
     * 
     * @param entry
     *            the entry
     * @return the prepared {@link Equipment} object
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	String identification;
	String size;
	Equipment_Type type;

	id = (int) entry.get("id");
	identification = (String) entry.get("identification");
	size = (String) entry.get("size");
	type = (Equipment_Type) entry.get("type");

	return new Equipment(id, identification, size, type);
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
	return new Equipment().getTableName();
    }
}