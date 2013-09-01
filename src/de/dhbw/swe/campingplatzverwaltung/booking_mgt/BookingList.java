package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class BookingList {

    public BookingList() {
	super();
	this.id = 0;
	this.booking = null;
	this.number = 0;
    }

    public BookingList(final Booking booking, final int number) {
	super();
	this.id = 0;
	this.booking = booking;
	this.number = number;
    }

    public BookingList(final int id, final Booking booking, final int number) {
	super();
	this.id = id;
	this.booking = booking;
	this.number = number;
    }

    public Booking getBooking() {
	return booking;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put(
		"booking_ID",
		DatabaseController.getInstance().queryInsertUpdateBooking(
			this.booking));
	objects.put("number", this.number);
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
	final String className = parentClass + "bookinglist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	objects.putAll(this.booking.getTableData(className));

	return objects;
    }

    public BookingList setDatabaseData(final HashMap<String, Object> objects) {
	this.booking = DatabaseController.getInstance().querySelectBooking(
		(int) objects.get("booking_ID"));
	setData(objects);
	return this;
    }

    public BookingList setTableData(final HashMap<String, Object> objects) {
	final String className = "bookinglist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), bookingMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("booking_")) {
		bookingMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.booking = new Booking().setTableData(bookingMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private Booking booking;
    private int id;
    private int number;
}
