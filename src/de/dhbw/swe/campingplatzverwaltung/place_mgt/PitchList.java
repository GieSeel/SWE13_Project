package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class PitchList {
    public PitchList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.pitch = DatabaseController.getInstance().querySelectPitch(
		(int) elements.get("pitch_ID"));
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.pitch.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put("pitch_ID",
		DatabaseController.getInstance().queryInsertUpdatePitch(this.pitch));
	return elements;
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

    private final int id;
    private final int number;
    private final Pitch pitch;
}
