package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Person implements PersonInterface, DataObject {

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
	this.id = id;
	this.identificationNumber = identificationNumber;
	this.firstName = firstName;
	this.name = name;
	this.dateOfBirth = dateOfBirth;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
	this.country = country;
	this.usage = new Usage();
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
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    @Override
    public void addUsage(final String parentTableName, final int parentID) {
	usage.addUsage(parentTableName, parentID);
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
	usage.delUsage(parentTableName, parentID);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Person object = (Person) obj;
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

    @Override
    public Country getCountry() {
	return country;
    }

    @Override
    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    @Override
    public String getFirstName() {
	return firstName;
    }

    @Override
    public String getHouseNumber() {
	return houseNumber;
    }

    @Override
    public int getId() {
	return id;
    }

    @Override
    public String getIdentificationNumber() {
	return identificationNumber;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public String getStreet() {
	return street;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "person";
    }

    @Override
    public Town getTown() {
	return town;
    }

    @Override
    public Usage getUsage() {
	return usage;
    }

    @Override
    public boolean isInUse() {
	return usage.isInUse();
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    @Override
    public void setId(final int id) {
	this.id = id;
    }

    /**
     * Sets the usage.
     * 
     * @param usage
     *            the usage to set
     */
    public void setUsage(final Usage usage) {
	this.usage = usage;
    }

    private final Country country;
    private final Date dateOfBirth;
    private final String firstName;
    private final String houseNumber;
    private int id;
    private final String identificationNumber;
    private final String name;
    private final String street;
    private final Town town;
    private Usage usage;
}
