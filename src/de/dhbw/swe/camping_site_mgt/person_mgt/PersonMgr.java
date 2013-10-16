/**
 * Comments for file PersonMgr.java
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

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Person} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PersonMgr extends BaseDataObjectMgr {

    /** The {@link CountryMgr} */
    private static CountryMgr countryMgr = CountryMgr.getInstance();

    /** The singleton instance. */
    private static PersonMgr instance;

    /** The {@link TownMgr} */
    private static TownMgr townMgr = TownMgr.getInstance();

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PersonMgr getInstance() {
	if (instance == null) {
	    instance = new PersonMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PersonMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Person().getTableName();
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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getSubMgr()
     */
    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(countryMgr);
	subMgr.add(townMgr);
	return subMgr;
    }

    /**
     * Converts the map to an {@link DataObject}.
     * 
     * @param map
     *            the map
     * @return the object
     */
    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final Date dateOfBirth = (Date) map.get("dateOfBirth");
	final String firstName = (String) map.get("firstName");
	final String houseNumber = (String) map.get("houseNumber");
	final String identificationNumber = (String) map.get("identificationNumber");
	final String name = (String) map.get("name");
	final String street = (String) map.get("street");

	final Country country = (Country) map.get("country");
	final Town town = (Town) map.get("town");

	return new Person(id, identificationNumber, firstName, name, dateOfBirth,
		street, houseNumber, town, country);
    }
}