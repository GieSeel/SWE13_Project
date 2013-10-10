package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.person_mgt.Guest;
import de.dhbw.swe.camping_site_mgt.person_mgt.GuestList;

public class Booking extends BaseDataObject {
    public Booking() {
	this(new Bill(), new ChipCardList(), new EquipmentList(),
		new ExtraBookingList(), new GuestList(), null,
		new PitchBookingList(), new Guest(), null);
    }

    public Booking(final Bill bill, final ChipCardList list,
	    final EquipmentList equipment, final ExtraBookingList extraBooking,
	    final GuestList fellowTravelers, final Date from,
	    final PitchBookingList pitchBooking, final Guest responsiblePerson,
	    final Date until) {
	this(0, bill, list, equipment, extraBooking, fellowTravelers, from,
		pitchBooking, responsiblePerson, until);
    }

    public Booking(final int id, final Bill bill, final ChipCardList list,
	    final EquipmentList equipment, final ExtraBookingList extraBooking,
	    final GuestList fellowTravelers, final Date from,
	    final PitchBookingList pitchBooking, final Guest responsiblePerson,
	    final Date until) {
	super(id);
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

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Booking object = (Booking) dataObject;
	if (this.bill.equals(object.getBill())
		&& this.chipCard.equals(object.getChipCard())
		&& this.equipment.equals(object.getEquipment())
		&& this.extraBooking.equals(object.getExtraBooking())
		&& this.fellowTravelers.equals(object.getFellowTravelers())
		&& this.from.equals(object.getFrom())
		&& this.pitchBooking.equals(object.getPitchBooking())
		&& this.responsiblePerson.equals(object.getResponsiblePerson())
		&& this.until.equals(object.getUntil())) {
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
     * Returns the chipCard.
     * 
     * @return the chipCard
     */
    public ChipCardList getChipCard() {
	return chipCard;
    }

    /**
     * Returns the equipment.
     * 
     * @return the equipment
     */
    public EquipmentList getEquipment() {
	return equipment;
    }

    /**
     * Returns the extraBooking.
     * 
     * @return the extraBooking
     */
    public ExtraBookingList getExtraBooking() {
	return extraBooking;
    }

    /**
     * Returns the fellowTravelers.
     * 
     * @return the fellowTravelers
     */
    public GuestList getFellowTravelers() {
	return fellowTravelers;
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
     * Returns the pitchBooking.
     * 
     * @return the pitchBooking
     */
    public PitchBookingList getPitchBooking() {
	return pitchBooking;
    }

    /**
     * Returns the responsiblePerson.
     * 
     * @return the responsiblePerson
     */
    public Guest getResponsiblePerson() {
	return responsiblePerson;
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

    private final Bill bill;
    private final ChipCardList chipCard;
    private final EquipmentList equipment;
    private final ExtraBookingList extraBooking;
    private final GuestList fellowTravelers;
    private final Date from;
    private final PitchBookingList pitchBooking;
    private final Guest responsiblePerson;
    private final Date until;
}
