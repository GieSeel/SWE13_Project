package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class PitchList {
    public PitchList() {
	super();
	this.id = 0;
	this.number = 0;
	this.pitch = null;
    }

    public PitchList(final int id, final int number, final Pitch pitch) {
	super();
	this.id = id;
	this.number = number;
	this.pitch = pitch;
    }

    public PitchList(final int number, final Pitch pitch) {
	super();
	this.id = 0;
	this.number = number;
	this.pitch = pitch;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put("pitch_ID",
		DatabaseController.getInstance().queryInsertUpdatePitch(this.pitch));
	return objects;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Pitch getPitch() {
	return pitch;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "pitchlist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	objects.putAll(this.pitch.getTableData(className));

	return objects;
    }

    public PitchList setDatabaseData(final HashMap<String, Object> objects) {
	this.pitch = DatabaseController.getInstance().querySelectPitch(
		(int) objects.get("pitch_ID"));
	setData(objects);
	return this;
    }

    public PitchList setTableData(final HashMap<String, Object> objects) {
	final String className = "pitchlist_";
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
	this.number = (int) objects.get("number");
    }

    private int id;
    private int number;
    private Pitch pitch;
}
