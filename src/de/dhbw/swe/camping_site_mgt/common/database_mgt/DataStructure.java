package de.dhbw.swe.camping_site_mgt.common.database_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

/**
 * Static access for database structure.
 * @author Benny
 *
 */
public class DataStructure {
 static private HashMap<String, ColumnInfo[]> sqlObjects = null;
 
 static public ColumnInfo[] getStructureFor(String key) {
	 if(sqlObjects == null) {
		 initObjects();
	 }
	 if(!sqlObjects.containsKey(key)) {
		 // TODO logger
		 return null;
	 } 
	 return sqlObjects.get(key);
 }
 
 static LanguageMgr lm = LanguageMgr.getInstance();
 static LanguageProperties lp;
 
 static private void initObjects() {
	 
	 

	// "address"
	sqlObjects.put("address", new ColumnInfo[] { new ColumnInfo("id", Integer.class, null, null),
		new ColumnInfo("street", String.class, lm.get(lp.COLUMN_STREET), null),
		new ColumnInfo("houseNumber", String.class, lm.get(lp.COLUMN_HOUSENR), null),
		new ColumnInfo("town_ID", Integer.class, null, "town")});
//	sqlObjects.put("address", new String[][] { { "id", "int", null },
//			{ "street", "string", "Street" },
//			{ "houseNumber", "string", "House Number" },
//			{ "town_ID", "int", null } });

//	// "bill"
//	sqlObjects.put("bill", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "billItem_ID", "int", null },
//		{ "multiplier", "int", "Multiplier" } });
//
//	// "billitem"
//	sqlObjects.put("billitem", new String[][] { { "id", "int", null },
//		{ "labeling", "string", "Labeling" },
//		{ "priceBusySeason", "float", "Price Busy Season" },
//		{ "priceLowSeason", "float", "Price Low Season" } });
//
//	// "booking"
//	sqlObjects.put("booking", new String[][] { { "id", "int", null },
//		{ "responsiblePerson_ID", "int", null },
//		{ "fellowTravelersList_number", "int", null },
//		{ "from", "date", "From" }, { "until", "date", "Until" },
//		{ "equipmentList_number", "int", null },
//		{ "pitchBookingList_number", "int", null },
//		{ "extraBookingList_number", "int", null },
//		{ "bill_number", "int", null },
//		{ "chipCardList_number", "int", null } });
//
//	// "bookinglist"
//	sqlObjects.put("bookinglist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "booking_ID", "int", null } });
//
//	// "chipcard"
//	sqlObjects.put("chipcard", new String[][] { { "id", "int", null },
//		{ "validFrom", "date", "Valide From" },
//		{ "validTo", "date", "Valide To" } });
//
//	// "chipcardlist"
//	sqlObjects.put("chipcardlist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "chipCard_ID", "int", null } });
//
//	// "country"
//	sqlObjects.put("country", new String[][] { { "id", "int", null },
//		{ "name", "string", "Name" }, { "acronym", "string", "Acronym" } });
//
//	// "employee"
//	sqlObjects.put("employee",
//		new String[][] { { "id", "int", null },
//			{ "person_ID", "int", null },
//			{ "employeeRole_ID", "int", null },
//			{ "userName", "string", "User Name" },
//			{ "password", "string", "Password" },
//			{ "blocked", "int", "Is Blocked" },
//			{ "chipCard_ID", "int", null } });
//
//	// "employeelist"
//	sqlObjects.put("employeelist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "employee_ID", "int", null } });
//
//	// "employeerole"
//	sqlObjects.put("employeerole", new String[][] { { "id", "int", null },
//		{ "labeling", "string", "Labeling" },
//		{ "arrangement", "string", "Arrangement" } });
//
//	// "equipment"
//	sqlObjects.put("equipment", new String[][] { { "id", "int", null },
//		{ "type", "string", "Type" }, { "size", "string", "Size" },
//		{ "identification", "string", "Identification" } });
//
//	// "equipmentlist"
//	sqlObjects.put("equipmentlist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "equipment_ID", "int", null } });
//
//	// "extrabooking"
//	sqlObjects.put("extrabooking", new String[][] { { "id", "int", null },
//		{ "name", "string", "Name" }, { "labeling", "string", "Labeling" },
//		{ "site_ID", "int", null } });
//
//	// "extrabookinglist"
//	sqlObjects.put("extrabookinglist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "site_ID", "int", null } });
//
//	// "guest"
//	sqlObjects.put("guest", new String[][] { { "id", "int", null },
//		{ "person_ID", "int", null },
//		{ "visitorsTaxClass_ID", "int", null } });
//
//	// "guestlist"
//	sqlObjects.put("guestlist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "guest_ID", "int", null } });
//
//	// "person"
//	sqlObjects.put("person", new String[][] { { "id", "int", null },
//		{ "identificationNumber", "string", "Identification Number" },
//		{ "name", "string", "Name" },
//		{ "firstName", "string", "First Name" },
//		{ "address_ID", "int", null },
//		{ "dateOfBirth", "date", "Date of Birth" } });
//
//	// "pitch"
//	sqlObjects.put("pitch", new String[][] { { "id", "int", null },
//		{ "district", "string", "District" }, { "type", "string", "Type" },
//		{ "length", "int", "Length" }, { "width", "int", "Width" },
//		{ "natureOfSoil", "string", "Nature of Soil" },
//		{ "deliveryPoint_ID", "int", null },
//		{ "characteristics", "string", "Characteristics" } });
//
//	// "pitchbooking"
//	sqlObjects.put("pitchbooking", new String[][] { { "id", "int", null },
//		{ "pitch_ID", "int", null },
//		{ "electricity", "int", "Electricity" } });
//
//	// "pitchbookinglist"
//	sqlObjects.put("pitchbookinglist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "pitchBooking_ID", "int", null } });
//
//	// "pitchlist"
//	sqlObjects.put("pitchlist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "pitch_ID", "int", null } });
//
//	// "service"
//	sqlObjects.put("service", new String[][] { { "id", "int", null },
//		{ "pitch_ID", "int", null }, { "site_ID", "int", null },
//		{ "employeeRole_ID", "int", null },
//		{ "description", "string", "Description" },
//		{ "creationDate", "date", "Creation Date" },
//		{ "priority", "int", "Priority" },
//		{ "doneDate", "date", "Done Date" } });
//
//	// "servicelist"
//	sqlObjects.put("servicelist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "service_ID", "int", null } });
//
//	// "site"
//	sqlObjects.put("site", new String[][] { { "id", "int", null },
//		{ "labeling", "string", "Labeling" }, { "type", "string", "Type" },
//		{ "openingHours", "string", "Opening Hours" },
//		{ "description", "string", "Description" } });
//
//	// "sitelist"
//	sqlObjects.put("sitelist", new String[][] { { "id", "int", null },
//		{ "number", "int", "Number" }, { "site_ID", "int", null } });
//
//	// "town"
//	sqlObjects.put("town", new String[][] { { "id", "int", null },
//		{ "name", "string", "Name" },
//		{ "postalCode", "string", "Postal Code" },
//		{ "country_ID", "int", null } });
//
//	// "visitorstaxclass"
//	sqlObjects.put("visitorstaxclass",
//		new String[][] { { "id", "int", null },
//			{ "labeling", "string", "Labeling" },
//			{ "price", "float", "Price" } });
 }
}
