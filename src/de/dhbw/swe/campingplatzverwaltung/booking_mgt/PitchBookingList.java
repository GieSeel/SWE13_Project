package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class PitchBookingList {
    public PitchBookingList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.pitchBooking = DatabaseController.getInstance().querySelectPitchBooking(
		(int) elements.get("pitchBooking_ID"));
    }

    public PitchBookingList(final int id, final int number,
	    final PitchBooking pitchBooking) {
	super();
	this.id = id;
	this.number = number;
	this.pitchBooking = pitchBooking;
    }

    public PitchBookingList(final int number, final PitchBooking pitchBooking) {
	super();
	this.id = 0;
	this.number = number;
	this.pitchBooking = pitchBooking;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.pitchBooking.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put(
		"pitchBooking_ID",
		DatabaseController.getInstance().queryInsertUpdatePitchBooking(
			this.pitchBooking));
	return elements;
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
