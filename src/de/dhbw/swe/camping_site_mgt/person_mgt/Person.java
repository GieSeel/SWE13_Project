package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.Country;
import de.dhbw.swe.camping_site_mgt.common.Town;

public class Person {

    /**
     * Constructor for empty object.
     * 
     */
    public Person() {
	this(new Country(), null, null, null, null, null, null, new Town());
    }

    /**
     * Constructor.
     * 
     * @param country
     * @param dateOfBirth
     * @param firstName
     * @param houseNumber
     * @param identificationNumber
     * @param name
     * @param street
     * @param town
     */
    public Person(final Country country, final Date dateOfBirth,
	    final String firstName, final String houseNumber,
	    final String identificationNumber, final String name,
	    final String street, final Town town) {
	this(0, country, dateOfBirth, firstName, houseNumber, identificationNumber,
		name, street, town);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param country
     * @param dateOfBirth
     * @param firstName
     * @param houseNumber
     * @param identificationNumber
     * @param name
     * @param street
     * @param town
     */
    public Person(final int id, final Country country, final Date dateOfBirth,
	    final String firstName, final String houseNumber,
	    final String identificationNumber, final String name,
	    final String street, final Town town) {
	this.id = id;
	this.country = country;
	this.dateOfBirth = dateOfBirth;
	this.firstName = firstName;
	this.houseNumber = houseNumber;
	this.identificationNumber = identificationNumber;
	this.name = name;
	this.street = street;
	this.town = town;
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
	    return true;
	}
	return false;
    }

    /**
     * Returns the country.
     * 
     * @return the country
     */
    public Country getCountry() {
	return country;
    }

    /**
     * Returns the dateOfBirth.
     * 
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    /**
     * Returns the firstName.
     * 
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * Returns the houseNumber.
     * 
     * @return the houseNumber
     */
    public String getHouseNumber() {
	return houseNumber;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Returns the identificationNumber.
     * 
     * @return the identificationNumber
     */
    public String getIdentificationNumber() {
	return identificationNumber;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the street.
     * 
     * @return the street
     */
    public String getStreet() {
	return street;
    }

    /**
     * Returns the town.
     * 
     * @return the town
     */
    public Town getTown() {
	return town;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
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
}
