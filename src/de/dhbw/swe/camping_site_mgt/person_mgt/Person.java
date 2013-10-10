package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Person extends BaseDataObject implements PersonInterface {

    /**
     * Constructor for empty object.
     * 
     */
    public Person() {
	this(null, null, null, null, null, null, new Town(), new Country());
    }

    /**
     * Constructor.
     * 
     * @param id
     *            the id
     * @param identificationNumber
     *            identification number
     * @param firstName
     *            the first name
     * @param name
     *            the name
     * @param dateOfBirth
     *            the birth date
     * @param street
     *            the street
     * @param houseNumber
     *            the house number
     * @param town
     *            the {@link Town}
     * @param country
     *            the {@link Country}
     */
    public Person(final int id, final String identificationNumber,
	    final String firstName, final String name, final Date dateOfBirth,
	    final String street, final String houseNumber, final Town town,
	    final Country country) {
	super(id);
	this.identificationNumber = identificationNumber;
	this.firstName = firstName;
	this.name = name;
	this.dateOfBirth = dateOfBirth;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
	this.country = country;
    }

    /**
     * Constructor.
     * 
     * @param identificationNumber
     *            identification number
     * @param firstName
     *            the first name
     * @param name
     *            the name
     * @param dateOfBirth
     *            the birth date
     * @param street
     *            the street
     * @param houseNumber
     *            the house number
     * @param town
     *            the {@link Town}
     * @param country
     *            the {@link Country}
     */
    public Person(final String identificationNumber, final String firstName,
	    final String name, final Date dateOfBirth, final String street,
	    final String houseNumber, final Town town, final Country country) {
	this(0, identificationNumber, firstName, name, dateOfBirth, street,
		houseNumber, town, country);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Person object = (Person) dataObject;
	if (this.country.equals(object.getCountry())
		&& this.dateOfBirth.equals(object.getDateOfBirth())
		&& this.firstName.equals(object.getFirstName())
		&& this.houseNumber.equals(object.getHouseNumber())
		&& this.identificationNumber.equals(object.getIdentificationNumber())
		&& this.name.equals(object.getName())
		&& this.street.equals(object.getStreet())
		&& this.town.equals(object.getTown())) {
	    setId(object.getId());
	    setUsage(object.getUsage());
	    return true;
	}
	return false;
    }

    /**
     * Returns the country.
     * 
     * @return the country
     */
    @Override
    public Country getCountry() {
	return country;
    }

    /**
     * Returns the dateOfBirth.
     * 
     * @return the dateOfBirth
     */
    @Override
    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    /**
     * Returns the firstName.
     * 
     * @return the firstName
     */
    @Override
    public String getFirstName() {
	return firstName;
    }

    /**
     * Returns the houseNumber.
     * 
     * @return the houseNumber
     */
    @Override
    public String getHouseNumber() {
	return houseNumber;
    }

    /**
     * Returns the identificationNumber.
     * 
     * @return the identificationNumber
     */
    @Override
    public String getIdentificationNumber() {
	return identificationNumber;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    @Override
    public String getName() {
	return name;
    }

    /**
     * Returns the street.
     * 
     * @return the street
     */
    @Override
    public String getStreet() {
	return street;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "person";
    }

    /**
     * Returns the town.
     * 
     * @return the town
     */
    @Override
    public Town getTown() {
	return town;
    }

    private final Country country;

    private final Date dateOfBirth;

    private final String firstName;

    private final String houseNumber;

    private final String identificationNumber;

    private final String name;

    private final String street;

    private final Town town;
}
