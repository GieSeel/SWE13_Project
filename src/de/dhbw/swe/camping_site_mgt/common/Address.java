package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;

public class Address implements DataObject {

    public static final String NAME = "address";

    public Address() {
	this(null, null, new Town());
    }

    public Address(final int id, final String street, final String houseNumber,
	    final Town town) {
	this.id = id;
	this.street = street;
	this.houseNumber = houseNumber;
	this.town = town;
    }

    public Address(final String street, final String houseNumber, final Town town) {
	this(0, street, houseNumber, town);
    }

    @Override
    public boolean allreadyExists(final HashMap<String, Object> elements) {
	
	// Alle Attribute auf einstimmung prüfen
	if(this.houseNumber.equals(elements.get("houseNumber"))&&this.street.equals(elements.get("street")) && ) {
	    
	}
	

	final Address dataObj = (Address) dataObject;

	// Zuerst alle unterobjekte ggf. auch in datenbank einfügen oder updaten
	
	DataObjectMethods.insertOrUpdateDatabase(this.getTown());

	// Dann pruefen ob attribute übereinstimmen
	if (this.houseNumber.equals(dataObj.getHouseNumber())
		&& this.street.equals(dataObj.getStreet())) {
	    // Ist das Objekt gleich wird die ID gespeichert
	    this.setId(dataObj.getId());
	    return true;
	}
	return false;

	/*
	 * TODO boolean exists = true; for (final ColumnInfo column :
	 * DataStructure.getStructureFor(getName())) { // Do not compare the id
	 * field if(!column.getFieldName().equals("id")) {
	 * if(column.getReleationToColumn() == null) { if(exists &&
	 * !ObjectFieldAccess.getValueOf(column.getFieldName(),
	 * this).equals(ObjectFieldAccess.getValueOf(column.getFieldName(),
	 * dataObject))) { exists = false; } } else { if
	 * (column.getReleationToColumnName().equals("town")) {
	 * if(DataObjectMethods
	 * .insertOrUpdateDatabase(ObjectFieldAccess.getValueOf
	 * (column.getFieldName(), this))); } } }
	 */
    }

    public String getHouseNumber() {
	return houseNumber;
    }

    @Override
    public int getId() {
	return id;
    }

    @Override
    public String getName() {
	return getName();
    }

    public String getStreet() {
	return street;
    }

    public Town getTown() {
	return town;
    }

    // @Override
    // public void setDatabaseData(final HashMap<String, Object> elements) {
    // for (final ColumnInfo column : DataStructure.getStructureFor(getName()))
    // {
    // if (column.getReleationToColumn() == null) {
    // ObjectFieldAccess.setValueOf(column.getFieldName(),
    // elements.get(column.getFieldName()), this);
    // } else {
    // Object object = null;
    // // TODO
    // // Class<? extends Object> type = column.getReleationToColumn();
    // // try {
    // // object = type.getConstructor().newInstance();
    // // } catch (InstantiationException | IllegalAccessException
    // // | IllegalArgumentException | InvocationTargetException
    // // | NoSuchMethodException | SecurityException e) {
    // // // TODO logger
    // // }
    //
    // if (column.getReleationToColumnName().equals("town")) {
    // object = new Town();
    // }
    // if (object != null) {
    // ObjectFieldAccess.querySelect(
    // (int) elements.get(column.getFieldName()), object);
    // ObjectFieldAccess.setValueOf(column.getFieldName(), object,
    // this);
    // }
    // }
    // }
    // }

    @Override
    public void setDisplayData(final HashMap<String, Object> elements) {
	String key;
	for (final ColumnInfo column : DataStructure.getStructureFor(getName())) {
	    if (column.getReleationToColumn() == null) {
		key = getName() + "_" + column.getFieldName();
		ObjectFieldAccess.setValueOf(column.getFieldName(),
			elements.get(key), this);
	    } else {
		final Town subObject = new Town();
		subObject.setDisplayData(elements);
		ObjectFieldAccess.setValueOf(column.getFieldName(), subObject, this);
	    }
	}
    }

    public void setId(final int id) {
	this.id = id;
    }

    private final String houseNumber;
    private int id;
    private final String street;
    private final Town town;
}
