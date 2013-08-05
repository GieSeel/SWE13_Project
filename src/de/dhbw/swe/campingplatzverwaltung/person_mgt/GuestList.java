package de.dhbw.swe.campingplatzverwaltung.person_mgt;

public class GuestList {
    public GuestList(final int id, final Guest guest, final int number) {
	super();
	this.id = id;
	this.guest = guest;
	this.number = number;
    }

    public Guest getGuest() {
	return guest;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    private final Guest guest;
    private final int id;

    private final int number;
}
