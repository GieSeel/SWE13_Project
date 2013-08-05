package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class BookingList {

    public BookingList(final int id, final Booking booking, final int number) {
	super();
	this.booking = booking;
	this.id = id;
	this.number = number;
    }

    public Booking getBooking() {
	return booking;
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
