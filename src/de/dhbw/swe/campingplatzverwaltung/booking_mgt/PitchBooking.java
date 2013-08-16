package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.Pitch;

public class PitchBooking {
    public PitchBooking(final boolean electricity, final Pitch pitch) {
	super();
	this.id = 0;
	this.electricity = electricity;
	this.pitch = pitch;
    }

    public PitchBooking(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.electricity = ((int) elements.get("electricity") == 1 ? true : false);
	this.pitch = DatabaseController.getInstance().querySelectPitch(
		(int) elements.get("pitch_ID"));
    }

    public PitchBooking(final int id, final boolean electricity, final Pitch pitch) {
	super();
	this.id = id;
	this.electricity = electricity;
	this.pitch = pitch;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add((this.electricity ? "yes" : "no")); // TODO SPRACHE!!
	data.addAll(this.pitch.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("electricity", (this.electricity ? 1 : 0));
	elements.put("pitch_ID",
		DatabaseController.getInstance().queryInsertUpdatePitch(this.pitch));
	return elements;
    }

    public int getId() {
	return id;
    }

    public Pitch getPitch() {
	return pitch;
    }

    public boolean isElectricity() {
	return electricity;
    }

    private final boolean electricity;
    private final int id;
    private final Pitch pitch;
}
