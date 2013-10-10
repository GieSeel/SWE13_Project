package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

@Deprecated
public class PitchBookingList {
    public PitchBookingList() {
	super();
	this.id = 0;
	this.number = 0;
	this.pitchBooking = null;
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

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put(
		"pitchBooking_ID",
		DatabaseController.getInstance().queryInsertUpdatePitchBooking(
			this.pitchBooking));
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "pitchbookinglist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	// objects.putAll(this.pitchBooking.getTableData(className));

	return objects;
    }

    public PitchBookingList setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.pitchBooking = db.querySelectPitchBooking((int) objects.get("pitchBooking_ID"));
	setData(objects);
	return this;
    }

    public PitchBookingList setTableData(final HashMap<String, Object> objects) {
	final String className = "pitchbookinglist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), pitchbookingMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("pitchbooking_")) {
		pitchbookingMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	// this.pitchBooking = new PitchBooking().setTableData(pitchbookingMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
	this.pitchBooking = DatabaseController.getInstance().querySelectPitchBooking(
		(int) objects.get("pitchBooking_ID"));
    }

    private int id;
    private int number;
    private PitchBooking pitchBooking;
}
