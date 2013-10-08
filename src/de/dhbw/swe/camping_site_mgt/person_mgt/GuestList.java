package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

@Deprecated
public class GuestList {
    public GuestList() {
	super();
	this.id = 0;
	this.guest = null;
	this.number = 0;
    }

    public GuestList(final Guest guest, final int number) {
	super();
	this.id = 0;
	this.guest = guest;
	this.number = number;
    }

    public GuestList(final int id, final Guest guest, final int number) {
	super();
	this.id = id;
	this.guest = guest;
	this.number = number;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("guest_ID",
		DatabaseController.getInstance().queryInsertUpdateGuest(this.guest));
	objects.put("number", this.number);
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "guestlist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	// objects.putAll(this.guest.getTableData(className));

	return objects;
    }

    public GuestList setDatabaseData(final HashMap<String, Object> objects) {
	this.guest = DatabaseController.getInstance().querySelectGuest(
		(int) objects.get("guest_ID"));
	setData(objects);
	return this;
    }

    public GuestList setTableData(final HashMap<String, Object> objects) {
	final String className = "guestlist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), guestMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("guest_")) {
		guestMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	// this.guest = new Guest().setTableData(guestMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private Guest guest;
    private int id;
    private int number;
}
