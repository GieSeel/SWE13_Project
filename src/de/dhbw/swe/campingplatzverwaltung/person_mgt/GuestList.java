package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class GuestList {
    public GuestList(final Guest guest, final int number) {
	super();
	this.id = 0;
	this.guest = guest;
	this.number = number;
    }

    public GuestList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.guest = DatabaseController.getInstance().querySelectGuest(
		(int) elements.get("guest_ID"));
	this.number = (int) elements.get("number");
    }

    public GuestList(final int id, final Guest guest, final int number) {
	super();
	this.id = id;
	this.guest = guest;
	this.number = number;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.guest.getData());
	return data;
    }

    public Guest getGuest() {
	return guest;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("guest_ID",
		DatabaseController.getInstance().queryInsertUpdateGuest(this.guest));
	elements.put("number", this.number);
	return elements;
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
