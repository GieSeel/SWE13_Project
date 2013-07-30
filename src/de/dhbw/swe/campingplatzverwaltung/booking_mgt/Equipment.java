package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class Equipment {
    private enum Type {
	BIKE, BOAT, CAMPER, CAR, CARAVAN, MOTORBIKE, OTHER, TENT
    }; // CAMPER = MOTORHOME

    private String identification;
    private String size;
}
