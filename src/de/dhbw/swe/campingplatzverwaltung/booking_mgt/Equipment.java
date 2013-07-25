package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class Equipment {
    private enum type {
	Bike, Boat, Camper, Car, Caravan, Motorbike, Other, Tent
    } // Camper = Motorhome

    private String identification;
    private String size;
}
