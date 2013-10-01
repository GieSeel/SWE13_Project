package de.dhbw.swe.camping_site_mgt.common;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;

public class Address implements DataObject {

    public Address() {
	this(null, null, null);
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

    public String getHouseNumber() {
	return houseNumber;
    }

    @Override
    public int getId() {
	return id;
    }

    @Override
    public String getName() {
	return "address";
    }

    public String getStreet() {
	return street;
    }

    public Town getTown() {
	return town;
    }

    @Override
    public void setDatabaseData(final HashMap<String, Object> elements) {
	for (final ColumnInfo column : DataStructure.getStructureFor(getName())) {
	    if (column.getReleationToColumn() == null) {
		ObjectFieldAccess.setValueOf(column.getColumnName(),
			elements.get(column.getColumnName()), this);
	    } else {
		final Town object = new Town();
		ObjectFieldAccess.querySelect(
			(int) elements.get(column.getColumnName()), object);
		ObjectFieldAccess.setValueOf(column.getColumnName(), object, this);
	    }
	}
    }

    @Override
    public void setDisplayData(final HashMap<String, Object> elements) {
	String key;
	for (final ColumnInfo column : DataStructure.getStructureFor(getName())) {
	    if (column.getReleationToColumn() == null) {
		key = getName() + "_" + column.getColumnName();
		ObjectFieldAccess.setValueOf(column.getColumnName(),
			elements.get(key), this);
	    } else {
		final Town subObject = new Town();
		subObject.setDisplayData(elements);
		ObjectFieldAccess.setValueOf(column.getColumnName(), subObject,
			this);
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
