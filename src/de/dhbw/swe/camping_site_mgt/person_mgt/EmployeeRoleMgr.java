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
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link EmployeeRole} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeRoleMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     */
    public EmployeeRoleMgr(final AccessableDatabase db) {
	super(db);
	load();
    }

    @Override
    public String getTableName() {
	return new EmployeeRole().getTableName();
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
	final int arrangementOrdinal = (int) map.get("arrangement");
	final EmployeeRole_Arrangement arrangement;
	arrangement = EmployeeRole_Arrangement.values()[arrangementOrdinal];

	final EmployeeRole_Labeling labeling;
	final int labelingOrdinal = (int) map.get("labeling");
	labeling = EmployeeRole_Labeling.values()[labelingOrdinal];

	return new EmployeeRole(id, arrangement, labeling);
    }
}
