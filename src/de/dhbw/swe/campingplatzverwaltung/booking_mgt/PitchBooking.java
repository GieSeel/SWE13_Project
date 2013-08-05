package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import de.dhbw.swe.campingplatzverwaltung.place_mgt.Pitch;

public class PitchBooking {
    public PitchBooking(final int id, final boolean electricity, final Pitch pitch) {
	super();
	this.id = id;
	this.electricity = electricity;
	this.pitch = pitch;
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
