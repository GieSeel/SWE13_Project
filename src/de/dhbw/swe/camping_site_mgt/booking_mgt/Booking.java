package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.Date;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Chipcard;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.person_mgt.Guest;

public class Booking extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Booking() {
    	this(new Guest(), null, null);
    }

    /**
     * Constructor.
     * 
     * @param guest
     * @param from
     * @param until
     */
    public Booking(Guest guest, Date from, Date until) {
    	this(0, guest, new Duration(from, until), null, null, null, null, null, null);
    }

    /**
     * Constructor.
     * 
 	 * @param Guest guest;
	 * @param Duration duration;
	 * @param Vector<PitchBooking> pitchBookings;
	 * @param Vector<Guest> fellowGuests;
	 * @param Vector<Equipment> equipments;
	 * @param Vector<ExtraBooking> extraBookings;
	 * @param Vector<Chipcard> chipcards;
	 * @param Vector<BillItem> bill;
     */
    public Booking(final int id, Guest guest, Duration duration, Vector<PitchBooking> pitchBookings, Vector<Guest> fellowGuests, Vector<Equipment> equipments, Vector<ExtraBooking> extraBookings, Vector<Chipcard> chipcards, Vector<BillItem> bill) {
		super(id);
		this.guest = guest;
		this.duration = duration;
		this.pitchBookings = pitchBookings;
		this.fellowGuests = fellowGuests;
		this.equipments = equipments;
		this.extraBookings = extraBookings;
		this.chipcards = chipcards;
		this.bill = bill;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
    	// TODO
//	final Booking object = (Booking) dataObject;
//	if (this.responsibleGuest.equals(object.getResponsibleGuest())
//		&& this.fellowGuests.equals(object.getFellowGuests())
//		&& this.duration.equals(object.getDuration())
//		&& this.equipments.equals(object.getEquipments())
//		&& this.pitchBookings.equals(object.getPitchBookings())
//		&& this.extraBookings.equals(object.getExtraBookings())
//		&& this.bill.equals(object.getBill())
//		&& this.chipcards.equals(object.getChipCards())) {
//	    return true;
//	}
	return false;
    }

    /**
	 * @return the guest
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
	 * @return the pitchBookings
	 */
	public Vector<PitchBooking> getPitchBookings() {
		return pitchBookings;
	}

	/**
	 * @return the fellowGuests
	 */
	public Vector<Guest> getFellowGuests() {
		return fellowGuests;
	}

	/**
	 * @return the equipments
	 */
	public Vector<Equipment> getEquipments() {
		return equipments;
	}

	/**
	 * @return the extraBookings
	 */
	public Vector<ExtraBooking> getExtraBookings() {
		return extraBookings;
	}

	/**
	 * @return the chipcards
	 */
	public Vector<Chipcard> getChipcards() {
		return chipcards;
	}

	/**
	 * @return the bill
	 */
	public Vector<BillItem> getBill() {
		return bill;
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
    
    public Boolean alreadyExists(PitchBooking pitchBooking) { 
    	return false;
    	}
    	public Boolean alreadyExists(Guest guest) { 
    	return false;
    	}
    	public Boolean alreadyExists(Equipment equipment) { 
    	return false;
    	}
    	public void addPitchBooking(PitchBooking pitchBooking) { 
    	}
    	public void addFellowGuest(Guest guest) { 
    	}
    	public void addEquipment(Equipment equipment) { 
    	}
    	public void addExtraBooking(ExtraBooking extraBooking) { 
    	}
    	public void addChipcard(Chipcard chipcard) { 
    	}
    	public Vector<BillItem> writeBill() { 
    		return null;
    	}
    	public void removePitchBooking(PitchBooking pitchBooking) { 
    	}
    	public void removeFellowGuest(Guest guest) { 
    	}
    	public void removeEquipment(Equipment equipment) { 
    	}
    	public void removeExtraBooking(ExtraBooking extraBooking) {
    	}

    private Guest guest;
    private Duration duration;
    private Vector<PitchBooking> pitchBookings;
    private Vector<Guest> fellowGuests;
    private Vector<Equipment> equipments;
    private Vector<ExtraBooking> extraBookings;
    private Vector<Chipcard> chipcards;
    private Vector<BillItem> bill;
}
