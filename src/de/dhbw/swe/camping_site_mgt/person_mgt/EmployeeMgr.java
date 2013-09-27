package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.*;

public class EmployeeMgr {
    /**
     * If the password is incorrect five times consecutively the user will be
     * blocked.
     * 
     * @return
     */
    public boolean block() {
	return false;
    }

    /**
     * Creates an {@link Employee}.
     * 
     * @param employee
     *            is the entry
     * @return
     */
    public boolean create(final Employee employee) {
	return false;
    }

    /**
     * Creates an {@link Employee}.
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
     * @param userName
     *            is the username of the {@link Person}s user
     * @param password
     *            is the password of the {@link Person}s user
     * @param chipCard
     *            is the {@link ChipCard} that the {@link Person} has
     * @param role
     *            is the {@link EmployeeRole}
     * @return
     */
    public boolean create(final int identificationNumber, final String name,
	    final String firstName, final Date dateOfBirth,
	    final Address principalResidence, final String userName,
	    final String password, final ChipCard chipCard, final EmployeeRole role) {
	return false;
    }

    /**
     * Creates an {@link Employee}.
     * 
     * @param person
     *            is the {@link Person}
     * @param userName
     *            is the username of the {@link Person}s user
     * @param password
     *            is the password of the {@link Person}s user
     * @param chipCard
     *            is the {@link ChipCard} that the {@link Person} has
     * @param role
     *            is the {@link EmployeeRole}
     * 
     * @return
     */
    public boolean create(final Person person, final String userName,
	    final String password, final ChipCard chipCard, final EmployeeRole role) {
	return false;
    }

    /**
     * Deletes the given {@link Employee}.
     * 
     * @param employee
     *            is the entry
     * @return
     */
    public boolean delete(final Employee employee) {
	return false;
    }

    /**
     * Deletes the entry with the given ID.
     * 
     * @param identificationNumber
     *            is the ID of the {@link Person}
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
     * @param userName
     *            is the username of the {@link Person}s user
     * @param password
     *            is the password of the {@link Person}s user
     * @param chipCard
     *            is the {@link ChipCard} that the {@link Person} has
     * @param role
     *            is the {@link EmployeeRole}
     * @return
     */
    public boolean edit(final int identificationNumber, final String name,
	    final String firstName, final Date dateOfBirth,
	    final Address principalResidence, final String userName,
	    final String password, final ChipCard chipCard, final EmployeeRole role) {
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
     *            is the ID of the enty
     * @return
     */
    public Employee search(final int identificationNumber) {
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
     * @param userName
     *            is the username of the {@link Person}s user
     * @param password
     *            is the password of the {@link Person}s user
     * @param chipCard
     *            is the {@link ChipCard} that the {@link Person} has
     * @param role
     *            is the {@link EmployeeRole}
     * @return
     */
    public EmployeeList search(final String name, final String firstName,
	    final Date dateOfBirth, final Address principalResidence,
	    final String userName, final String password, final ChipCard chipCard,
	    final EmployeeRole role) {
	return null;
    }

    /**
     * Unblocks the user.
     * 
     * @return
     */
    public boolean unblock() {
	return false;
    }

    private EmployeeList employee;

}
