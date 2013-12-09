package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.Date;
import java.util.HashMap;

import javax.xml.datatype.Duration;

import de.dhbw.swe.camping_site_mgt.booking_mgt.BillElement;
import de.dhbw.swe.camping_site_mgt.booking_mgt.BillElement_Labeling;
import de.dhbw.swe.camping_site_mgt.booking_mgt.Booking;
import de.dhbw.swe.camping_site_mgt.booking_mgt.Equipment;
import de.dhbw.swe.camping_site_mgt.booking_mgt.Equipment_Type;
import de.dhbw.swe.camping_site_mgt.booking_mgt.ExtraBooking;
import de.dhbw.swe.camping_site_mgt.booking_mgt.PitchBooking;
import de.dhbw.swe.camping_site_mgt.common.Address;
import de.dhbw.swe.camping_site_mgt.common.Chipcard;
import de.dhbw.swe.camping_site_mgt.common.Country;
import de.dhbw.swe.camping_site_mgt.common.Town;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole_Arrangement;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole_Labeling;
import de.dhbw.swe.camping_site_mgt.person_mgt.Guest;
import de.dhbw.swe.camping_site_mgt.person_mgt.Person;
import de.dhbw.swe.camping_site_mgt.place_mgt.Deliverypoint;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_NatureOfSoil;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_Type;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;
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
			new ColumnInfo("house_number", String.class,
				LM.get(LP.COLUMN_HOUSENR)) });
    }

    private static void initBillitem() {
    	sqlObjects.put(
    			"bill_item",
    			new ColumnInfo[] {
    					new ColumnInfo("id", Integer.class),
    					new ColumnInfo("labeling", Enum.class,
    							LM.get(LP.DM_LABELING), BillElement_Labeling.class),
    							new ColumnInfo("price_busy_season", Integer.class,
    									LM.get(LP.DM_PRICE_BUSY_SEASON)),
    									new ColumnInfo("price_low_season", Integer.class,
    											LM.get(LP.DM_PRICE_LOW_SEASON)) });
    }
    
    private static void initBooking() {
	sqlObjects.put("booking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("duration_id", Integer.class, Duration.class),
		new ColumnInfo("guest_id", Integer.class, Guest.class),
		new ColumnInfo("checked_in", Boolean.class, "Checked in") });
    }

    private static void initBill() {
    	sqlObjects.put(
    			"booking_bill_item",
    			new ColumnInfo[] {
    					new ColumnInfo("id", Integer.class),
    					new ColumnInfo("bill_item_id", Integer.class, BillElement.class),
    					new ColumnInfo("multiplier", Integer.class,
    							LM.get(LP.DM_MULTIPLIER)),
    							new ColumnInfo("current_price", Integer.class,
    									"Current Price")});
    }

    private static void initBookingChipcard() {
	sqlObjects.put("booking_chipcard", new ColumnInfo[] {
		new ColumnInfo("booking_id", Integer.class, Booking.class),
		new ColumnInfo("chipcard_id", Integer.class, Chipcard.class)});
    }

    private static void initBookingEquipment() {
	sqlObjects.put("booking_equipment", new ColumnInfo[] {
		new ColumnInfo("booking_id", Integer.class, Booking.class),
		new ColumnInfo("equipment_id", Integer.class, Equipment.class)});
    }

    private static void initBookingExtraBooking() {
    	sqlObjects.put("booking_extra_booking", new ColumnInfo[] {
    			new ColumnInfo("booking_id", Integer.class, Booking.class),
    			new ColumnInfo("extra_booking_id", Integer.class, ExtraBooking.class),
    			new ColumnInfo("duration_id", Integer.class, Duration.class)});
    }
    
    private static void initBookingFellowGuest() {
    	sqlObjects.put("booking_fellow_guest", new ColumnInfo[] {
    			new ColumnInfo("booking_id", Integer.class, Booking.class),
    			new ColumnInfo("guest_id", Integer.class, Guest.class),
    			new ColumnInfo("duration_id", Integer.class, Duration.class)});
    }
    
    private static void initBookingPitchBooking() {
    	sqlObjects.put("booking_pitch_booking", new ColumnInfo[] {
    			new ColumnInfo("booking_id", Integer.class, Booking.class),
    			new ColumnInfo("pitch_booking_id", Integer.class, PitchBooking.class),
    			new ColumnInfo("duration_id", Integer.class, Duration.class)});
    }
    
