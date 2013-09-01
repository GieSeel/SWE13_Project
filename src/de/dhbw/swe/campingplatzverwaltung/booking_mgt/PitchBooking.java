package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.Pitch;

public class PitchBooking {
    public PitchBooking() {
	super();
	this.id = 0;
	this.electricity = false;
	this.pitch = null;
    }

    public PitchBooking(final boolean electricity, final Pitch pitch) {
	super();
	this.id = 0;
	this.electricity = electricity;
	this.pitch = pitch;
    }

    public PitchBooking(final int id, final boolean electricity, final Pitch pitch) {
	super();
	this.id = id;
	this.electricity = electricity;
	this.pitch = pitch;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("electricity", (this.electricity ? 1 : 0));
	objects.put("pitch_ID",
		DatabaseController.getInstance().queryInsertUpdatePitch(this.pitch));
	return objects;
    }

    public int getId() {
	return id;
    }

    public Pitch getPitch() {
	return pitch;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "pitchbooking_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "electricity", new String(this.electricity ? "yes"
		: "no")); // TODO SPRACHE!!

	objects.putAll(this.pitch.getTableData(className));

	return objects;
    }

    public boolean isElectricity() {
	return electricity;
    }

    public PitchBooking setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.pitch = db.querySelectPitch((int) objects.get("pitch_ID"));
	setData(objects);
	return this;
    }

    public PitchBooking setTableData(final HashMap<String, Object> objects) {
	final String className = "pitchbooking_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), pitchMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("pitch_")) {
		pitchMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.pitch = new Pitch().setTableData(pitchMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.electricity = ((int) objects.get("electricity") == 1 ? true : false);

    }

    private boolean electricity;
    private int id;
    private Pitch pitch;
}
