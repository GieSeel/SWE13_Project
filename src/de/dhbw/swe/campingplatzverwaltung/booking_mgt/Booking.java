package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.sql.Date;

import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;

public class Booking {
    private Bill bill;
    private ChipCardList chipCard;
    private EquipmentList equipment;
    private ExtraBookingList extraBooking;
    private GuestList fellowTravelers;
    private Date from;
    private int number;
    private PitchBookingList pitchBooking;
    private Guest responsiblePerson;
    private Date until;

}