//    private static void initBookinglist() {
//	sqlObjects.put("bookinglist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("booking_ID", Integer.class, Booking.class) });
//    }

    private static void initChipcard() {
	sqlObjects.put("chipcard", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("duration_id", Integer.class, Duration.class) });
    }
//
//    private static void initChipcardlist() {
//	sqlObjects.put("chipcardlist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("chipCard_ID", Integer.class, ChipCard.class) });
//    }

    private static void initCountry() {
	sqlObjects.put("country", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, LM.get(LP.DM_COUNTRY)),
		new ColumnInfo("acronym", String.class, LM.get(LP.DM_ACRONYM)) });
    }

    private static void initDeliverypoint() {
	sqlObjects.put("deliverypoint", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("description", String.class, "Description") });
    }
    
    private static void initDuration() {
	sqlObjects.put("duration", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("from", Date.class, "From"),
		new  ColumnInfo("until", Date.class, "Until")});
    }
    
    private static void initEmployee() {
	sqlObjects.put("employee", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("person_id", Integer.class, Person.class),
		new ColumnInfo("employee_role_id", Integer.class, EmployeeRole.class),
		new ColumnInfo("user_name", String.class, LM.get(LP.DM_USER_NAME)),
		new ColumnInfo("password", String.class, LM.get(LP.DM_PASSWORD)),
		new ColumnInfo("blocked", Boolean.class, LM.get(LP.DM_IS_BLOCKED)),
		new ColumnInfo("chipcard_id", Integer.class, Chipcard.class) });
    }

//    private static void initEmployeelist() {
//	sqlObjects.put("employeelist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("employee_ID", Integer.class, Employee.class) });
//    }
//
    private static void initEmployeerole() {
	sqlObjects.put(
		"employee_role",
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

//    private static void initEquipmentlist() {
//	sqlObjects.put("equipmentlist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("equipment_ID", Integer.class, Equipment.class) });
//    }
//
    private static void initExtrabooking() {
	sqlObjects.put("extra_booking", new ColumnInfo[] {
		new ColumnInfo("id", Integer.class),
		new ColumnInfo("name", String.class, LM.get(LP.DM_EXTRABOOKING)),
		new ColumnInfo("labeling", String.class, LM.get(LP.DM_LABELING)),
		new ColumnInfo("site_id", Integer.class, Site.class) });
    }

//    private static void initExtrabookinglist() {
//	sqlObjects.put("extrabookinglist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("site_ID", Integer.class, Site.class) });
//    }
//
    private static void initGuest() {
	sqlObjects.put("guest", new ColumnInfo[] {
		new ColumnInfo("person_id", Integer.class, Person.class) });
    }

//    private static void initGuestlist() {
//	sqlObjects.put("guestlist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("guest_ID", Integer.class, Guest.class) });
//    }

    private static void initPerson() {
    	sqlObjects.put("person", new ColumnInfo[] { new ColumnInfo("id", Integer.class),
    	new ColumnInfo("identification_number", String.class, LM.get(LP.DM_IDENTIFICATION_NUMBER)),
    	new ColumnInfo("name", String.class, LM.get(LP.DM_NAME)),
    	new ColumnInfo("first_name", String.class, LM.get(LP.DM_FIRST_NAME)),
    	new ColumnInfo("date_of_birth", Date.class, LM.get(LP.DM_DATE_OF_BIRTH)),
    	new ColumnInfo("address_id", Integer.class, Address.class),
    	new ColumnInfo("town_id", Integer.class, Town.class),
    	new ColumnInfo("country_id", Integer.class, Country.class)
    	});
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
			new ColumnInfo("nature_of_soil", Enum.class,
				LM.get(LP.DM_NATURE_OF_SOIL),
				Pitch_NatureOfSoil.class),
			new ColumnInfo("characteristics", String.class,
						LM.get(LP.DM_CHARACTERISTICS)),
			new ColumnInfo("deliverypoint_id", Integer.class, Deliverypoint.class),
			new ColumnInfo("x_coords", String.class),
			new ColumnInfo("y_coords", String.class) });
    }

    private static void initPitchbooking() {
	sqlObjects.put(
		"pitch_booking",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("pitch_id", Integer.class, Pitch.class),
			new ColumnInfo("electricity", Boolean.class,
				LM.get(LP.DM_ELECTRICITY)) });
    }

