package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;

public class Booking {
    public Booking() {
	super();
	this.id = 0;
	this.bill = null;
	this.chipCard = null;
	this.equipment = null;
	this.extraBooking = null;
	this.fellowTravelers = null;
	this.pitchBooking = null;
	this.responsiblePerson = null;
	this.from = null;
	this.until = null;
    }

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

    public HashMap<String, Object> getDatabaseData() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("bill_number", (db.queryInsertUpdateBill(this.bill)));
	objects.put("chipCardList_number",
		db.queryInsertUpdateChipCardList(this.chipCard));
	objects.put("equipmentList_number",
		db.queryInsertUpdateEquipmentList(this.equipment));
	objects.put("extraBookingList_number",
		db.queryInsertUpdateExtraBookingList(this.extraBooking));
	objects.put("fellowTravelersList_number",
		db.queryInsertUpdateGuestList(this.fellowTravelers));
	objects.put("from", this.from);
	objects.put("pitchBookingList_number",
		db.queryInsertUpdatePitchBookingList(this.pitchBooking));
	objects.put("responsiblePerson_ID",
		db.queryInsertUpdateGuest(this.responsiblePerson));
	objects.put("until", this.until);
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "booking_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "from", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(from)));
	objects.put(className + "until", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(until)));

	objects.putAll(this.bill.getTableData(className));
	objects.putAll(this.chipCard.getTableData(className));
	objects.putAll(this.equipment.getTableData(className));
	objects.putAll(this.extraBooking.getTableData(className));
	objects.putAll(this.fellowTravelers.getTableData(className));
	objects.putAll(this.pitchBooking.getTableData(className));
	objects.putAll(this.responsiblePerson.getTableData(className));

	return objects;
    }

    public Date getUntil() {
	return until;
    }

    public Booking setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.bill = db.querySelectBill((int) objects.get("bill_number"));
	this.chipCard = db.querySelectChipCardList((int) objects.get("chipCardList_number"));
	this.equipment = db.querySelectEquipmentList((int) objects.get("equipmentList_number"));
	this.extraBooking = db.querySelectExtraBookingList((int) objects.get("extraBookingList_number"));
	this.fellowTravelers = db.querySelectGuestList((int) objects.get("fellowTravelersList_number"));
	this.pitchBooking = db.querySelectPitchBookingList((int) objects.get("pitchBookingList_number"));
	this.responsiblePerson = db.querySelectGuest((int) objects.get("responsiblePerson_ID"));
	setData(objects);
	return this;
    }

    public Booking setTableData(final HashMap<String, Object> objects) {
	final String className = "booking_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), billMap = new HashMap<String, Object>(), chipcardMap = new HashMap<String, Object>(), equipmentMap = new HashMap<String, Object>(), extrabookingMap = new HashMap<String, Object>(), guestlistMap = new HashMap<String, Object>(), pitchbookinglistMap = new HashMap<String, Object>(), guestMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("bill_")) {
		billMap.put(key, val);
	    } else if (key.startsWith("chipcardlist_")) {
		chipcardMap.put(key, val);
	    } else if (key.startsWith("equipmentlist_")) {
		equipmentMap.put(key, val);
	    } else if (key.startsWith("extrabookinglist_")) {
		extrabookingMap.put(key, val);
	    } else if (key.startsWith("guestlist_")) {
		guestlistMap.put(key, val);
	    } else if (key.startsWith("pitchbookinglist_")) {
		pitchbookinglistMap.put(key, val);
	    } else if (key.startsWith("guest_")) {
		guestMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.bill = new Bill().setTableData(billMap);
	this.chipCard = new ChipCardList().setTableData(chipcardMap);
	this.equipment = new EquipmentList().setTableData(equipmentMap);
	this.extraBooking = new ExtraBookingList().setTableData(extrabookingMap);
	this.fellowTravelers = new GuestList().setTableData(guestlistMap);
	this.pitchBooking = new PitchBookingList().setTableData(pitchbookinglistMap);
	this.responsiblePerson = new Guest().setTableData(guestMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.from = (Date) objects.get("from");
	this.until = (Date) objects.get("until");
    }

    private Bill bill;
    private ChipCardList chipCard;
    private EquipmentList equipment;
    private ExtraBookingList extraBooking;
    private GuestList fellowTravelers;
    private Date from;
    private int id;
    private PitchBookingList pitchBooking;
    private Guest responsiblePerson;
    private Date until;
}
