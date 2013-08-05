package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.Date;

import de.dhbw.swe.campingplatzverwaltung.common.Address;

public class Person {
    public Person(final int id, final Address address, final Date dateOfBirth,
	    final String firstName, final String identificationNumber,
	    final String name) {
	super();
	this.id = id;
	this.address = address;
	this.dateOfBirth = dateOfBirth;
	this.firstName = firstName;
	this.identificationNumber = identificationNumber;
	this.name = name;
    }

    public Address getAddress() {
	return address;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public String getFirstName() {
	return firstName;
    }

    public int getId() {
	return id;
    }

    public String getIdentificationNumber() {
	return identificationNumber;
    }

    public String getName() {
	return name;
    }

    private final Address address;
    private final Date dateOfBirth;
    private final String firstName;
    private final int id;
    private final String identificationNumber;
    private final String name;
}
