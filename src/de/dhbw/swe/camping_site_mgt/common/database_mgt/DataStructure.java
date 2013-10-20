package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.lang.reflect.Array;
import java.util.*;

import de.dhbw.swe.camping_site_mgt.booking_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;
import de.dhbw.swe.camping_site_mgt.service_mgt.Service;

/**
 * Static access for database structure.
 * 
 * @author Benny
 * 
 */
@SuppressWarnings("static-access")
public class DataStructure {
    static LanguageMgr LM = LanguageMgr.getInstance();

    static LanguageProperties LP;

    static private HashMap<String, ColumnInfo[]> sqlObjects = null;

    static public ColumnInfo[] getStructureFor(final String key) {
	if (sqlObjects == null) {
	    initObjects();
	}
	if (!sqlObjects.containsKey(key)) {
	    CampingLogger.getLogger(DataStructure.class).warn(
		    "Data structure does not contain '" + key + "'!");
	    return null;
	}
	return sqlObjects.get(key);
    }

    private static void initAddress() {
	sqlObjects.put(
		"address",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("street", String.class,
				LM.get(LP.COLUMN_STREET)),
			new ColumnInfo("houseNumber", String.class,
				LM.get(LP.COLUMN_HOUSENR)),
			new ColumnInfo("town_ID", Integer.class, Town.class) });
    }

    private static void initBill() {
	sqlObjects.put(
		"bill",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("number", Integer.class,
				LM.get(LP.DM_NUMBER)),
			new ColumnInfo("billItem_ID", Integer.class, BillItem.class),
			new ColumnInfo("multiplier", Integer.class,
				LM.get(LP.DM_MULTIPLIER)) });
    }

    private static void initBillitem() {
	sqlObjects.put(
		"billitem",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", Enum.class,
				LM.get(LP.DM_LABELING), BillItem_Labeling.class),
			new ColumnInfo("priceBusySeason", Euro.class,
				LM.get(LP.DM_PRICE_BUSY_SEASON)),
			new ColumnInfo("priceLowSeason", Euro.class,
				LM.get(LP.DM_PRICE_LOW_SEASON)) });
    }

    private static void initBooking() {
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
    }

    private static void initBookinglist() {
	sqlObjects.put("bookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("booking_ID", Integer.class, Booking.class) });
    }

    private static void initChipcard() {
	sqlObjects.put("chipcard", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("validFrom", Date.class, LM.get(LP.DM_VALIDE_FROM)),
		new ColumnInfo("validTo", Date.class, LM.get(LP.DM_VALIDE_TO)) });
    }

    private static void initChipcardlist() {
	sqlObjects.put("chipcardlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });
    }

    private static void initCountry() {
	sqlObjects.put("country", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, LM.get(LP.DM_COUNTRY)),
		new ColumnInfo("acronym", String.class, LM.get(LP.DM_ACRONYM)) });
    }

    private static void initEmployee() {
	sqlObjects.put("employee", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_ID", Integer.class, Person.class),
		new ColumnInfo("employeeRole_ID", Integer.class, null, "role",
			EmployeeRole.class),
		new ColumnInfo("userName", String.class, LM.get(LP.DM_USER_NAME)),
		new ColumnInfo("password", String.class, LM.get(LP.DM_PASSWORD)),
		new ColumnInfo("blocked", Boolean.class, LM.get(LP.DM_IS_BLOCKED)),
		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });
    }

    private static void initEmployeelist() {
	sqlObjects.put("employeelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("employee_ID", Integer.class, Employee.class) });
    }

    private static void initEmployeerole() {
	sqlObjects.put(
		"employeerole",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", Enum.class,
				LM.get(LP.DM_LABELING), EmployeeRole_Labeling.class),
			new ColumnInfo("arrangement", Enum.class,
				LM.get(LP.DM_ARRANGEMENT),
				EmployeeRole_Arrangement.class) });
    }

    private static void initEquipment() {
	sqlObjects.put(
		"equipment",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("type", Enum.class, LM.get(LP.DM_TYPE),
				Equipment_Type.class),
			new ColumnInfo("size", String.class, LM.get(LP.DM_SIZE)),
			new ColumnInfo("identification", String.class,
				LM.get(LP.DM_IDENTIFICATION)) });
    }

    private static void initEquipmentlist() {
	sqlObjects.put("equipmentlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("equipment_ID", Integer.class, Equipment.class) });
    }

    private static void initExtrabooking() {
	sqlObjects.put("extrabooking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, LM.get(LP.DM_EXTRABOOKING)),
		new ColumnInfo("labeling", String.class, LM.get(LP.DM_LABELING)),
		new ColumnInfo("site_ID", Integer.class, Site.class) });
    }

    private static void initExtrabookinglist() {
	sqlObjects.put("extrabookinglist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("site_ID", Integer.class, Site.class) });
    }

    private static void initGuest() {
	sqlObjects.put("guest", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_ID", Integer.class, Person.class),
		new ColumnInfo("visitorsTaxClass_ID", Integer.class,
			VisitorsTaxClass.class) });
    }

    private static void initGuestlist() {
	sqlObjects.put("guestlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("guest_ID", Integer.class, Guest.class) });
    }

    static private void initObjects() {
	sqlObjects = new HashMap<>();

	initAddress();
	initBill();
	initBillitem();
	initBooking();
	initBookinglist();
	initChipcard();
	initChipcardlist();
	initCountry();
	initEmployee();
	initEmployeelist();
	initEmployeerole();
	initEquipment();
	initEquipmentlist();
	initExtrabooking();
	initExtrabookinglist();
	initGuest();
	initGuestlist();
	initPerson();
	initPitch();
	initPitchbooking();
	initPitchbookinglist();
	initPitchlist();
	initService();
	initServicelist();
	initSite();
	initSitelist();
	initTown();
	initVisitorstaxclass();
    }

    private static void initPerson() {
	sqlObjects.put(
		"person",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("identificationNumber", String.class,
				LM.get(LP.DM_IDENTIFICATION_NUMBER)),
			new ColumnInfo("name", String.class, LM.get(LP.DM_NAME)),
			new ColumnInfo("firstName", String.class,
				LM.get(LP.DM_FIRST_NAME)),
			new ColumnInfo("street", String.class, LM.get(LP.DM_STREET)),
			new ColumnInfo("houseNumber", String.class,
				LM.get(LP.DM_HOUSE_NUMBER)),
			new ColumnInfo("town_ID", Integer.class, Town.class),
			new ColumnInfo("country_ID", Integer.class, Country.class),
			new ColumnInfo("dateOfBirth", Date.class,
				LM.get(LP.DM_DATE_OF_BIRTH)) });
    }

    private static void initPitch() {
	sqlObjects.put(
		"pitch",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("area", String.class, LM.get(LP.DM_AREA)),
			new ColumnInfo("type", Enum.class, LM.get(LP.DM_TYPE),
				Pitch_Type.class),
			new ColumnInfo("height", Integer.class,
				LM.get(LP.DM_LENGTH)),
			new ColumnInfo("width", Integer.class, LM.get(LP.DM_WIDTH)),
			new ColumnInfo("natureOfSoil", Enum.class,
				LM.get(LP.DM_NATURE_OF_SOIL),
				Pitch_NatureOfSoil.class),
			new ColumnInfo("deliveryPoint_ID", Integer.class, null,
				"deliveryPoint", Site.class),
			new ColumnInfo("characteristics", String.class,
				LM.get(LP.DM_CHARACTERISTICS)),
			new ColumnInfo("xCoords", Array.class),
			new ColumnInfo("yCoords", Array.class) });
    }

    private static void initPitchbooking() {
	sqlObjects.put(
		"pitchbooking",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("pitch_ID", Integer.class, Pitch.class),
			new ColumnInfo("electricity", Boolean.class,
				LM.get(LP.DM_ELECTRICITY)) });
    }

    private static void initPitchbookinglist() {
	sqlObjects.put(
		"pitchbookinglist",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("number", Integer.class,
				LM.get(LP.DM_NUMBER)),
			new ColumnInfo("pitchBooking_ID", Integer.class,
				PitchBooking.class) });
    }

    private static void initPitchlist() {
	sqlObjects.put("pitchlist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("pitch_ID", Integer.class, Pitch.class) });
    }

    private static void initService() {
	sqlObjects.put(
		"service",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("pitch_ID", Integer.class, Pitch.class),
			new ColumnInfo("site_ID", Integer.class, Site.class),
			new ColumnInfo("employeeRole_ID", Integer.class,
				EmployeeRole.class),
			new ColumnInfo("description", String.class,
				LM.get(LP.DM_DESCRIPTION)),
			new ColumnInfo("creationDate", Date.class,
				LM.get(LP.DM_CREATION_DATE)),
			new ColumnInfo("priority", Integer.class,
				LM.get(LP.DM_PRIORITY)),
			new ColumnInfo("doneDate", Date.class,
				LM.get(LP.DM_DONE_DATE)) });
    }

    private static void initServicelist() {
	sqlObjects.put("servicelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("service_ID", Integer.class, Service.class) });
    }

    private static void initSite() {
	sqlObjects.put(
		"site",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", String.class,
				LM.get(LP.DM_LABELING)),
			new ColumnInfo("type", String.class, "Type"),
			new ColumnInfo("openingHours", String.class,
				LM.get(LP.DM_OPENING_HOURS)),
			new ColumnInfo("description", String.class,
				LM.get(LP.DM_DESCRIPTION)) });
    }

    private static void initSitelist() {
	sqlObjects.put("sitelist", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
		new ColumnInfo("site_ID", Integer.class, Site.class) });
    }

    private static void initTown() {
	sqlObjects.put(
		"town",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("name", String.class, LM.get(LP.DM_TOWN)),
			new ColumnInfo("postalCode", String.class,
				LM.get(LP.DM_POSTAL_CODE)) });
    }

    private static void initVisitorstaxclass() {
	sqlObjects.put("visitorstaxclass", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("labeling", Enum.class, LM.get(LP.DM_LABELING),
			VisitorsTaxClass_Labeling.class),
		new ColumnInfo("price", Euro.class, LM.get(LP.DM_PRICE)) });
    }
}
