/**
 * Comments for file EmployeeRoleMgr.java
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
package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link EmployeeRole} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeRoleMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static EmployeeRoleMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EmployeeRoleMgr getInstance() {
	if (instance == null) {
	    instance = new EmployeeRoleMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EmployeeRoleMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new EmployeeRole().getTableName();
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
	return null;
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
	final EmployeeRole_Arrangement arrangement = EmployeeRole_Arrangement.values()[(int) map.get("arrangement")];
	final EmployeeRole_Labeling labeling = EmployeeRole_Labeling.values()[(int) map.get("labeling")];

	return new EmployeeRole(id, arrangement, labeling);
    }
}
