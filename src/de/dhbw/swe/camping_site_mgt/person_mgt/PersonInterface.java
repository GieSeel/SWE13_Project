package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.*;

public interface PersonInterface {
    /**
     * Returns the country.
     * 
     * @return the country
     */
    public Country getCountry();

    /**
     * Returns the dateOfBirth.
     * 
     * @return the dateOfBirth
     */
    public Date getDateOfBirth();

    /**
     * Returns the firstName.
     * 
     * @return the firstName
     */
    public String getFirstName();

    /**
     * Returns the houseNumber.
     * 
     * @return the houseNumber
     */
    public String getHouseNumber();

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId();

    /**
     * Returns the identificationNumber.
     * 
     * @return the identificationNumber
     */
    public String getIdentificationNumber();

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName();

    /**
     * Returns the street.
     * 
     * @return the street
     */
    public String getStreet();

    /**
     * Returns the town.
     * 
     * @return the town
     */
    public Town getTown();

    /**
     * Returns the usage.
     * 
     * @return the usage
     */
    public Usage getUsage();

    /**
     * Checks if the object is still in use.
     * 
     * @return true if it's still in use
     */
    public boolean isInUse();
}
