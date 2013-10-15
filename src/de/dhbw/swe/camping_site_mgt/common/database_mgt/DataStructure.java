package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.booking_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;
import de.dhbw.swe.camping_site_mgt.person_mgt.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;
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
	sqlObjects.put(
		"bill",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("number", Integer.class,
				lm.get(lp.DM_NUMBER)),
			new ColumnInfo("billItem_ID", Integer.class, BillItem.class),
			new ColumnInfo("multiplier", Integer.class,
				lm.get(lp.DM_MULTIPLIER)) });

	// "billitem"
	sqlObjects.put(
		"billitem",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", Enum.class,
				lm.get(lp.DM_LABELING), BillItem_Labeling.class),
			new ColumnInfo("priceBusySeason", Euro.class,
				lm.get(lp.DM_PRICE_BUSY_SEASON)),
			new ColumnInfo("priceLowSeason", Euro.class,
				lm.get(lp.DM_PRICE_LOW_SEASON)) });

	// "booking"
	sqlObjects.put("booking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("responsibleGuest_ID", Integer.class, null,
			"responsibleGuest", Guest.class),
		new ColumnInfo("fellowGuests", Array.class, "Fellow Guests"),
		new ColumnInfo("from", Date.class, "From"),
		new ColumnInfo("until", Date.class, "Until"),
		new ColumnInfo("equipments", Array.class, "Equipments"),
		new ColumnInfo("pitchBookings", Array.class, "Pitch Bookings"),
		new ColumnInfo("extraBookings", Array.class, "Extra Bookings"),
		new ColumnInfo("bill_number", Integer.class, Bill.class),
		new ColumnInfo("chipCards", Array.class, "Chip Cards") });
	// TODO BILL??

	// "bookinglist"
	sqlObjects.put("bookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("booking_ID", Integer.class, Booking.class) });

	// "chipcard"
	sqlObjects.put("chipcard", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("validFrom", Date.class, lm.get(lp.DM_VALIDE_FROM)),
		new ColumnInfo("validTo", Date.class, lm.get(lp.DM_VALIDE_TO)) });

	// "chipcardlist"
	sqlObjects.put("chipcardlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });

	// "country"
	sqlObjects.put("country", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, lm.get(lp.DM_COUNTRY)),
		new ColumnInfo("acronym", String.class, lm.get(lp.DM_ACRONYM)) });

	// "employee"
	sqlObjects.put("employee",

	new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_ID", Integer.class, Person.class),
		new ColumnInfo("employeeRole_ID", Integer.class, null, "role",
			EmployeeRole.class),
		new ColumnInfo("userName", String.class, lm.get(lp.DM_USER_NAME)),
		new ColumnInfo("password", String.class, lm.get(lp.DM_PASSWORD)),
		new ColumnInfo("blocked", Integer.class, lm.get(lp.DM_IS_BLOCKED)),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });

	// "employeelist"
	sqlObjects.put("employeelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("employee_ID", Integer.class, Employee.class) });

	// "employeerole"
	sqlObjects.put(
		"employeerole",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", Enum.class,
				lm.get(lp.DM_LABELING), EmployeeRole_Labeling.class),
			new ColumnInfo("arrangement", Enum.class,
				lm.get(lp.DM_ARRANGEMENT),
				EmployeeRole_Arrangement.class) });

	// "equipment"
	sqlObjects.put(
		"equipment",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("type", Enum.class, lm.get(lp.DM_TYPE),
				Equipment_Type.class),
			new ColumnInfo("size", String.class, lm.get(lp.DM_SIZE)),
			new ColumnInfo("identification", String.class,
				lm.get(lp.DM_IDENTIFICATION)) });

	// "equipmentlist"
	sqlObjects.put("equipmentlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("equipment_ID", Integer.class, Equipment.class) });

	// "extrabooking"
	sqlObjects.put("extrabooking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, lm.get(lp.DM_EXTRABOOKING)),
		new ColumnInfo("labeling", String.class, lm.get(lp.DM_LABELING)),
		new ColumnInfo("site_ID", Integer.class, Site.class) });

	// "extrabookinglist"
	sqlObjects.put("extrabookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
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
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("guest_ID", Integer.class, Guest.class) });

	// "person"
	sqlObjects.put(
		"person",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("identificationNumber", String.class,
				lm.get(lp.DM_IDENTIFICATION_NUMBER)),
			new ColumnInfo("name", String.class, lm.get(lp.DM_NAME)),
			new ColumnInfo("firstName", String.class,
				lm.get(lp.DM_FIRST_NAME)),
			new ColumnInfo("street", String.class, lm.get(lp.DM_STREET)),
			new ColumnInfo("houseNumber", String.class,
				lm.get(lp.DM_HOUSE_NUMBER)),
			new ColumnInfo("town_ID", Integer.class, Town.class),
			new ColumnInfo("country_ID", Integer.class, Country.class),
			new ColumnInfo("dateOfBirth", Date.class,
				lm.get(lp.DM_DATE_OF_BIRTH)) });

	// "pitch"
	sqlObjects.put(
		"pitch",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("area", String.class, lm.get(lp.DM_AREA)),
			new ColumnInfo("type", Enum.class, lm.get(lp.DM_TYPE),
				Pitch_Type.class),
			new ColumnInfo("height", Integer.class,
				lm.get(lp.DM_LENGTH)),
			new ColumnInfo("width", Integer.class, lm.get(lp.DM_WIDTH)),
			new ColumnInfo("natureOfSoil", Enum.class,
				lm.get(lp.DM_NATURE_OF_SOIL),
				Pitch_NatureOfSoil.class),
			new ColumnInfo("deliveryPoint_ID", Integer.class, null,
				"deliveryPoint", Site.class),
			new ColumnInfo("characteristics", String.class,
				lm.get(lp.DM_CHARACTERISTICS)),
			new ColumnInfo("xCoords", Array.class),
			new ColumnInfo("yCoords", Array.class) });

	// "pitchbooking"
	sqlObjects.put(
		"pitchbooking",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("pitch_ID", Integer.class, Pitch.class),
			new ColumnInfo("electricity", Integer.class,
				lm.get(lp.DM_ELECTRICITY)) });

	// "pitchbookinglist"
	sqlObjects.put(
		"pitchbookinglist",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("number", Integer.class,
				lm.get(lp.DM_NUMBER)),
			new ColumnInfo("pitchBooking_ID", Integer.class,
				PitchBooking.class) });

	// "pitchlist"
	sqlObjects.put("pitchlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
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
			new ColumnInfo("description", String.class,
				lm.get(lp.DM_DESCRIPTION)),
			new ColumnInfo("creationDate", Date.class,
				lm.get(lp.DM_CREATION_DATE)),
			new ColumnInfo("priority", Integer.class,
				lm.get(lp.DM_PRIORITY)),
			new ColumnInfo("doneDate", Date.class,
				lm.get(lp.DM_DONE_DATE)) });

	// "servicelist"
	sqlObjects.put("servicelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("service_ID", Integer.class, Service.class) });

	// "site"
	sqlObjects.put(
		"site",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", String.class,
				lm.get(lp.DM_LABELING)),
			new ColumnInfo("type", String.class, "Type"),
			new ColumnInfo("openingHours", String.class,
				lm.get(lp.DM_OPENING_HOURS)),
			new ColumnInfo("description", String.class,
				lm.get(lp.DM_DESCRIPTION)) });

	// "sitelist"
	sqlObjects.put("sitelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, lm.get(lp.DM_NUMBER)),
		new ColumnInfo("site_ID", Integer.class, Site.class) });

	// "town"
	sqlObjects.put(
		"town",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("name", String.class, lm.get(lp.DM_TOWN)),
			new ColumnInfo("postalCode", String.class,
				lm.get(lp.DM_POSTAL_CODE)) });

	// "visitorstaxclass"
	sqlObjects.put("visitorstaxclass",

	new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("labeling", Enum.class, lm.get(lp.DM_LABELING),
			VisitorsTaxClass_Labeling.class),
		new ColumnInfo("price", Euro.class, lm.get(lp.DM_PRICE)) });
    }
}
