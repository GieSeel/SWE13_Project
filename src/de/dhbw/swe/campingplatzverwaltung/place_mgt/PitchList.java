package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class PitchList {
    public PitchList(final int id, final int number, final Pitch pitch) {
	super();
	this.id = id;
	this.number = number;
	this.pitch = pitch;
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
