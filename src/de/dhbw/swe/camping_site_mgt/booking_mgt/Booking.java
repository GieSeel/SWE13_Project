package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.person_mgt.Guest;

public class Booking extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Booking() {
	this(new Bill(), null, null, null, null, null, null, new Guest(), null);
    }

    /**
     * Constructor.
     * 
     * @param bill
     * @param chipCards
     * @param equipments
     * @param extraBookings
     * @param fellowGuests
     * @param from
     * @param pitchBookings
     * @param responsibleGuest
     * @param until
     */
    public Booking(final Bill bill, final int[] chipCards, final int[] equipments,
	    final int[] extraBookings, final int[] fellowGuests, final Date from,
	    final int[] pitchBookings, final Guest responsibleGuest,
	    final Date until) {
	this(0, bill, chipCards, equipments, extraBookings, fellowGuests, from,
		pitchBookings, responsibleGuest, until);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param bill
     * @param chipCards
     * @param equipments
     * @param extraBookings
     * @param fellowGuests
     * @param from
     * @param pitchBookings
     * @param responsibleGuest
     * @param until
     */
    public Booking(final int id, final Bill bill, final int[] chipCards,
	    final int[] equipments, final int[] extraBookings,
	    final int[] fellowGuests, final Date from, final int[] pitchBookings,
	    final Guest responsibleGuest, final Date until) {
	super(id);
	this.bill = bill;
	this.chipCards = chipCards;
	this.equipments = equipments;
	this.extraBookings = extraBookings;
	this.fellowGuests = fellowGuests;
	this.from = from;
	this.pitchBookings = pitchBookings;
	this.responsibleGuest = responsibleGuest;
	this.until = until;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Booking object = (Booking) dataObject;
	if (this.responsibleGuest.equals(object.getResponsibleGuest())
		&& this.fellowGuests.equals(object.getFellowGuests())
		&& this.from.equals(object.getFrom())
		&& this.until.equals(object.getUntil())
		&& this.equipments.equals(object.getEquipments())
		&& this.pitchBookings.equals(object.getPitchBookings())
		&& this.extraBookings.equals(object.getExtraBookings())
		&& this.bill.equals(object.getBill())
		&& this.chipCards.equals(object.getChipCards())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the bill.
     * 
     * @return the bill
     */
    public Bill getBill() {
	return bill;
    }

    /**
     * Returns the chipCards.
     * 
     * @return the chipCards
     */
    public int[] getChipCards() {
	return chipCards;
    }

    /**
     * Returns the equipments.
     * 
     * @return the equipments
     */
    public int[] getEquipments() {
	return equipments;
    }

    /**
     * Returns the extraBookings.
     * 
     * @return the extraBookings
     */
    public int[] getExtraBookings() {
	return extraBookings;
    }

    /**
     * Returns the fellowGuests.
     * 
     * @return the fellowGuests
     */
    public int[] getFellowGuests() {
	return fellowGuests;
    }

    /**
     * Returns the from.
     * 
     * @return the from
     */
    public Date getFrom() {
	return from;
    }

    /**
     * Returns the pitchBookings.
     * 
     * @return the pitchBookings
     */
    public int[] getPitchBookings() {
	return pitchBookings;
    }

    /**
     * Returns the responsibleGuest.
     * 
     * @return the responsibleGuest
     */
    public Guest getResponsibleGuest() {
	return responsibleGuest;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "booking";
    }

    /**
     * Returns the until.
     * 
     * @return the until
     */
    public Date getUntil() {
	return until;
    }

    Bill bill;
    int[] chipCards;
    int[] equipments;
    int[] extraBookings;
    int[] fellowGuests;
    Date from;
    int[] pitchBookings;
    Guest responsibleGuest;
    Date until;
}
