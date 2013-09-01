package de.dhbw.swe.campingplatzverwaltung.common;

import java.text.*;
import java.util.*;

public class ChipCard {
    public ChipCard() {
	super();
	this.id = 0;
	this.number = 0;
	this.validFrom = null;
	this.validTo = null;
    }

    public ChipCard(final int id, final int number, final Date validFrom,
	    final Date validTo) {
	super();
	this.id = id;
	this.number = number;
	this.validFrom = validFrom;
	this.validTo = validTo;
    }

    public ChipCard(final int number, final String validFrom, final String validTo) {
	super();
	this.id = 0;
	this.number = number;
	this.validFrom = new Date(0);
	this.validTo = new Date(0);
	try {
	    this.validFrom = new SimpleDateFormat("dd.MM.yyyy").parse(validFrom);
	    this.validTo = new SimpleDateFormat("dd.MM.yyyy").parse(validTo);
	} catch (final ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put("validFrom", this.validFrom);
	objects.put("validTo", this.validTo);
	return objects;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "chipcard_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));
	objects.put(className + "validFrom", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(validFrom)));
	objects.put(className + "validTo", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(validTo)));

	return objects;
    }

    public Date getValidFrom() {
	return validFrom;
    }

    public Date getValidTo() {
	return validTo;
    }

    public ChipCard setDatabaseData(final HashMap<String, Object> objects) {
	setData(objects);
	return this;
    }

    public ChipCard setTableData(final HashMap<String, Object> objects) {
	final String className = "chipcard_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    thisMap.put(key, val);
	}
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
	this.validFrom = (Date) objects.get("validFrom");
	this.validTo = (Date) objects.get("validTo");
    }

    private int id;
    private int number;
    private Date validFrom;
    private Date validTo;
}
