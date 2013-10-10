/**
 * Comments for file VisitorsTaxClassMgr.java
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
package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link VisitorsTaxClass} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class VisitorsTaxClassMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static VisitorsTaxClassMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized VisitorsTaxClassMgr getInstance() {
	if (instance == null) {
	    instance = new VisitorsTaxClassMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private VisitorsTaxClassMgr() {
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
	String labeling;
	Euro price;

	id = (int) entry.get("id");
	labeling = (String) entry.get("labeling");
	price = (Euro) entry.get("price");

	return new VisitorsTaxClass(id, labeling, price);
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
	return new VisitorsTaxClass().getTableName();
    }

}
