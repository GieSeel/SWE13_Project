/**
 * Comments for file Usage.java
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

import java.util.HashMap;
import java.util.Vector;

/**
 * Insert description for Usage
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Usage {
    /**
     * Constructor.
     * 
     * @param usage
     */
    public Usage() {
	this.usage = new HashMap<>();
    }

    /**
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void addUsage(final String parentTableName, final int parentID) {
	Vector<Integer> tmpV = new Vector<>();
	if (usage.containsKey(parentTableName)) {
	    tmpV = usage.get(parentTableName);
	    if (!tmpV.contains(parentID)) {
		tmpV.add(parentID);
	    }
	} else {
	    tmpV.add(parentID);
	    usage.put(parentTableName, tmpV);
	}
    }

    /**
     * Deletes entry from usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void delUsage(final String parentTableName, final int parentID) {
	Vector<Integer> tmpV = new Vector<>();
	if (usage.containsKey(parentTableName)) {
	    tmpV = usage.get(parentTableName);
	    if (tmpV.contains(parentID)) {
		tmpV.remove(tmpV.indexOf(parentID));
		if (tmpV.size() == 0) {
		    usage.remove(parentTableName);
		}
	    }
	}
    }

    /**
     * Checks if the object is still in use.
     * 
     * @return true if it's still in use
     */
    public boolean isInUse() {
	if (usage.size() > 0) {
	    return true;
	}
	return false;
    }

    private final HashMap<String, Vector<Integer>> usage;
}
