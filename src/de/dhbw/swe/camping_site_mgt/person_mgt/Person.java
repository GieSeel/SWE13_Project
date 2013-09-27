package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.Address;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Person {

    public Person() {
	super();
	this.id = 0;
	this.address = null;
	this.dateOfBirth = null;
	this.firstName = null;
	this.identificationNumber = null;
	this.name = null;
    }

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

    public HashMap<String, Object> getDatabaseData() {
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "person_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "identificationNumber", new String(
		this.identificationNumber));
	objects.put(className + "firstName", new String(this.firstName));
	objects.put(className + "name", new String(this.name));
	objects.put(className + "dateOfBirth", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(this.dateOfBirth)));
	objects.putAll(this.address.getTableData(className));
	return objects;
    }

    public Person setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.address = db.querySelectAddress((int) objects.get("address_ID"));
	setData(objects);
	return this;
    }

    public Person setTableData(final HashMap<String, Object> objects) {
	final String className = "person_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), addressMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("address_")) {
		addressMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.address = new Address().setTableData(addressMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.dateOfBirth = (Date) objects.get("dateOfBirth");
	this.firstName = (String) objects.get("firstName");
	this.identificationNumber = (String) objects.get("identificationNumber");
	this.name = (String) objects.get("name");
    }

    private Address address;
    private Date dateOfBirth;
    private String firstName;
    private int id;
    private String identificationNumber;
    private String name;
}
