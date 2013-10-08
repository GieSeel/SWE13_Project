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

    // ############################################################
    // public HashMap<String, Object> getDatabaseData() {
    // final HashMap<String, Object> elements = new HashMap<String, Object>();
    // elements.put("id", this.id);
    // elements.put(
    // "address_ID",
    // DatabaseController.getInstance().queryInsertUpdateAddress(
    // this.address));
    // elements.put("dateOfBirth", this.dateOfBirth);
    // elements.put("firstName", this.firstName);
    // elements.put("identificationNumber", this.identificationNumber);
    // elements.put("name", this.name);
    // return elements;
    // }
    //
    // public HashMap<String, Object> getTableData(final String parentClass) {
    // final HashMap<String, Object> objects = new HashMap<String, Object>();
    // final String className = parentClass + "person_";
    // objects.put(className + "id", new Integer(this.id));
    // objects.put(className + "identificationNumber", new String(
    // this.identificationNumber));
    // objects.put(className + "firstName", new String(this.firstName));
    // objects.put(className + "name", new String(this.name));
    // objects.put(className + "dateOfBirth", new String(new SimpleDateFormat(
    // "dd.MM.yyyy").format(this.dateOfBirth)));
    // objects.putAll(this.address.getTableData(className));
    // return objects;
    // }
    //
    // public Person setDatabaseData(final HashMap<String, Object> objects) {
    // final DatabaseController db = DatabaseController.getInstance();
    // this.address = db.querySelectAddress((int) objects.get("address_ID"));
    // setData(objects);
    // return this;
    // }
    //
    // public Person setTableData(final HashMap<String, Object> objects) {
    // final String className = "person_";
    // final int classNameLength = className.length();
    // final HashMap<String, Object> thisMap = new HashMap<String, Object>(),
    // addressMap = new HashMap<String, Object>();
    //
    // Object val;
    // final Set<String> keys = objects.keySet();
    // for (String key : keys) {
    // val = objects.get(key);
    // key = key.substring(classNameLength);
    // if (key.startsWith("address_")) {
    // addressMap.put(key, val);
    // } else {
    // thisMap.put(key, val);
    // }
    // }
    // this.address = new Address().setTableData(addressMap);
    // setData(thisMap);
    // return this;
    // }
    //
    // private void setData(final HashMap<String, Object> objects) {
    // this.id = (int) objects.get("id");
    // this.dateOfBirth = (Date) objects.get("dateOfBirth");
    // this.firstName = (String) objects.get("firstName");
    // this.identificationNumber = (String) objects.get("identificationNumber");
    // this.name = (String) objects.get("name");
    // }
    // ############################################################
}
