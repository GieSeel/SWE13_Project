package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.Address;
import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Country;
import de.dhbw.swe.camping_site_mgt.common.Town;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Person extends BaseDataObject implements PersonInterface {

    /**
     * Constructor for empty object.
     * 
     */
    public Person() {
    	this(null, null, null, null, null, null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     *            the id
     * @param identification_number
     *            identification number
     * @param name
     *            the name
     * @param first_name
     *            the first name
     * @param date_of_birth
     *            the birth date
     * @param adress
     * 			  the {@link Address}
     * @param town
     *            the {@link Town}
     * @param country
     *            the {@link Country}
     */
    public Person(final int id, final String identification_number,
	    final String name, final String first_name, final Date date_of_birth,
	    final Address address, final Town town,
	    final Country country) {
	super(id);
	this.identification_number = identification_number;
	this.name = name;
	this.first_name = first_name;
	this.date_of_birth = date_of_birth;
	this.address = address;
	this.town = town;
	this.country = country;
    }

    /**
     * Constructor.
     * 
     * @param identification_number
     *            identification number
     * @param name
     *            the name
     * @param first_name
     *            the first name
     * @param date_of_birth
     *            the birth date
     * @param adress
     * 			  the {@link Address}
     * @param town
     *            the {@link Town}
     * @param country
     *            the {@link Country}
     */
    public Person(final String identification_number,
    	    final String name, final String first_name, final Date date_of_birth,
    	    final Address address, final Town town,
    	    final Country country) {
    	this(0, identification_number, name, first_name, date_of_birth, address, town, country);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
//	final Person object = (Person) dataObject;
//	if (this.country.equals(object.getCountry())
//		&& this.dateOfBirth.equals(object.getDateOfBirth())
//		&& this.firstName.equals(object.getFirstName())
//		&& this.houseNumber.equals(object.getHouseNumber())
//		&& this.identificationNumber.equals(object.getIdentificationNumber())
//		&& this.name.equals(object.getName())
//		&& this.street.equals(object.getStreet())
//		&& this.town.equals(object.getTown())) {
//	    setId(object.getId());
//	    setUsage(object.getUsage());
//	    return true;
//	}
	return false;
    }

    /**
	 * @return the identification_number
	 */
	public String getIdentificationNumber() {
		return identification_number;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the first_name
	 */
	public String getFirstName() {
		return first_name;
	}

	/**
	 * @return the date_of_birth
	 */
	public Date getDateOfBirth() {
		return date_of_birth;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the town
	 */
	public Town getTown() {
		return town;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
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

    private final String identification_number;
    private final String name;
    private final String first_name;
    private final Date date_of_birth;
    private final Address address;
    private final Town town;
    private final Country country;
}
