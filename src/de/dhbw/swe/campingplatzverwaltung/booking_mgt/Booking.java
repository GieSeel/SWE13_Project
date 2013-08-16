package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;

public class Booking {
    public Booking(final Bill bill, final ChipCardList list,
	    final EquipmentList equipment, final ExtraBookingList extraBooking,
	    final GuestList fellowTravelers, final String from,
	    final PitchBookingList pitchBooking, final Guest responsiblePerson,
	    final String until) {
	super();
	this.id = 0;
	this.bill = bill;
	this.chipCard = list;
	this.equipment = equipment;
	this.extraBooking = extraBooking;
	this.fellowTravelers = fellowTravelers;
	this.pitchBooking = pitchBooking;
	this.responsiblePerson = responsiblePerson;
	this.from = new Date(0);
	this.until = new Date(0);
	try {
	    this.from = new SimpleDateFormat("dd.MM.yyyy").parse(from);
	    this.until = new SimpleDateFormat("dd.MM.yyyy").parse(until);
	} catch (final ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public Booking(final HashMap<String, Object> elements) {
	super();
	final DatabaseController db = DatabaseController.getInstance();
	this.id = (int) elements.get("id");
	this.bill = db.querySelectBill((int) elements.get("bill_number"));
	this.chipCard = db.querySelectChipCardList((int) elements.get("chipCardList_number"));
	this.equipment = db.querySelectEquipmentList((int) elements.get("equipmentList_number"));
	this.extraBooking = db.querySelectExtraBookingList((int) elements.get("extraBookingList_number"));
	this.fellowTravelers = db.querySelectGuestList((int) elements.get("fellowTravelersList_number"));
	this.from = (Date) elements.get("from");
	this.pitchBooking = db.querySelectPitchBookingList((int) elements.get("pitchBookingList_number"));
	this.responsiblePerson = db.querySelectGuest((int) elements.get("responsiblePerson_ID"));
	this.until = (Date) elements.get("until");
    }

    public Booking(final int id, final Bill bill, final ChipCardList list,
	    final EquipmentList equipment, final ExtraBookingList extraBooking,
	    final GuestList fellowTravelers, final Date from,
	    final PitchBookingList pitchBooking, final Guest responsiblePerson,
	    final Date until) {
	super();
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(from));
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(until));
	data.addAll(this.bill.getData());
	data.addAll(this.chipCard.getData());
	data.addAll(this.equipment.getData());
	data.addAll(this.extraBooking.getData());
	data.addAll(this.fellowTravelers.getData());
	data.addAll(this.pitchBooking.getData());
	data.addAll(this.responsiblePerson.getData());
	return data;
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

    public HashMap<String, Object> getHashMap() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("bill_number", (db.queryInsertUpdateBill(this.bill)));
	elements.put("chipCardList_number",
		db.queryInsertUpdateChipCardList(this.chipCard));
	elements.put("equipmentList_number",
		db.queryInsertUpdateEquipmentList(this.equipment));
	elements.put("extraBookingList_number",
		db.queryInsertUpdateExtraBookingList(this.extraBooking));
	elements.put("fellowTravelersList_number",
		db.queryInsertUpdateGuestList(this.fellowTravelers));
	elements.put("from", this.from);
	elements.put("pitchBookingList_number",
		db.queryInsertUpdatePitchBookingList(this.pitchBooking));
	elements.put("responsiblePerson_ID",
		db.queryInsertUpdateGuest(this.responsiblePerson));
	elements.put("until", this.until);
	return elements;
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
    private Date from;
    private final int id;
    private final PitchBookingList pitchBooking;
    private final Guest responsiblePerson;
    private Date until;
}
