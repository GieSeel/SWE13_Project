package de.dhbw.swe.campingplatzverwaltung.common;

import java.text.*;
import java.util.*;

public class ChipCard {
    public ChipCard(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.validFrom = (Date) elements.get("validFrom");
	this.validTo = (Date) elements.get("validTo");
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(validFrom));
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(validTo));
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put("validFrom", this.validFrom);
	elements.put("validTo", this.validTo);
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Date getValidFrom() {
	return validFrom;
    }

    public Date getValidTo() {
	return validTo;
    }

    private final int id;
    private final int number;
    private Date validFrom;
    private Date validTo;
}