//    private static void initPitchbookinglist() {
//	sqlObjects.put(
//		"pitchbookinglist",
//		new ColumnInfo[] {
//			new ColumnInfo("id", Integer.class),
//			new ColumnInfo("number", Integer.class,
//				LM.get(LP.DM_NUMBER)),
//			new ColumnInfo("pitchBooking_ID", Integer.class,
//				PitchBooking.class) });
//    }
//
//    private static void initPitchlist() {
//	sqlObjects.put("pitchlist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("pitch_ID", Integer.class, Pitch.class) });
//    }
//
    private static void initService() {
	sqlObjects.put(
		"service",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("employee_role_id", Integer.class,
				EmployeeRole.class),
			new ColumnInfo("duration_id", Integer.class, Duration.class),
			new ColumnInfo("description", String.class,
				LM.get(LP.DM_DESCRIPTION)),
			new ColumnInfo("priority", Integer.class,
				LM.get(LP.DM_PRIORITY)) });
    }
    
    private static void initServiceDeliverypoint() {
	sqlObjects.put("service_deliverypoint", new ColumnInfo[] {
		new ColumnInfo("service_id", Integer.class, Service.class),
		new ColumnInfo("deliverypoint_id", Integer.class, Deliverypoint.class) });
    }
    
    private static void initServicePitch() {
	sqlObjects.put("service_pitch", new ColumnInfo[] {
		new ColumnInfo("service_id", Integer.class, Service.class),
		new ColumnInfo("pitch_id", Integer.class, Pitch.class) });
    }
    
    private static void initServiceSite() {
	sqlObjects.put("service_site", new ColumnInfo[] {
		new ColumnInfo("service_id", Integer.class, Service.class),
		new ColumnInfo("site_id", Integer.class, Site.class) });
    }
//    
//    private static void initServicelist() {
//	sqlObjects.put("servicelist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("service_ID", Integer.class, Service.class) });
//    }

    private static void initSite() {
	sqlObjects.put(
		"site",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("labeling", String.class,
				LM.get(LP.DM_LABELING)),
			new ColumnInfo("type", Enum.class, "Type"),
			new ColumnInfo("opening_hours", String.class,
				LM.get(LP.DM_OPENING_HOURS)),
			new ColumnInfo("description", String.class,
				LM.get(LP.DM_DESCRIPTION)) });
    }
//
//    private static void initSitelist() {
//	sqlObjects.put("sitelist", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("number", Integer.class, LM.get(LP.DM_NUMBER)),
//		new ColumnInfo("site_ID", Integer.class, Site.class) });
//    }

    private static void initTown() {
	sqlObjects.put(
		"town",
		new ColumnInfo[] {
			new ColumnInfo("id", Integer.class),
			new ColumnInfo("name", String.class, LM.get(LP.DM_TOWN)),
			new ColumnInfo("postal_code", String.class,
				LM.get(LP.DM_POSTAL_CODE)) });
    }
//
//    private static void initVisitorstaxclass() {
//	sqlObjects.put("visitorstaxclass", new ColumnInfo[] {
//		new ColumnInfo("id", Integer.class),
//		new ColumnInfo("labeling", Enum.class, LM.get(LP.DM_LABELING),
//			VisitorsTaxClass_Labeling.class),
//		new ColumnInfo("price", Euro.class, LM.get(LP.DM_PRICE)) });
//    }
    
    static private void initObjects() {
    	sqlObjects = new HashMap<>();

    	initAddress();
    	initBill();
    	initBillitem();
    	initBooking();
//    	initBookinglist();
    	initChipcard();
//    	initChipcardlist();
    	initCountry();
    	initEmployee();
//    	initEmployeelist();
    	initEmployeerole();
    	initEquipment();
//    	initEquipmentlist();
    	initExtrabooking();
//    	initExtrabookinglist();
    	initGuest();
//    	initGuestlist();
    	initPerson();
    	initPitch();
    	initPitchbooking();
//    	initPitchbookinglist();
//    	initPitchlist();
    	initService();
//    	initServicelist();
    	initSite();
//    	initSitelist();
    	initTown();
//    	initVisitorstaxclass();
    	initBookingChipcard();
    	initBookingEquipment();
    	initBookingExtraBooking();
    	initBookingFellowGuest();
    	initBookingPitchBooking();
    	initDeliverypoint();
    	initDuration();
    	initServiceDeliverypoint();
    	initServicePitch();
    	initServiceSite();
        }
}
