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
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Person} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PersonMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param thecCountryMgr
     *            the {@link CountryMgr}
     * @param theTownMgr
     *            the {@link TownMgr}
     */
    public PersonMgr(final AccessableDatabase db, final CountryMgr thecCountryMgr,
	    final TownMgr theTownMgr) {
	super(db);
	countryMgr = thecCountryMgr;
	townMgr = theTownMgr;
	load(); // TODO alle in baseMgr
    }

    @Override
    public String getTableName() {
	return new Person().getTableName();
    }

    @Override
    protected boolean evenUpdateInUse() {
	return true;
    }

    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(countryMgr);
	subMgr.add(townMgr);
	return subMgr;
    }

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

    /** The {@link CountryMgr} */
    private final CountryMgr countryMgr;

    /** The {@link TownMgr} */
    private final TownMgr townMgr;
}