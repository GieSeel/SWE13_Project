package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Address extends BaseDataObject {

    /**
     * Constructor for empty object.
     * 
     */
    public Address() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param street
     * @param houseNumber
     */
    public Address(final int id, final String street, final String houseNumber) {
	super(id);
	this.street = street;
	this.houseNumber = houseNumber;
    }

    /**
     * Constructor.
     * 
     * @param street
     * @param houseNumber
     */
    public Address(final String street, final String houseNumber) {
	this(0, street, houseNumber);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Address object = (Address) dataObject;
	if (this.street.equals(object.getstreet())
		&& this.houseNumber.equals(object.gethouseNumber())) {
	    setId(object.getId());
	    setUsage(object.getUsage());
	    return true;
	}
	return false;
    }

    /**
     * Returns the street.
     * 
     * @return the street
     */
    public String getstreet() {
	return street;
    }

    /**
     * Returns the houseNumber.
     * 
     * @return the houseNumber
     */
    public String gethouseNumber() {
	return houseNumber;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTablehouseNumber()
     */
    @Override
    public String getTableName() {
	return "address";
    }

    private final String street;
    private final String houseNumber;
}
