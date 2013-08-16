package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class BookingList {

    public BookingList(final Booking booking, final int number) {
	super();
	this.id = 0;
	this.booking = booking;
	this.number = number;
    }

    public BookingList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.booking = DatabaseController.getInstance().querySelectBooking(
		(int) elements.get("booking_ID"));
	this.number = (int) elements.get("number");

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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.booking.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put(
		"booking_ID",
		DatabaseController.getInstance().queryInsertUpdateBooking(
			this.booking));
	elements.put("number", this.number);
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    private final Booking booking;
    private final int id;
    private final int number;
}
