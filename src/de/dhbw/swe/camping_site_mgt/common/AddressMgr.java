/**
 * Comments for file AddressMgr.java
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

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseMgr;

/**
 * The manager class for the {@link Address} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
/**
 * Insert description for AddressMgr
 * 
 * @author GieSeel
 * @version 1.0
 */
public class AddressMgr {

    /** The singleton instance. */
    private static AddressMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized AddressMgr getInstance() {
	if (instance == null) {
	    instance = new AddressMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private AddressMgr() {
	addresses = new HashMap<>();
	loadFromDatabase();
    }

    /**
     * Adds the object to the object list.
     * 
     * @param address
     *            the {@link Address} object
     */
    public void add(final Address address) {
	addresses.put(address.getId(), address);
    }

    /**
     * Checks if the object already exists. <br />
     * If not, then it will be saved in the object list and the database.
     * 
     * @param address
     *            the {@link Address} object
     */
    public void checkIfObjectAlreadyExists(final Address address) {
	final String street = address.getStreet();
	final String houseNumber = address.getHouseNumber();
	final Town town = address.getTown();
	TownMgr.getInstance().checkIfObjectAlreadyExists(town);

	for (final Address tmpAddress : addresses.values()) {
	    if (street.equals(tmpAddress.getStreet())
		    && houseNumber.equals(tmpAddress.getHouseNumber())
		    && town.getId() == tmpAddress.getTown().getId()) {
		address.setId(tmpAddress.getId());
		return;
	    }
	}
	// If Object is not existing then add to object list and database
	saveObjectInDatabase(address);
	add(address);
    }

    /**
     * Gets the object with the given id.
     * 
     * @param id
     *            the object id
     * @return the {@link Address}
     */
    public Address get(final int id) {
	if (addresses.containsKey(id)) {
	    return addresses.get(id);
	}
	return null;
    }

    /**
     * Removes the object from the object list.
     * 
     * @param address
     *            the {@link Address} object
     * @return
     */
    public boolean remove(final Address address) {
	final int id = address.getId();
	if (addresses.containsKey(id)) {
	    addresses.remove(id);
	    return true;
	}
	return false;
    }

    /**
     * Loads the objects from the database.
     */
    private void loadFromDatabase() {
	int id;
	String street, houseNumber;
	Town town;

	for (final HashMap<String, Object> dbAddress : DatabaseMgr.getInstance().getAllEntriesOf(
		"address")) {
	    id = (int) dbAddress.get("id");
	    street = (String) dbAddress.get("street");
	    houseNumber = (String) dbAddress.get("houseNumber");
	    town = TownMgr.getInstance().get((int) dbAddress.get("town"));

	    add(new Address(id, street, houseNumber, town));
	}
	// TODO del-test
	checkIfObjectAlreadyExists(new Address("Neue Straﬂe", "5", new Town(
		new Country("DE", "Deutschland"), "Karlsruhe", "76131")));
	// TODO del-test
    }

    /**
     * Saves the object in database.
     * 
     * @param address
     *            the {@link Address} object
     */
    private void saveObjectInDatabase(final Address address) {
	final HashMap<String, Object> dbAddress = new HashMap<>();
	dbAddress.put("id", address.getId());
	dbAddress.put("street", address.getStreet());
	dbAddress.put("houseNumber", address.getHouseNumber());
	dbAddress.put("town", address.getTown().getId());

	final int id = DatabaseMgr.getInstance().saveEntryTo("address", dbAddress);
	address.setId(id);
    }

    private final HashMap<Integer, Address> addresses;
}
