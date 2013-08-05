package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.Date;

import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;

public class Booking {
    public Booking(final int id, final Bill bill, final ChipCardList list,
	    final EquipmentList equipment, final ExtraBookingList extraBooking,
	    final GuestList fellowTravelers, final Date from,
	    final PitchBookingList pitchBooking, final Guest responsiblePerson,
	    final Date until) {
	this.id = id;
	this.bill = bill;
	this.chipCard = list;
	this.equipment = equipment;
	this.extraBooking = extraBooking;
	this.fellowTravelers = fellowTravelers;
	this.from = from;
	this.pitchBooking = pitchBooking;
	this.responsiblePerson = responsiblePerson;
	this.until = until;

    }

    public Bill getBill() {
	return bill;
    }

    public ChipCardList getChipCard() {
	return chipCard;
    }

    public EquipmentList getEquipment() {
	return equipment;
    }

    public ExtraBookingList getExtraBooking() {
	return extraBooking;
    }

    public GuestList getFellowTravelers() {
	return fellowTravelers;
    }

    public Date getFrom() {
	return from;
    }

    public int getId() {
	return id;
    }

    public PitchBookingList getPitchBooking() {
	return pitchBooking;
    }

    public Guest getResponsiblePerson() {
	return responsiblePerson;
    }

    public Date getUntil() {
	return until;
    }

    private final Bill bill;

    private final ChipCardList chipCard;

    private final EquipmentList equipment;

    private final ExtraBookingList extraBooking;

    private final GuestList fellowTravelers;

    private final Date from;

    private final int id;
    private final PitchBookingList pitchBooking;
    private final Guest responsiblePerson;

    private final Date until;

}
