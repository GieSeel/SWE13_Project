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
@Deprecated
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
    public PersonMgr(final AccessableDatabase db, AddressMgr theAddressMgr, final CountryMgr theCountryMgr,
	    final TownMgr theTownMgr) {
	super(db);
	addressMgr = theAddressMgr;
	countryMgr = theCountryMgr;
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
	subMgr.add(addressMgr);
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
	String identification_number = (String) map.get("identification_number");
	String name = (String) map.get("name");
	String first_name = (String) map.get("first_name");
	Date date_of_birth = (Date) map.get("date_of_birth");
	Address address = (Address) map.get("address");
	Town town = (Town) map.get("town");
	Country country = (Country) map.get("country");
	
	return new Person(id, identification_number, name, first_name, date_of_birth, address, town, country);
    }
    
    private AddressMgr addressMgr;


    /** The {@link CountryMgr} */
    private final CountryMgr countryMgr;

    /** The {@link TownMgr} */
    private final TownMgr townMgr;
}