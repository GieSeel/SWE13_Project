package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

public class GuestMgr {
    /**
     * Creates an {@link Guest}.
     * 
     * @param guest
     *            is the entry
     * @return
     */
    public boolean create(final Guest guest) {
	return false;
    }

    /**
     * Creates an {@link Guest}.
     * 
     * @param identificationNumber
     *            is the ID of the {@link Person}
     * @param name
     *            is the family-name of the {@link Person}
     * @param firstName
     *            is the first-name of the {@link Person}
     * @param dateOfBirth
     *            is the date of birth of the {@link Person}
     * @param principalResidence
     *            is the {@link Address} of the {@link Person}
     * 
     * @param visitorsTaxClass
     *            is the {@link VisitorsTaxClass} of the {@link Person}
     * @return
     */
    public boolean create(final int identificationNumber, final String name,
	    final String firstName, final Date dateOfBirth,
	    // final Address principalResidence,
	    final VisitorsTaxClass visitorsTaxClass) {
	return false;
    }

    /**
     * Creates an {@link Guest}.
     * 
     * @param person
     *            is the {@link Person}
     * @param visitorsTaxClass
     *            is the {@link VisitorsTaxClass} of the {@link Person}
     * @return
     */
    public boolean create(final Person person,
	    final VisitorsTaxClass visitorsTaxClass) {
	return false;
    }

    /**
     * Deletes the given {@link Guest}.
     * 
     * @param guest
     *            is the entry
     * @return
     */
    public boolean delete(final Guest guest) {
	return false;
    }

    /**
     * Deletes the entry with the given ID.
     * 
     * @param identificationNumber
     *            si the number of the {@link Person}
     * @return
     */
    public boolean delete(final int identificationNumber) {
	return false;
    }

    /**
     * Edits an entry.
     * 
     * @param identificationNumber
     *            is the ID of the {@link Person}
     * @param name
     *            is the family-name of the {@link Person}
     * @param firstName
     *            is the first-name of the {@link Person}
     * @param dateOfBirth
     *            is the date of birth of the {@link Person}
     * @param principalResidence
     *            is the {@link Address} of the {@link Person}
     * 
     * @param visitorsTaxClass
     *            is the {@link VisitorsTaxClass} of the {@link Person}
     * @return
     */
    public boolean edit(final int identificationNumber, final String name,
	    final String firstName, final Date dateOfBirth,
	    // final Address principalResidence,
	    final VisitorsTaxClass visitorsTaxClass) {
	return false;
    }

    /**
     * Exports all entries.
     * 
     * @return
     */
    public boolean exportAll() {
	return false;
    }

    /**
     * Imports all entries.
     * 
     * @return
     */
    public boolean importAll() {
	return false;
    }

    /**
     * Searchs for the {@link Person} with the given ID.
     * 
     * @param identificationNumber
     * @return
     */
    public Guest search(final int identificationNumber) {
	return null;
    }

    /**
     * Search for the {@link Person} with the given parameters.
     * 
     * @param name
     *            is the family-name of the {@link Person}
     * @param firstName
     *            is the first-name of the {@link Person}
     * @param dateOfBirth
     *            is the date of birth of the {@link Person}
     * @param principalResidence
     *            is the {@link Address} of the {@link Person}
     * 
     * @param visitorsTaxClass
     *            is the {@link VisitorsTaxClass} of the {@link Person}
     * @return
     */
    public GuestList search(final String name, final String firstName,
	    final Date dateOfBirth,
	    // final Address principalResidence,
	    final VisitorsTaxClass visitorsTaxClass) {
	return null;
    }

    private GuestList guests;
}
