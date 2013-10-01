package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.booking_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;
import de.dhbw.swe.camping_site_mgt.person_mgt.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;
import de.dhbw.swe.camping_site_mgt.service_mgt.Service;

/**
 * Static access for database structure.
 * 
 * @author Benny
 * 
 */
public class DataStructure {
    static LanguageMgr lm = LanguageMgr.getInstance();

    static LanguageProperties lp;

    static private HashMap<String, ColumnInfo[]> sqlObjects = null;

    static public ColumnInfo[] getStructureFor(final String key) {
	if (sqlObjects == null) {
	    initObjects();
	}
	if (!sqlObjects.containsKey(key)) {
	    // TODO logger
	    return null;
	}
	return sqlObjects.get(key);
    }

    static private void initObjects() {
	sqlObjects = new HashMap<>();

	// "address"
	sqlObjects.put(
		"address",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("street", String.class,
				lm.get(lp.COLUMN_STREET)),
			new ColumnInfo("houseNumber", String.class,
				lm.get(lp.COLUMN_HOUSENR)),
			new ColumnInfo("town_ID", Integer.class, Town.class) });

	// TODO alle languageproperties noch einfügen und verwenden..

	// "bill"
	sqlObjects.put("bill", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("billItem_ID", Integer.class, BillItem.class),
		new ColumnInfo("multiplier", Integer.class, "Multiplier") });

	// "billitem"
	sqlObjects.put("billitem",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", String.class, "Labeling"),
			new ColumnInfo("priceBusySeason", Float.class,
				"Price Busy Season"),
			new ColumnInfo("priceLowSeason", Float.class,
				"Price Low Season") });

	// "booking"
	sqlObjects.put("booking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("responsiblePerson_ID", Integer.class, null,
			"responsiblePerson", Guest.class),
		new ColumnInfo("fellowTravelersList_number", Integer.class, null,
			"fellowTravelers", GuestList.class),
		new ColumnInfo("from", Date.class, "From"),
		new ColumnInfo("until", Date.class, "Until"),
		new ColumnInfo("equipmentList_number", Integer.class, null,
			"equipment", EquipmentList.class),
		new ColumnInfo("pitchBookingList_number", Integer.class, null,
			"pitchBooking", PitchBookingList.class),
		new ColumnInfo("extraBookingList_number", Integer.class, null,
			"extraBooking", ExtraBookingList.class),
		new ColumnInfo("bill_number", Integer.class, Bill.class),
		new ColumnInfo("chipCardList_number", Integer.class, null,
			"chipCard", ChipCardList.class) });
	// TODO BOOKING NAMEN ANPASSEN!!

	// "bookinglist"
	sqlObjects.put("bookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("booking_ID", Integer.class, Booking.class) });

	// "chipcard"
	sqlObjects.put("chipcard", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("validFrom", Date.class, "Valide From"),
		new ColumnInfo("validTo", Date.class, "Valide To") });

	// "chipcardlist"
	sqlObjects.put("chipcardlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });

	// "country"
	sqlObjects.put("country", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, "Name"),
		new ColumnInfo("acronym", String.class, "Acronym") });

	// "employee"
	sqlObjects.put("employee",

	new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_ID", Integer.class, Person.class),
		new ColumnInfo("employeeRole_ID", Integer.class, null, "role",
			EmployeeRole.class),
		new ColumnInfo("userName", String.class, "User Name"),
		new ColumnInfo("password", String.class, "Password"),
		new ColumnInfo("blocked", Integer.class, "Is Blocked"),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });

	// "employeelist"
	sqlObjects.put("employeelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("employee_ID", Integer.class, Employee.class) });

	// "employeerole"
	sqlObjects.put("employeerole", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("labeling", String.class, "Labeling"),
		new ColumnInfo("arrangement", String.class, "Arrangement") });

	// "equipment"
	sqlObjects.put("equipment", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("type", String.class, "Type"),
		new ColumnInfo("size", String.class, "Size"),
		new ColumnInfo("identification", String.class, "Identification") });

	// "equipmentlist"
	sqlObjects.put("equipmentlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("equipment_ID", Integer.class, Equipment.class) });

	// "extrabooking"
	sqlObjects.put("extrabooking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, "Name"),
		new ColumnInfo("labeling", String.class, "Labeling"),
		new ColumnInfo("site_ID", Integer.class, Site.class) });

	// "extrabookinglist"
	sqlObjects.put("extrabookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("site_ID", Integer.class, Site.class) });

	// "guest"
	sqlObjects.put("guest", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_ID", Integer.class, Person.class),
		new ColumnInfo("visitorsTaxClass_ID", Integer.class,
			VisitorsTaxClass.class) });

	// "guestlist"
	sqlObjects.put("guestlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("guest_ID", Integer.class, Guest.class) });

	// "person"
	sqlObjects.put("person", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("identificationNumber", String.class,
			"Identification Number"),
		new ColumnInfo("name", String.class, "Name"),
		new ColumnInfo("firstName", String.class, "First Name"),
		new ColumnInfo("address_ID", Integer.class, Address.class),
		new ColumnInfo("dateOfBirth", Date.class, "Date of Birth") });

	// "pitch"
	sqlObjects.put("pitch", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("district", String.class, "District"),
		new ColumnInfo("type", String.class, "Type"),
		new ColumnInfo("length", Integer.class, "Length"),
		new ColumnInfo("width", Integer.class, "Width"),
		new ColumnInfo("natureOfSoil", String.class, "Nature of Soil"),
		new ColumnInfo("deliveryPoint_ID", Integer.class, null,
			"deliveryPoint", Site.class),
		new ColumnInfo("characteristics", String.class, "Characteristics"),
		new ColumnInfo("xCoords", String.class),
		new ColumnInfo("yCoords", String.class) });

	// "pitchbooking"
	sqlObjects.put("pitchbooking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("pitch_ID", Integer.class, Pitch.class),
		new ColumnInfo("electricity", Integer.class, "Electricity") });

	// "pitchbookinglist"
	sqlObjects.put("pitchbookinglist",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("number", Integer.class, "Number"),
			new ColumnInfo("pitchBooking_ID", Integer.class,
				PitchBooking.class) });

	// "pitchlist"
	sqlObjects.put("pitchlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("pitch_ID", Integer.class, Pitch.class) });

	// "service"
	sqlObjects.put(
		"service",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("pitch_ID", Integer.class, Pitch.class),
			new ColumnInfo("site_ID", Integer.class, Site.class),
			new ColumnInfo("employeeRole_ID", Integer.class,
				EmployeeRole.class),
			new ColumnInfo("description", String.class, "Description"),
			new ColumnInfo("creationDate", Date.class, "Creation Date"),
			new ColumnInfo("priority", Integer.class, "Priority"),
			new ColumnInfo("doneDate", Date.class, "Done Date") });

	// "servicelist"
	sqlObjects.put("servicelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("service_ID", Integer.class, Service.class) });

	// "site"
	sqlObjects.put("site", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("labeling", String.class, "Labeling"),
		new ColumnInfo("type", String.class, "Type"),
		new ColumnInfo("openingHours", String.class, "Opening Hours"),
		new ColumnInfo("description", String.class, "Description") });

	// "sitelist"
	sqlObjects.put("sitelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, "Number"),
		new ColumnInfo("site_ID", Integer.class, Site.class) });

	// "town"
	sqlObjects.put("town", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, "Name"),
		new ColumnInfo("postalCode", String.class, "Postal Code"),
		new ColumnInfo("country_ID", Integer.class, Country.class) });

	// "visitorstaxclass"
	sqlObjects.put("visitorstaxclass",

	new ColumnInfo[] { new ColumnInfo("id", Integer.class),
		new ColumnInfo("labeling", String.class, "Labeling"),
		new ColumnInfo("price", Float.class, "Price") });
    }
}
