package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class PitchBookingList {
    public PitchBookingList(final int id, final int number,
	    final PitchBooking pitchBooking) {
	super();
	this.id = id;
	this.number = number;
	this.pitchBooking = pitchBooking;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public PitchBooking getPitchBooking() {
	return pitchBooking;
    }

    private final int id;
    private final int number;
    private final PitchBooking pitchBooking;
}
