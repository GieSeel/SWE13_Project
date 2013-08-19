package de.dhbw.swe.campingplatzverwaltung.common.database_mgt;

import java.sql.*;
import java.util.*;
import java.util.Date;

import de.dhbw.swe.campingplatzverwaltung.booking_mgt.*;
import de.dhbw.swe.campingplatzverwaltung.common.*;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.*;
import de.dhbw.swe.campingplatzverwaltung.service_mgt.*;

/**
 * Insert description for DatabaseController
 * 
 * @author GieSeel
 * @version 1.0
 */
/**
 * Insert description for DatabaseController
 * 
 * @author GieSeel
 * @version 1.0
 */
public class DatabaseController {

    static DatabaseController databaseController;

    static public DatabaseController getInstance() {
	if (databaseController == null) {
	    databaseController = new DatabaseController();
	}
	return databaseController;
    }

    /**
     * Constructor.
     * 
     */
    public DatabaseController() {
	sqlObjects = new HashMap<String, String[][]>();
	final HashMap<String, String[][]> columns = new HashMap<String, String[][]>();

	// Tables
	final String[] tables = { "address", "bill", "billitem", "booking",
		"bookinglist", "chipcard", "chipcardlist", "country", "employee",
		"employeelist", "employeerole", "equipment", "equipmentlist",
		"extrabooking", "extrabookinglist", "guest", "guestlist", "person",
		"pitch", "pitchbooking", "pitchbookinglist", "pitchlist",
		"service", "servicelist", "site", "sitelist", "town",
		"visitorstaxclass" };

	// "address"
	columns.put("address", new String[][] { { "id", "int" },
		{ "street", "string" }, { "houseNumber", "string" },
		{ "town_ID", "int" } });

	// "bill"
	columns.put("bill", new String[][] { { "id", "int" }, { "number", "int" },
		{ "billItem_ID", "int" }, { "multiplier", "int" } });

	// "billitem"
	columns.put("billitem", new String[][] { { "id", "int" },
		{ "labeling", "string" }, { "priceBusySeason", "float" },
		{ "priceLowSeason", "float" } });

	// "booking"
	columns.put("booking", new String[][] { { "id", "int" },
		{ "responsiblePerson_ID", "int" },
		{ "fellowTravelersList_number", "int" }, { "from", "date" },
		{ "until", "date" }, { "equipmentList_number", "int" },
		{ "pitchBookingList_number", "int" },
		{ "extraBookingList_number", "int" }, { "bill_number", "int" },
		{ "chipCardList_number", "int" } });

	// "bookinglist"
	columns.put("bookinglist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "booking_ID", "int" } });

	// "chipcard"
	columns.put("chipcard", new String[][] { { "id", "int" },
		{ "validFrom", "date" }, { "validTo", "date" } });

	// "chipcardlist"
	columns.put("chipcardlist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "chipCard_ID", "int" } });

	// "country"
	columns.put("country", new String[][] { { "id", "int" },
		{ "name", "string" }, { "acronym", "string" } });

	// "employee"
	columns.put("employee", new String[][] { { "id", "int" },
		{ "person_ID", "int" }, { "employeeRole_ID", "int" },
		{ "userName", "string" }, { "password", "string" },
		{ "blocked", "int" }, { "chipCard_ID", "int" } });

	// "employeelist"
	columns.put("employeelist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "employee_ID", "int" } });

	// "employeerole"
	columns.put("employeerole", new String[][] { { "id", "int" },
		{ "labeling", "string" }, { "arrangement", "string" } });

	// "equipment"
	columns.put("equipment", new String[][] { { "id", "int" },
		{ "type", "string" }, { "size", "string" },
		{ "identification", "string" } });

	// "equipmentlist"
	columns.put("equipmentlist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "equipment_ID", "int" } });

	// "extrabooking"
	columns.put("extrabooking", new String[][] { { "id", "int" },
		{ "name", "string" }, { "labeling", "string" },
		{ "site_ID", "int" } });

	// "extrabookinglist"
	columns.put("extrabookinglist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "site_ID", "int" } });

	// "guest"
	columns.put("guest", new String[][] { { "id", "int" },
		{ "person_ID", "int" }, { "visitorsTaxClass_ID", "int" } });

	// "guestlist"
	columns.put("guestlist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "guest_ID", "int" } });

	// "person"
	columns.put("person", new String[][] { { "id", "int" },
		{ "identificationNumber", "string" }, { "name", "string" },
		{ "firstName", "string" }, { "address_ID", "int" },
		{ "dateOfBirth", "date" } });

	// "pitch"
	columns.put("pitch", new String[][] { { "id", "int" },
		{ "district", "string" }, { "type", "string" },
		{ "length", "int" }, { "width", "int" },
		{ "natureOfSoil", "string" }, { "deliveryPoint_ID", "int" },
		{ "characteristics", "string" } });

	// "pitchbooking"
	columns.put("pitchbooking", new String[][] { { "id", "int" },
		{ "pitch_ID", "int" }, { "electricity", "int" } });

	// "pitchbookinglist"
	columns.put("pitchbookinglist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "pitchBooking_ID", "int" } });

	// "pitchlist"
	columns.put("pitchlist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "pitch_ID", "int" } });

	// "service"
	columns.put("service", new String[][] { { "id", "int" },
		{ "pitch_ID", "int" }, { "site_ID", "int" },
		{ "emplyeeRole_ID", "int" }, { "description", "string" },
		{ "creationDate", "date" }, { "priority", "int" },
		{ "doneDate", "date" } });

	// "servicelist"
	columns.put("servicelist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "service_ID", "int" } });

	// "site"
	columns.put("site", new String[][] { { "id", "int" },
		{ "labeling", "string" }, { "type", "string" },
		{ "openingHours", "string" }, { "description", "string" } });

	// "sitelist"
	columns.put("sitelist", new String[][] { { "id", "int" },
		{ "number", "int" }, { "site_ID", "int" } });

	// "town"
	columns.put("town", new String[][] { { "id", "int" }, { "name", "string" },
		{ "postalCode", "string" }, { "country_ID", "int" } });

	// "visitorstaxclass"
	columns.put("visitorstaxclass", new String[][] { { "id", "int" },
		{ "labeling", "string" }, { "price", "float" } });

	// Put all together ("table name" => { "columns" })
	for (final String table : tables) {
	    sqlObjects.put(table, columns.get(table));
	}
    }

    /**
     * Connect to the database.
     * 
     * @param url
     *            where the database is
     * @param user
     *            name of the user login
     * @param password
     *            of the user login
     * @return
     */
    public boolean connect(final String url, final String user,
	    final String password) {

	// url = "jdbc:mysql://http://gieseel.funpic.de/mysql1157678";
	// user = "mysql1157678";
	// password = "blubber1bis3";

	try {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException | IllegalAccessException
		| ClassNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    return false;
	}

	try {

	    conncetion = DriverManager.getConnection(url, user, password);

	    System.out.println("Connected with Database.");

	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * Disconnect from database.
     * 
     * @return
     */
    public boolean disconnect() {
	try {
	    conncetion.close();
	    System.out.println("Disconnected from Database.");
	} catch (final SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * Prepares the {@link Address} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateAddress(final Address obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Address tmpObj : querySelectAddress()) {
		if (obj.getHouseNumber().equals(tmpObj.getHouseNumber())
			&& obj.getStreet().equals(tmpObj.getStreet())
			&& (int) hashObj.get("town_ID") == tmpObj.getTown().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("address", hashObj);
    }

    /**
     * Prepares the {@link Bill} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateBill(final Bill obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Bill tmpObj : querySelectBill()) {
		if (obj.getMultiplier() == tmpObj.getMultiplier()
			&& obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("billItem_ID") == tmpObj.getBillItem().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("bill", hashObj);
    }

    /**
     * Prepares the {@link BillItem} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateBillItem(final BillItem obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final BillItem tmpObj : querySelectBillItem()) {
		if (obj.getLabeling().equals(tmpObj.getLabeling())
			&& obj.getPriceBusySeason().isTheSame(
				tmpObj.getPriceBusySeason())
			&& obj.getPriceLowSeason().isTheSame(
				tmpObj.getPriceLowSeason())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("billitem", hashObj);
    }

    /**
     * Prepares the {@link Booking} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateBooking(final Booking obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Booking tmpObj : querySelectBooking()) {
		if (obj.getFrom().equals(tmpObj.getFrom())
			&& obj.getUntil().equals(tmpObj.getUntil())
			&& (int) hashObj.get("bill_number") == tmpObj.getBill().getId()
			&& (int) hashObj.get("chipCardList_number") == tmpObj.getChipCard().getId()
			&& (int) hashObj.get("equipmentList_number") == tmpObj.getEquipment().getId()
			&& (int) hashObj.get("extraBookingList_number") == tmpObj.getExtraBooking().getId()
			&& (int) hashObj.get("fellowTravelersList_number") == tmpObj.getFellowTravelers().getId()
			&& (int) hashObj.get("pitchBookingList_number") == tmpObj.getPitchBooking().getId()
			&& (int) hashObj.get("responsiblePerson_ID") == tmpObj.getResponsiblePerson().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("booking", hashObj);
    }

    /**
     * Prepares the {@link BookingList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateBookingList(final BookingList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final BookingList tmpObj : querySelectBookingList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("booking_ID") == tmpObj.getBooking().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("bookinglist", hashObj);
    }

    /**
     * Prepares the {@link ChipCard} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateChipCard(final ChipCard obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final ChipCard tmpObj : querySelectChipCard()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& obj.getValidFrom().equals(tmpObj.getValidFrom())
			&& obj.getValidTo().equals(tmpObj.getValidTo())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("chipcard", hashObj);
    }

    /**
     * Prepares the {@link ChipCardList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateChipCardList(final ChipCardList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final ChipCardList tmpObj : querySelectChipCardList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("chipCard_ID") == tmpObj.getChipCard().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("chipcardlist", hashObj);
    }

    /**
     * Prepares the {@link Country} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateCountry(final Country obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Country tmpObj : querySelectCountry()) {
		if (obj.getAcronym().equals(tmpObj.getAcronym())
			&& obj.getName().equals(tmpObj.getName())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("country", hashObj);
    }

    /**
     * Prepares the {@link Employee} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateEmployee(final Employee obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Employee tmpObj : querySelectEmployee()) {
		if (obj.isBlocked() == tmpObj.isBlocked()
			&& obj.getPassword().equals(tmpObj.getPassword())
			&& obj.getUserName().equals(tmpObj.getUserName())
			&& (int) hashObj.get("chipCard_ID") == tmpObj.getChipCard().getId()
			&& (int) hashObj.get("person_ID") == tmpObj.getPerson().getId()
			&& (int) hashObj.get("employeeRole_ID") == tmpObj.getRole().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("employee", hashObj);
    }

    /**
     * Prepares the {@link EmployeeList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateEmployeeList(final EmployeeList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final EmployeeList tmpObj : querySelectEmployeeList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("employee_ID") == tmpObj.getEmployee().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("employeelist", hashObj);
    }

    /**
     * Prepares the {@link EmployeeRole} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateEmployeeRole(final EmployeeRole obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final EmployeeRole tmpObj : querySelectEmployeeRole()) {
		if (obj.getArrangement().equals(tmpObj.getArrangement())
			&& obj.getLabeling().equals(tmpObj.getLabeling())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("employeerole", hashObj);
    }

    /**
     * Prepares the {@link Equipment} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateEquipment(final Equipment obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Equipment tmpObj : querySelectEquipment()) {
		if (obj.getIdentification().equals(tmpObj.getIdentification())
			&& obj.getSize().equals(tmpObj.getSize())
			&& obj.getType().equals(tmpObj.getType())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("equipment", hashObj);
    }

    /**
     * Prepares the {@link EquipmentList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateEquipmentList(final EquipmentList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final EquipmentList tmpObj : querySelectEquipmentList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("equipment_ID") == tmpObj.getEquipment().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("equipmentlist", hashObj);
    }

    /**
     * Prepares the {@link ExtraBooking} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateExtraBooking(final ExtraBooking obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final ExtraBooking tmpObj : querySelectExtraBooking()) {
		if (obj.getLabeling().equals(tmpObj.getLabeling())
			&& obj.getName().equals(tmpObj.getName())
			&& (int) hashObj.get("site_ID") == tmpObj.getSite().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("extrabooking", hashObj);
    }

    /**
     * Prepares the {@link ExtraBookingList} object and saves it in the
     * database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateExtraBookingList(final ExtraBookingList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final ExtraBookingList tmpObj : querySelectExtraBookingList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("site_ID") == tmpObj.getSite().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("extrabookinglist", hashObj);
    }

    /**
     * Prepares the {@link Guest} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateGuest(final Guest obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Guest tmpObj : querySelectGuest()) {
		if ((int) hashObj.get("person_ID") == tmpObj.getPerson().getId()
			&& (int) hashObj.get("visitorsTaxClass_ID") == tmpObj.getVisitorsTaxClass().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("guest", hashObj);
    }

    /**
     * Prepares the {@link GuestList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateGuestList(final GuestList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final GuestList tmpObj : querySelectGuestList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("guest_ID") == tmpObj.getGuest().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("guestlist", hashObj);
    }

    /**
     * Prepares the {@link Person} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdatePerson(final Person obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Person tmpObj : querySelectPerson()) {
		if (obj.getDateOfBirth().equals(tmpObj.getDateOfBirth())
			&& obj.getFirstName().equals(tmpObj.getFirstName())
			&& obj.getIdentificationNumber().equals(
				tmpObj.getIdentificationNumber())
			&& obj.getName().equals(tmpObj.getName())
			&& (int) hashObj.get("address_ID") == tmpObj.getAddress().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("person", hashObj);
    }

    /**
     * Prepares the {@link Pitch} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdatePitch(final Pitch obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Pitch tmpObj : querySelectPitch()) {
		if (obj.getCharacteristics().equals(tmpObj.getCharacteristics())
			&& obj.getDistrict().equals(tmpObj.getDistrict())
			&& obj.getLength() == tmpObj.getLength()
			&& obj.getNatureOfSoil().equals(tmpObj.getNatureOfSoil())
			&& obj.getType().equals(tmpObj.getType())
			&& obj.getWidth() == tmpObj.getWidth()
			&& (int) hashObj.get("deliveryPoint") == tmpObj.getDeliveryPoint().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("pitch", hashObj);
    }

    /**
     * Prepares the {@link PitchBooking} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdatePitchBooking(final PitchBooking obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final PitchBooking tmpObj : querySelectPitchBooking()) {
		if (obj.isElectricity() == tmpObj.isElectricity()
			&& (int) hashObj.get("pitch_ID") == tmpObj.getPitch().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("pitchbooking", hashObj);
    }

    /**
     * Prepares the {@link PitchBookingList} object and saves it in the
     * database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdatePitchBookingList(final PitchBookingList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final PitchBookingList tmpObj : querySelectPitchBookingList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("pitchBooking_ID") == tmpObj.getPitchBooking().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("pitchbookinglist", hashObj);
    }

    /**
     * Prepares the {@link PitchList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdatePitchList(final PitchList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final PitchList tmpObj : querySelectPitchList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("pitch_ID") == tmpObj.getPitch().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("pitchlist", hashObj);
    }

    /**
     * Prepares the {@link Service} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateService(final Service obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Service tmpObj : querySelectService()) {
		if (obj.getCreationDate().equals(tmpObj.getCreationDate())
			&& obj.getDescription().equals(tmpObj.getDescription())
			&& obj.getDoneDate().equals(tmpObj.getDoneDate())
			&& obj.getPriority() == tmpObj.getPriority()
			&& obj.getServiceNumber() == tmpObj.getServiceNumber()
			&& (int) hashObj.get("employeeRole_ID") == tmpObj.getEmployeeRole().getId()
			&& (int) hashObj.get("pitch_ID") == tmpObj.getPitch().getId()
			&& (int) hashObj.get("site_ID") == tmpObj.getSite().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("service", hashObj);
    }

    /**
     * Prepares the {@link ServiceList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateServiceList(final ServiceList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final ServiceList tmpObj : querySelectServiceList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("service_ID") == tmpObj.getService().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("servicelist", hashObj);
    }

    /**
     * Prepares the {@link Site} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateSite(final Site obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Site tmpObj : querySelectSite()) {
		if (obj.getDescription().equals(tmpObj.getDescription())
			&& obj.getLabeling().equals(tmpObj.getLabeling())
			&& obj.getOpeningHours().equals(tmpObj.getOpeningHours())
			&& obj.getType().equals(tmpObj.getType())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("site", hashObj);
    }

    /**
     * Prepares the {@link SiteList} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateSiteList(final SiteList obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final SiteList tmpObj : querySelectSiteList()) {
		if (obj.getNumber() == tmpObj.getNumber()
			&& (int) hashObj.get("site_ID") == tmpObj.getSite().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("sitelist", hashObj);
    }

    /**
     * Prepares the {@link Town} object and saves it in the database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateTown(final Town obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final Town tmpObj : querySelectTown()) {
		if (obj.getName().equals(tmpObj.getName())
			&& obj.getPostalCode().equals(tmpObj.getPostalCode())
			&& (int) hashObj.get("country_ID") == tmpObj.getCountry().getId()) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("town", hashObj);
    }

    /**
     * Prepares the {@link VisitorsTaxClass} object and saves it in the
     * database.
     * 
     * @param obj
     *            is the object
     * @return
     */
    public int queryInsertUpdateVisitorsTaxClass(final VisitorsTaxClass obj) {
	final HashMap<String, Object> hashObj = obj.getHashMap();
	if (obj.getId() == 0) {
	    // Check if object already exists
	    for (final VisitorsTaxClass tmpObj : querySelectVisitorsTaxClass()) {
		if (obj.getLabeling().equals(tmpObj.getLabeling())
			&& obj.getPrice().isTheSame(tmpObj.getPrice())) {
		    // If object exists return the ID of the object
		    return tmpObj.getId();
		}
	    }
	}

	// Insert or update the object
	return queryInsertUpdate("visitorstaxclass", hashObj);
    }

    /**
     * Returns a list of {@link Address} objects.
     * 
     * @return
     */
    public List<Address> querySelectAddress() {
	final List<Address> objectList = new ArrayList<Address>();
	for (final HashMap<String, Object> object : querySelect("address")) {
	    objectList.add(new Address(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Address} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Address querySelectAddress(final int id) {
	return new Address(querySelect("address", id));
    }

    /**
     * Returns a list of {@link Bill} objects.
     * 
     * @return
     */
    public List<Bill> querySelectBill() {
	final List<Bill> objectList = new ArrayList<Bill>();
	for (final HashMap<String, Object> object : querySelect("bill")) {
	    objectList.add(new Bill(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Bill} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Bill querySelectBill(final int id) {
	return new Bill(querySelect("bill", id));
    }

    /**
     * Returns a list of {@link BillItem} objects.
     * 
     * @return
     */
    public List<BillItem> querySelectBillItem() {
	final List<BillItem> objectList = new ArrayList<BillItem>();
	for (final HashMap<String, Object> object : querySelect("billitem")) {
	    objectList.add(new BillItem(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link BillItem} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public BillItem querySelectBillItem(final int id) {
	return new BillItem(querySelect("billitem", id));
    }

    /**
     * Returns a list of {@link Booking} objects.
     * 
     * @return
     */
    public List<Booking> querySelectBooking() {
	final List<Booking> objectList = new ArrayList<Booking>();
	for (final HashMap<String, Object> object : querySelect("booking")) {
	    objectList.add(new Booking(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Booking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Booking querySelectBooking(final int id) {
	return new Booking(querySelect("booking", id));
    }

    /**
     * Returns a list of {@link BookingList} objects.
     * 
     * @return
     */
    public List<BookingList> querySelectBookingList() {
	final List<BookingList> objectList = new ArrayList<BookingList>();
	for (final HashMap<String, Object> object : querySelect("bookinglist")) {
	    objectList.add(new BookingList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link BookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public BookingList querySelectBookingList(final int id) {
	return new BookingList(querySelect("bookinglist", id));
    }

    /**
     * Returns a list of {@link ChipCard} objects.
     * 
     * @return
     */
    public List<ChipCard> querySelectChipCard() {
	final List<ChipCard> objectList = new ArrayList<ChipCard>();
	for (final HashMap<String, Object> object : querySelect("chipcard")) {
	    objectList.add(new ChipCard(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link ChipCard} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ChipCard querySelectChipCard(final int id) {
	return new ChipCard(querySelect("chipcard", id));
    }

    /**
     * Returns a list of {@link ChipCardList} objects.
     * 
     * @return
     */
    public List<ChipCardList> querySelectChipCardList() {
	final List<ChipCardList> objectList = new ArrayList<ChipCardList>();
	for (final HashMap<String, Object> object : querySelect("chipcardlist")) {
	    objectList.add(new ChipCardList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link ChipCardList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ChipCardList querySelectChipCardList(final int id) {
	return new ChipCardList(querySelect("chipcardlist", id));
    }

    /**
     * Returns a list of {@link Country} objects.
     * 
     * @return
     */
    public List<Country> querySelectCountry() {
	final List<Country> objectList = new ArrayList<Country>();
	for (final HashMap<String, Object> object : querySelect("country")) {
	    objectList.add(new Country(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Country} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Country querySelectCountry(final int id) {
	return new Country(querySelect("country", id));
    }

    /**
     * Returns a list of {@link Employee} objects.
     * 
     * @return
     */
    public List<Employee> querySelectEmployee() {
	final List<Employee> objectList = new ArrayList<Employee>();
	for (final HashMap<String, Object> object : querySelect("employee")) {
	    objectList.add(new Employee(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Employee} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Employee querySelectEmployee(final int id) {
	return new Employee(querySelect("employee", id));
    }

    /**
     * Returns a list of {@link EmployeeList} objects.
     * 
     * @return
     */
    public List<EmployeeList> querySelectEmployeeList() {
	final List<EmployeeList> objectList = new ArrayList<EmployeeList>();
	for (final HashMap<String, Object> object : querySelect("employeelist")) {
	    objectList.add(new EmployeeList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link EmployeeList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EmployeeList querySelectEmployeeList(final int id) {
	return new EmployeeList(querySelect("employeelist", id));
    }

    /**
     * Returns a list of {@link EmployeeRole} objects.
     * 
     * @return
     */
    public List<EmployeeRole> querySelectEmployeeRole() {
	final List<EmployeeRole> objectList = new ArrayList<EmployeeRole>();
	for (final HashMap<String, Object> object : querySelect("employeerole")) {
	    objectList.add(new EmployeeRole(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link EmployeeRole} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EmployeeRole querySelectEmployeeRole(final int id) {
	return new EmployeeRole(querySelect("employeerole", id));
    }

    /**
     * Returns a list of {@link Equipment} objects.
     * 
     * @return
     */
    public List<Equipment> querySelectEquipment() {
	final List<Equipment> objectList = new ArrayList<Equipment>();
	for (final HashMap<String, Object> object : querySelect("equipment")) {
	    objectList.add(new Equipment(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Equipment} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Equipment querySelectEquipment(final int id) {
	return new Equipment(querySelect("equipment", id));
    }

    /**
     * Returns a list of {@link EquipmentList} objects.
     * 
     * @return
     */
    public List<EquipmentList> querySelectEquipmentList() {
	final List<EquipmentList> objectList = new ArrayList<EquipmentList>();
	for (final HashMap<String, Object> object : querySelect("equipmentlist")) {
	    objectList.add(new EquipmentList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link EquipmentList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EquipmentList querySelectEquipmentList(final int id) {
	return new EquipmentList(querySelect("equipmentlist", id));
    }

    /**
     * Returns a list of {@link ExtraBooking} objects.
     * 
     * @return
     */
    public List<ExtraBooking> querySelectExtraBooking() {
	final List<ExtraBooking> objectList = new ArrayList<ExtraBooking>();
	for (final HashMap<String, Object> object : querySelect("extrabooking")) {
	    objectList.add(new ExtraBooking(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link ExtraBooking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ExtraBooking querySelectExtraBooking(final int id) {
	return new ExtraBooking(querySelect("extrabooking", id));
    }

    /**
     * Returns a list of {@link ExtraBookingList} objects.
     * 
     * @return
     */
    public List<ExtraBookingList> querySelectExtraBookingList() {
	final List<ExtraBookingList> objectList = new ArrayList<ExtraBookingList>();
	for (final HashMap<String, Object> object : querySelect("extrabookinglist")) {
	    objectList.add(new ExtraBookingList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link ExtraBookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ExtraBookingList querySelectExtraBookingList(final int id) {
	return new ExtraBookingList(querySelect("extrabookinglist", id));
    }

    /**
     * Returns a list of {@link Guest} objects.
     * 
     * @return
     */
    public List<Guest> querySelectGuest() {
	final List<Guest> objectList = new ArrayList<Guest>();
	for (final HashMap<String, Object> object : querySelect("guest")) {
	    objectList.add(new Guest(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Guest} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Guest querySelectGuest(final int id) {
	return new Guest(querySelect("guest", id));
    }

    /**
     * Returns a list of {@link GuestList} objects.
     * 
     * @return
     */
    public List<GuestList> querySelectGuestList() {
	final List<GuestList> objectList = new ArrayList<GuestList>();
	for (final HashMap<String, Object> object : querySelect("guestlist")) {
	    objectList.add(new GuestList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link GuestList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public GuestList querySelectGuestList(final int id) {
	return new GuestList(querySelect("guestlist", id));
    }

    /**
     * Returns a list of {@link Person} objects.
     * 
     * @return
     */
    public List<Person> querySelectPerson() {
	final List<Person> objectList = new ArrayList<Person>();
	for (final HashMap<String, Object> object : querySelect("person")) {
	    objectList.add(new Person(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Person} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Person querySelectPerson(final int id) {
	return new Person(querySelect("person", id));
    }

    /**
     * Returns a list of {@link Pitch} objects.
     * 
     * @return
     */
    public List<Pitch> querySelectPitch() {
	final List<Pitch> objectList = new ArrayList<Pitch>();
	for (final HashMap<String, Object> object : querySelect("pitch")) {
	    objectList.add(new Pitch(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Pitch} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Pitch querySelectPitch(final int id) {
	return new Pitch(querySelect("pitch", id));
    }

    /**
     * Returns a list of {@link PitchBooking} objects.
     * 
     * @return
     */
    public List<PitchBooking> querySelectPitchBooking() {
	final List<PitchBooking> objectList = new ArrayList<PitchBooking>();
	for (final HashMap<String, Object> object : querySelect("pitchbooking")) {
	    objectList.add(new PitchBooking(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link PitchBooking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchBooking querySelectPitchBooking(final int id) {
	return new PitchBooking(querySelect("pitchbooking", id));
    }

    /**
     * Returns a list of {@link PitchBookingList} objects.
     * 
     * @return
     */
    public List<PitchBookingList> querySelectPitchBookingList() {
	final List<PitchBookingList> objectList = new ArrayList<PitchBookingList>();
	for (final HashMap<String, Object> object : querySelect("pitchbookinglist")) {
	    objectList.add(new PitchBookingList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link PitchBookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchBookingList querySelectPitchBookingList(final int id) {
	return new PitchBookingList(querySelect("pitchbookinglist", id));
    }

    /**
     * Returns a list of {@link PitchList} objects.
     * 
     * @return
     */
    public List<PitchList> querySelectPitchList() {
	final List<PitchList> objectList = new ArrayList<PitchList>();
	for (final HashMap<String, Object> object : querySelect("pitchlist")) {
	    objectList.add(new PitchList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link PitchList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchList querySelectPitchList(final int id) {
	return new PitchList(querySelect("pitchlist", id));
    }

    /**
     * Returns a list of {@link Service} objects.
     * 
     * @return
     */
    public List<Service> querySelectService() {
	final List<Service> objectList = new ArrayList<Service>();
	for (final HashMap<String, Object> object : querySelect("service")) {
	    objectList.add(new Service(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Service} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Service querySelectService(final int id) {
	return new Service(querySelect("service", id));
    }

    /**
     * Returns a list of {@link ServiceList} objects.
     * 
     * @return
     */
    public List<ServiceList> querySelectServiceList() {
	final List<ServiceList> objectList = new ArrayList<ServiceList>();
	for (final HashMap<String, Object> object : querySelect("servicelist")) {
	    objectList.add(new ServiceList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link ServiceList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ServiceList querySelectServiceList(final int id) {
	return new ServiceList(querySelect("servicelist", id));
    }

    /**
     * Returns a list of {@link Site} objects.
     * 
     * @return
     */
    public List<Site> querySelectSite() {
	final List<Site> objectList = new ArrayList<Site>();
	for (final HashMap<String, Object> object : querySelect("site")) {
	    objectList.add(new Site(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Site} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Site querySelectSite(final int id) {
	return new Site(querySelect("site", id));
    }

    /**
     * Returns a list of {@link SiteList} objects.
     * 
     * @return
     */
    public List<SiteList> querySelectSiteList() {
	final List<SiteList> objectList = new ArrayList<SiteList>();
	for (final HashMap<String, Object> object : querySelect("sitelist")) {
	    objectList.add(new SiteList(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link SiteList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public SiteList querySelectSiteList(final int id) {
	return new SiteList(querySelect("sitelist", id));
    }

    /**
     * Returns a list of {@link Town} objects.
     * 
     * @return
     */
    public List<Town> querySelectTown() {
	final List<Town> objectList = new ArrayList<Town>();
	for (final HashMap<String, Object> object : querySelect("town")) {
	    objectList.add(new Town(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link Town} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Town querySelectTown(final int id) {
	return new Town(querySelect("town", id));
    }

    /**
     * Returns a list of {@link VisitorsTaxClass} objects.
     * 
     * @return
     */
    public List<VisitorsTaxClass> querySelectVisitorsTaxClass() {
	final List<VisitorsTaxClass> objectList = new ArrayList<VisitorsTaxClass>();
	for (final HashMap<String, Object> object : querySelect("visitorstaxclass")) {
	    objectList.add(new VisitorsTaxClass(object));
	}
	return objectList;
    }

    /**
     * Returns one {@link VisitorsTaxClass} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public VisitorsTaxClass querySelectVisitorsTaxClass(final int id) {
	return new VisitorsTaxClass(querySelect("visitorstaxclass", id));
    }

    /**
     * Inserts or updates a object in the given table.
     * 
     * @param table
     *            is the table
     * @param data
     *            is the object
     * @return
     */
    private int queryInsertUpdate(final String table,
	    final HashMap<String, Object> data) {
	final String[][] entries = sqlObjects.get(table);
	int id = (Integer) data.get(entries[0][0]);
	final boolean insert = (id == 0 ? true : false);
	PreparedStatement statement;
	String query;
	if (insert) {
	    // Prepare INSERT query
	    query = "INSERT INTO " + table + "(";
	    query += entries[1][0];
	    for (int i = 2; i < entries.length; i++) {
		query += ", " + entries[i][0];
	    }
	    query += ") VALUES (";
	    query += "?";
	    for (int i = 2; i < entries.length; i++) {
		query += ", ?";
	    }
	    query += ");";
	} else {
	    // Prepare UPDATE query
	    query = "UPDATE " + table + " SET ";
	    query += entries[1][0] + "=?";
	    for (int i = 2; i < entries.length; i++) {
		query += ", " + entries[i][0] + "=?";
	    }
	    query += " WHERE " + entries[0][0] + "=?;";
	}
	try {
	    statement = conncetion.prepareStatement(query);
	    if (insert) {
		statement = conncetion.prepareStatement(query,
			Statement.RETURN_GENERATED_KEYS);
	    }
	    int i = 1;
	    for (; i < entries.length; i++) {
		if (entries[i][1].equals("string")) {
		    statement.setString(i, (String) data.get(entries[i][0]));
		} else if (entries[i][1].equals("int")) {
		    statement.setInt(i, (Integer) data.get(entries[i][0]));
		} else if (entries[i][1].equals("float")) {
		    statement.setFloat(i, (Float) data.get(entries[i][0]));
		} else if (entries[i][1].equals("date")) {
		    statement.setTimestamp(
			    i,
			    new Timestamp(
				    ((Date) data.get(entries[i][0])).getTime()));
		} else {
		    // TODO throw Unerwarteter Typ!
		}
	    }
	    if (!insert) {
		statement.setInt(i, id);
	    }
	    statement.executeUpdate();

	    if (insert) {
		final ResultSet result = statement.getGeneratedKeys();
		if (result.next()) {
		    id = result.getInt(1);// entries[0][0]);
		} else {
		    // TODO throw Beim einfügen des Eintrags ist ein Fehler
		    // aufgetreten!
		}
	    }

	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Returns all entries of the given table.
     * 
     * @param table
     *            is the table
     * @return
     */
    private List<HashMap<String, Object>> querySelect(final String table) {
	return querySelectAll(table, 0);
    }

    /**
     * Returns one entry of the given table.
     * 
     * @param table
     *            is the table
     * @param id
     *            is the id of the entry
     * @return
     */
    private HashMap<String, Object> querySelect(final String table, final int id) {
	return querySelectAll(table, id).get(0);
    }

    /**
     * Returns a list of all entries of the given table.
     * 
     * @param table
     *            is the table
     * @param id
     *            is the id of the entry (is 0 when all entries are wanted)
     * @return
     */
    private List<HashMap<String, Object>> querySelectAll(final String table,
	    final int id) {
	final String[][] entries = sqlObjects.get(table);
	PreparedStatement statement;
	final String query = "SELECT * FROM " + table + " "
		+ (id == 0 ? ";" : " WHERE " + entries[0][0] + "='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<HashMap<String, Object>> ret = new ArrayList<HashMap<String, Object>>();
	    HashMap<String, Object> tmp_list;

	    while (result.next()) {
		tmp_list = new HashMap<String, Object>();
		for (final String[] entry : entries) {
		    if (entry[1].equals("string")) {
			tmp_list.put(entry[0],
				new String(result.getString(entry[0])));
		    } else if (entry[1].equals("int")) {
			tmp_list.put(entry[0], new Integer(result.getInt(entry[0])));
		    } else if (entry[1].equals("float")) {
			tmp_list.put(entry[0], new Float(result.getFloat(entry[0])));
		    } else if (entry[1].equals("date")) {
			tmp_list.put(entry[0],
				new Date(result.getTimestamp(entry[0]).getTime()));
		    } else {
			// TODO throw Unerwarteter Typ!
		    }
		}
		ret.add(tmp_list);
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    private Connection conncetion;
    private final HashMap<String, String[][]> sqlObjects;
}
