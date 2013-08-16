package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.Address;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Person {
    public Person(final Address address, final String dateOfBirth,
	    final String firstName, final String identificationNumber,
	    final String name) {
	super();
	this.id = 0;
	this.address = address;
	this.dateOfBirth = new Date(0);
	try {
	    this.dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").parse(dateOfBirth);
	} catch (final ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.firstName = firstName;
	this.identificationNumber = identificationNumber;
	this.name = name;
    }

    public Person(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.address = DatabaseController.getInstance().querySelectAddress(
		(int) elements.get("address_ID"));
	this.dateOfBirth = (Date) elements.get("dateOfBirth");
	this.firstName = (String) elements.get("firstName");
	this.identificationNumber = (String) elements.get("identificationNumber");
	this.name = (String) elements.get("name");
    }

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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.identificationNumber);
	data.add(this.firstName);
	data.add(this.name);
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(dateOfBirth));
	data.addAll(this.address.getData());
	return data;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public String getFirstName() {
	return firstName;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put(
		"address_ID",
		DatabaseController.getInstance().queryInsertUpdateAddress(
			this.address));
	elements.put("dateOfBirth", this.dateOfBirth);
	elements.put("firstName", this.firstName);
	elements.put("identificationNumber", this.identificationNumber);
	elements.put("name", this.name);
	return elements;
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
    private Date dateOfBirth;
    private final String firstName;
    private final int id;
    private final String identificationNumber;
    private final String name;
}
