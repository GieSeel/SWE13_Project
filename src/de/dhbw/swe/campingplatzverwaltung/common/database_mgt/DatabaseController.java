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
     * Inserts an {@link Address} object into database.
     * 
     * @param address
     *            is the object
     * @return
     */
    public int queryInsertAddress(final Address address) {
	return (address.getId() == 0 ? queryInsertAddress(address.getStreet(),
		address.getHouseNumber(), queryInsertTown(address.getTown()))
		: address.getId());
    }

    /**
     * Inserts a {@link Bill} object into database.
     * 
     * @param bill
     *            is the object
     * @return
     */
    public int queryInsertBill(final Bill bill) {
	return (bill.getId() == 0 ? queryInsertBill(bill.getNumber(),
		queryInsertBillitem(bill.getBillItem()), bill.getMultiplier())
		: bill.getId());
    }

    /**
     * Inserts a {@link BillItem} object into database.
     * 
     * @param billItem
     *            is the object
     * @return
     */
    public int queryInsertBillitem(final BillItem billItem) {
	return (billItem.getId() == 0 ? queryInsertBillitem(billItem.getLabeling(),
		billItem.getPriceBusySeason(), billItem.getPriceLowSeason())
		: billItem.getId());

    }

    /**
     * Inserts a {@link Booking} object into database.
     * 
     * @param booking
     *            is the object
     * @return
     */
    public int queryInsertBooking(final Booking booking) {
	return (booking.getId() == 0 ? queryInsertBooking(
		queryInsertGuest(booking.getResponsiblePerson()),
		queryInsertGuestList(booking.getFellowTravelers()),
		booking.getFrom(), booking.getUntil(),
		queryInsertEquipmentList(booking.getEquipment()),
		queryInsertExtraBookingList(booking.getExtraBooking()),
		queryInsertPitchBookingList(booking.getPitchBooking()),
		queryInsertBill(booking.getBill()),
		queryInsertChipCardList(booking.getChipCard())) : booking.getId());
    }

    /**
     * Inserts a {@link BookingList} object into database.
     * 
     * @param bookingList
     *            is the object
     * @return
     */
    public int queryInsertBookingList(final BookingList bookingList) {
	return (bookingList.getId() == 0 ? queryInsertBookinglist(
		bookingList.getNumber(),
		queryInsertBooking(bookingList.getBooking())) : bookingList.getId());
    }

    /**
     * Inserts a {@link ChipCard} object into database.
     * 
     * @param chipCard
     *            is the object
     * @return
     */
    public int queryInsertChipCard(final ChipCard chipCard) {
	return (chipCard.getId() == 0 ? queryInsertChipcard(
		chipCard.getValidFrom(), chipCard.getValidTo()) : chipCard.getId());
    }

    /**
     * Inserts a {@link ChipCardList} object into database.
     * 
     * @param chipCardList
     *            is the object
     * @return
     */
    public int queryInsertChipCardList(final ChipCardList chipCardList) {
	return (chipCardList.getId() == 0 ? queryInsertChipcardlist(
		chipCardList.getNumber(),
		queryInsertChipCard(chipCardList.getChipCard()))
		: chipCardList.getId());
    }

    /**
     * Inserts a {@link Country} object into database.
     * 
     * @param country
     *            is the object
     * @return
     */
    public int queryInsertCountry(final Country country) {
	return (country.getId() == 0 ? queryInsertCountry(country.getName(),
		country.getAcronym()) : country.getId());
    }

    /**
     * Inserts a {@link Employee} object into database.
     * 
     * @param employee
     *            is the object
     * @return
     */
    public int queryInsertEmployee(final Employee employee) {
	return (employee.getId() == 0 ? queryInsertEmployee(
		queryInsertPerson(employee.getPerson()),
		queryInsertEmployeeRole(employee.getRole()),
		employee.getUserName(), employee.getPassword(),
		employee.isBlocked(), queryInsertChipCard(employee.getChipCard()))
		: employee.getId());
    }

    /**
     * Inserts {@link Employee} data into database.
     * 
     * @param number
     *            of the list
     * @param employee_ID
     *            is the id of the {@link Site}
     * @return
     */
    public int queryInsertEmployeelist(final int number, final int employee_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "bookinglist(number, employee_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, employee_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts a {@link EmployeeList} object into database.
     * 
     * @param employeeList
     *            is the object
     * @return
     */
    public int queryInsertEmployeeList(final EmployeeList employeeList) {
	return (employeeList.getId() == 0 ? queryInsertEmployeelist(
		employeeList.getNumber(),
		queryInsertEmployee(employeeList.getEmployee()))
		: employeeList.getId());
    }

    /**
     * Inserts a {@link EmployeeRole} object into database.
     * 
     * @param employeeRole
     *            is the object
     * @return
     */
    public int queryInsertEmployeeRole(final EmployeeRole employeeRole) {
	return (employeeRole.getId() == 0 ? queryInsertEmployeerole(
		employeeRole.getLabeling(), employeeRole.getArrangement())
		: employeeRole.getId());
    }

    /**
     * Inserts a {@link Equipment} object into database.
     * 
     * @param equipment
     *            is the object
     * @return
     */
    public int queryInsertEquipment(final Equipment equipment) {
	return (equipment.getId() == 0 ? queryInsertEquipment(equipment.getType(),
		equipment.getSize(), equipment.getIdentification())
		: equipment.getId());
    }

    /**
     * Inserts a {@link EquipmentList} object into database.
     * 
     * @param equipmentList
     *            is the object
     * @return
     */
    public int queryInsertEquipmentList(final EquipmentList equipmentList) {
	return (equipmentList.getId() == 0 ? queryInsertEquipmentlist(
		equipmentList.getNumber(),
		queryInsertEquipment(equipmentList.getEquipment()))
		: equipmentList.getId());
    }

    /**
     * Inserts a {@link ExtraBooking} object into database.
     * 
     * @param extraBooking
     *            is the object
     * @return
     */
    public int queryInsertExtraBooking(final ExtraBooking extraBooking) {
	return (extraBooking.getId() == 0 ? queryInsertExtrabooking(
		extraBooking.getName(), extraBooking.getLabeling(),
		queryInsertSite(extraBooking.getSite())) : extraBooking.getId());
    }

    /**
     * Inserts a {@link ExtraBookingList} object into database.
     * 
     * @param extraBookingList
     *            is the object
     * @return
     */
    public int queryInsertExtraBookingList(final ExtraBookingList extraBookingList) {
	return (extraBookingList.getId() == 0 ? queryInsertExtrabookinglist(
		extraBookingList.getNumber(),
		queryInsertSite(extraBookingList.getSite()))
		: extraBookingList.getId());
    }

    /**
     * Inserts a {@link Guest} object into database.
     * 
     * @param guest
     *            is the object
     * @return
     */
    public int queryInsertGuest(final Guest guest) {
	return (guest.getId() == 0 ? queryInsertGuest(
		queryInsertPerson(guest.getPerson()),
		queryInsertVisitorsTaxClass(guest.getVisitorsTaxClass()))
		: guest.getId());
    }

    /**
     * Inserts a {@link GuestList} object into database.
     * 
     * @param guestList
     *            is the object
     * @return
     */
    public int queryInsertGuestList(final GuestList guestList) {
	return (guestList.getId() == 0 ? queryInsertGuestlist(
		guestList.getNumber(), queryInsertGuest(guestList.getGuest()))
		: guestList.getId());
    }

    /**
     * Inserts a {@link Person} object into database.
     * 
     * @param person
     *            is the object
     * @return
     */
    public int queryInsertPerson(final Person person) {
	return (person.getId() == 0 ? queryInsertPerson(
		person.getIdentificationNumber(), person.getName(),
		person.getFirstName(), queryInsertAddress(person.getAddress()),
		person.getDateOfBirth()) : person.getId());
    }

    /**
     * Inserts a {@link Pitch} object into database.
     * 
     * @param pitch
     *            is the object
     * @return
     */
    public int queryInsertPitch(final Pitch pitch) {
	return (pitch.getId() == 0 ? queryInsertPitch(pitch.getDistrict(),
		pitch.getType(), pitch.getLength(), pitch.getWidth(),
		pitch.getNatureOfSoil(), queryInsertSite(pitch.getDeliveryPoint()),
		pitch.getCharacteristics()) : pitch.getId());
    }

    /**
     * Inserts a {@link PitchBooking} object into database.
     * 
     * @param pitchBooking
     *            is the object
     * @return
     */
    public int queryInsertPitchBooking(final PitchBooking pitchBooking) {
	return (pitchBooking.getId() == 0 ? queryInsertPitchbooking(
		queryInsertPitch(pitchBooking.getPitch()),
		pitchBooking.isElectricity()) : pitchBooking.getId());
    }

    /**
     * Inserts a {@link PitchBookingList} object into database.
     * 
     * @param pitchBookingList
     *            is the object
     * @return
     */
    public int queryInsertPitchBookingList(final PitchBookingList pitchBookingList) {
	return (pitchBookingList.getId() == 0 ? queryInsertPitchbookinglist(
		pitchBookingList.getNumber(),
		queryInsertPitchBooking(pitchBookingList.getPitchBooking()))
		: pitchBookingList.getId());
    }

    /**
     * Inserts a {@link PitchList} object into database.
     * 
     * @param pitchList
     *            is the object
     * @return
     */
    public int queryInsertPitchList(final PitchList pitchList) {
	return (pitchList.getId() == 0 ? queryInsertPitchlist(
		pitchList.getNumber(), queryInsertPitch(pitchList.getPitch()))
		: pitchList.getId());
    }

    /**
     * Inserts a {@link Service} object into database.
     * 
     * @param service
     *            is the object
     * @return
     */
    public int queryInsertService(final Service service) {
	return (service.getId() == 0 ? queryInsertService(
		queryInsertPitch(service.getPitch()),
		queryInsertSite(service.getSite()),
		queryInsertEmployeeRole(service.getEmployeeRole()),
		service.getDescription(), service.getCreationDate(),
		service.getPriority(), service.getDoneDate()) : service.getId());
    }

    /**
     * Inserts a {@link ServiceList} object into database.
     * 
     * @param serviceList
     *            is the object
     * @return
     */
    public int queryInsertServiceList(final ServiceList serviceList) {
	return (serviceList.getId() == 0 ? queryInsertServicelist(
		serviceList.getNumber(),
		queryInsertService(serviceList.getService())) : serviceList.getId());
    }

    /**
     * Inserts a {@link Site} object into database.
     * 
     * @param site
     *            is the object
     * @return
     */
    public int queryInsertSite(final Site site) {
	return (site.getId() == 0 ? queryInsertSite(site.getLabeling(),
		site.getType(), site.getOpeningHours(), site.getDescription())
		: site.getId());
    }

    /**
     * Inserts a {@link SiteList} object into database.
     * 
     * @param siteList
     *            is the object
     * @return
     */
    public int queryInsertSiteList(final SiteList siteList) {
	return (siteList.getId() == 0 ? queryInsertSitelist(siteList.getNumber(),
		queryInsertSite(siteList.getSite())) : siteList.getId());
    }

    /**
     * Inserts a {@link Town} object into database.
     * 
     * @param town
     *            is the object
     * @return
     */
    public int queryInsertTown(final Town town) {
	return (town.getId() == 0 ? queryInsertTown(town.getName(),
		town.getPostalCode(), queryInsertCountry(town.getCountry()))
		: town.getId());
    }

    /**
     * Inserts a {@link VisitorsTaxClass} object into database.
     * 
     * @param visitorsTaxClass
     *            is the object
     * @return
     */
    public int queryInsertVisitorsTaxClass(final VisitorsTaxClass visitorsTaxClass) {
	return (visitorsTaxClass.getId() == 0 ? queryInsertVisitorstaxclass(
		visitorsTaxClass.getLabeling(), visitorsTaxClass.getPrice())
		: visitorsTaxClass.getId());
    }

    /**
     * Returns a list of all {@link Address}es.
     * 
     * @return
     */
    public List<Address> querySelectAddress() {
	return querySelectAddresses(0);
    }

    /**
     * Returns one {@link Address} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Address querySelectAddress(final int id) {
	return querySelectAddresses(id).get(0);
    }

    /**
     * Returns a list of all {@link Bill}s.
     * 
     * @return
     */
    public List<Bill> querySelectBill() {
	return querySelectBills(0);
    }

    /**
     * Returns one {@link Bill} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Bill querySelectBill(final int id) {
	return querySelectBills(id).get(0);
    }

    /**
     * Returns a list of all {@link BillItem}s.
     * 
     * @return
     */
    public List<BillItem> querySelectBillitem() {
	return querySelectBillitems(0);
    }

    /**
     * Returns one {@link BillItem} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public BillItem querySelectBillitem(final int id) {
	return querySelectBillitems(id).get(0);
    }

    /**
     * Returns a list of all {@link Booking}s.
     * 
     * @return
     */
    public List<Booking> querySelectBooking() {
	return querySelectBookings(0);
    }

    /**
     * Returns one {@link Booking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Booking querySelectBooking(final int id) {
	return querySelectBookings(id).get(0);
    }

    /**
     * Returns a list of all {@link BookingList}s.
     * 
     * @return
     */
    public List<BookingList> querySelectBookinglist() {
	return querySelectBookinglists(0);
    }

    /**
     * Returns one {@link BookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public BookingList querySelectBookinglist(final int id) {
	return querySelectBookinglists(id).get(0);
    }

    /**
     * Returns a list of all {@link ChipCard}s.
     * 
     * @return
     */
    public List<ChipCard> querySelectChipcard() {
	return querySelectChipcards(0);
    }

    /**
     * Returns one {@link ChipCard} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ChipCard querySelectChipcard(final int id) {
	return querySelectChipcards(id).get(0);
    }

    /**
     * Returns a list of all {@link ChipCardList}s.
     * 
     * @return
     */
    public List<ChipCardList> querySelectChipcardlist() {
	return querySelectChipcardlists(0);
    }

    /**
     * Returns one {@link ChipCardList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ChipCardList querySelectChipcardlist(final int id) {
	return querySelectChipcardlists(id).get(0);
    }

    /**
     * Returns a list of all {@link Country}s.
     * 
     * @return
     */
    public List<Country> querySelectCountry() {
	return querySelectCountrys(0);
    }

    /**
     * Returns one {@link Country} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Country querySelectCountry(final int id) {
	return querySelectCountrys(id).get(0);
    }

    /**
     * Returns a list of all {@link Employee}s.
     * 
     * @return
     */
    public List<Employee> querySelectEmployee() {
	return querySelectEmployees(0);
    }

    /**
     * Returns one {@link Employee} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Employee querySelectEmployee(final int id) {
	return querySelectEmployees(id).get(0);
    }

    /**
     * Returns a list of all {@link EmployeeList}s.
     * 
     * @return
     */
    public List<EmployeeList> querySelectEmployeelist() {
	return querySelectEmployeelists(0);
    }

    /**
     * Returns one {@link EmployeeList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EmployeeList querySelectEmployeelist(final int id) {
	return querySelectEmployeelists(id).get(0);
    }

    /**
     * Returns a list of all {@link EmployeeRole}s.
     * 
     * @return
     */
    public List<EmployeeRole> querySelectEmployeerole() {
	return querySelectEmployeeroles(0);

    }

    /**
     * Returns one {@link EmployeeRole} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EmployeeRole querySelectEmployeerole(final int id) {
	return querySelectEmployeeroles(id).get(0);

    }

    /**
     * Returns a list of all {@link Equipment}s.
     * 
     * @return
     */
    public List<Equipment> querySelectEquipment() {
	return querySelectEquipments(0);
    }

    /**
     * Returns one {@link Equipment} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Equipment querySelectEquipment(final int id) {
	return querySelectEquipments(id).get(0);
    }

    /**
     * Returns a list of all {@link EquipmentList}s.
     * 
     * @return
     */
    public List<EquipmentList> querySelectEquipmentlist() {
	return querySelectEquipmentlists(0);
    }

    /**
     * Returns one {@link EquipmentList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public EquipmentList querySelectEquipmentlist(final int id) {
	return querySelectEquipmentlists(id).get(0);
    }

    /**
     * Returns a list of all {@link ExtraBooking}s.
     * 
     * @return
     */
    public List<ExtraBooking> querySelectExtrabooking() {
	return querySelectExtrabookings(0);
    }

    /**
     * Returns one {@link ExtraBooking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ExtraBooking querySelectExtrabooking(final int id) {
	return querySelectExtrabookings(id).get(0);
    }

    /**
     * Returns a list of all {@link ExtraBookingList}s.
     * 
     * @return
     */
    public List<ExtraBookingList> querySelectExtrabookinglist() {
	return querySelectExtrabookinglists(0);
    }

    /**
     * Returns one {@link ExtraBookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ExtraBookingList querySelectExtrabookinglist(final int id) {
	return querySelectExtrabookinglists(id).get(0);
    }

    /**
     * Returns a list of all {@link Guest}s.
     * 
     * @return
     */
    public List<Guest> querySelectGuest() {
	return querySelectGuests(0);
    }

    /**
     * Returns one {@link Guest} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Guest querySelectGuest(final int id) {
	return querySelectGuests(id).get(0);
    }

    /**
     * Returns a list of all {@link GuestList}s.
     * 
     * @return
     */
    public List<GuestList> querySelectGuestlist() {
	return querySelectGuestlists(0);
    }

    /**
     * Returns one {@link GuestList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public GuestList querySelectGuestlist(final int id) {
	return querySelectGuestlists(id).get(0);
    }

    /**
     * Returns a list of all {@link Person}s.
     * 
     * @return
     */
    public List<Person> querySelectPerson() {
	return querySelectPersons(0);
    }

    /**
     * Returns one {@link Person} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Person querySelectPerson(final int id) {
	return querySelectPersons(id).get(0);
    }

    /**
     * Returns a list of all {@link Pitch}es.
     * 
     * @return
     */
    public List<Pitch> querySelectPitch() {
	return querySelectPitchs(0);
    }

    /**
     * Returns one {@link Pitch} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Pitch querySelectPitch(final int id) {
	return querySelectPitchs(id).get(0);
    }

    /**
     * Returns a list of all {@link PitchBooking}s.
     * 
     * @return
     */
    public List<PitchBooking> querySelectPitchbooking() {
	return querySelectPitchbookings(0);
    }

    /**
     * Returns one {@link PitchBooking} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchBooking querySelectPitchbooking(final int id) {
	return querySelectPitchbookings(id).get(0);
    }

    /**
     * Returns a list of all {@link PitchBookingList}s.
     * 
     * @return
     */
    public List<PitchBookingList> querySelectPitchbookinglist() {
	return querySelectPitchbookinglists(0);
    }

    /**
     * Returns one {@link PitchBookingList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchBookingList querySelectPitchbookinglist(final int id) {
	return querySelectPitchbookinglists(id).get(0);
    }

    /**
     * Returns a list of all {@link PitchList}s.
     * 
     * @return
     */
    public List<PitchList> querySelectPitchlist() {
	return querySelectPitchlists(0);
    }

    /**
     * Returns one {@link PitchList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public PitchList querySelectPitchlist(final int id) {
	return querySelectPitchlists(id).get(0);
    }

    /**
     * Returns a list of all {@link Service}es.
     * 
     * @return
     */
    public List<Service> querySelectService() {
	return querySelectServices(0);
    }

    /**
     * Returns one {@link Service} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Service querySelectService(final int id) {
	return querySelectServices(id).get(0);
    }

    /**
     * Returns a list of all {@link ServiceList}s.
     * 
     * @return
     */
    public List<ServiceList> querySelectServicelist() {
	return querySelectServicelists(0);
    }

    /**
     * Returns one {@link ServiceList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public ServiceList querySelectServicelist(final int id) {
	return querySelectServicelists(id).get(0);
    }

    /**
     * Returns a list of all {@link Site}s.
     * 
     * @return
     */
    public List<Site> querySelectSite() {
	return querySelectSites(0);
    }

    /**
     * Returns one {@link Site} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Site querySelectSite(final int id) {
	return querySelectSites(id).get(0);
    }

    /**
     * Returns a list of all {@link SiteList}s.
     * 
     * @return
     */
    public List<SiteList> querySelectSitelist() {
	return querySelectSitelists(0);
    }

    /**
     * Returns one {@link SiteList} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public SiteList querySelectSitelist(final int id) {
	return querySelectSitelists(id).get(0);
    }

    /**
     * Returns a list of all {@link Town}s.
     * 
     * @return
     */
    public List<Town> querySelectTown() {
	return querySelectTowns(0);
    }

    /**
     * Returns one {@link Town} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public Town querySelectTown(final int id) {
	return querySelectTowns(id).get(0);
    }

    /**
     * Returns a list of all {@link VisitorsTaxClass}es.
     * 
     * @return
     */
    public List<VisitorsTaxClass> querySelectVisitorstaxclass() {
	return querySelectVisitorstaxclasss(0);
    }

    /**
     * Returns one {@link VisitorsTaxClass} object.
     * 
     * @param id
     *            of the entry
     * @return
     */
    public VisitorsTaxClass querySelectVisitorstaxclass(final int id) {
	return querySelectVisitorstaxclasss(id).get(0);
    }

    /**
     * Updates a {@link Address} object.
     * 
     * @param address
     *            is the object
     * @return
     */
    public int queryUpdateAddress(final Address address) {
	return queryUpdateAddress(address.getId(), address.getStreet(),
		address.getHouseNumber(), queryUpdateTown(address.getTown()));
    }

    /**
     * Updates a {@link Bill} object.
     * 
     * @param bill
     *            is the object
     * @return
     */
    public int queryUpdateBill(final Bill bill) {
	return queryUpdateBill(bill.getId(), bill.getNumber(),
		queryUpdateBillItem(bill.getBillItem()), bill.getMultiplier());
    }

    /**
     * Updates a {@link BillItem} object.
     * 
     * @param billItem
     *            is the object
     * @return
     */
    public int queryUpdateBillItem(final BillItem billItem) {
	return queryUpdateBillitem(billItem.getId(), billItem.getLabeling(),
		billItem.getPriceBusySeason(), billItem.getPriceLowSeason());
    }

    /**
     * Updates a {@link BookingList} object.
     * 
     * @param bookingList
     *            is the object
     * @return
     */
    public int queryUpdateBookingList(final BookingList bookingList) {
	return queryUpdateBookinglist(bookingList.getId(), bookingList.getNumber(),
		queryUpdateBooking(bookingList.getBooking()));
    }

    /**
     * Updates a {@link ChipCard} object.
     * 
     * @param chipCard
     *            is the object
     * @return
     */
    public int queryUpdateChipCard(final ChipCard chipCard) {
	return queryUpdateChipcard(chipCard.getId(), chipCard.getValidFrom(),
		chipCard.getValidTo());
    }

    /**
     * Updates a {@link ChipCardList} object.
     * 
     * @param cardList
     *            is the object
     * @return
     */
    public int queryUpdateChipCardList(final ChipCardList cardList) {
	return queryUpdateChipcardlist(cardList.getId(), cardList.getNumber(),
		queryUpdateChipCard(cardList.getChipCard()));
    }

    /**
     * Updates a {@link Country} object.
     * 
     * @param country
     *            is the object
     * @return
     */
    public int queryUpdateCountry(final Country country) {
	return queryUpdateCountry(country.getId(), country.getName(),
		country.getAcronym());
    }

    /**
     * Updates a {@link Employee} object.
     * 
     * @param employee
     *            is the object
     * @return
     */
    public int queryUpdateEmployee(final Employee employee) {
	return queryUpdateEmployee(employee.getId(),
		queryUpdatePerson(employee.getPerson()),
		queryUpdateEmployeeRole(employee.getRole()),
		employee.getUserName(), employee.getPassword(),
		employee.isBlocked(), queryUpdateChipCard(employee.getChipCard()));
    }

    /**
     * Updates a {@link EmployeeList} object.
     * 
     * @param employeeList
     *            is the object
     * @return
     */
    public int queryUpdateEmployeeList(final EmployeeList employeeList) {
	return queryUpdateEmployeelist(employeeList.getId(),
		employeeList.getNumber(),
		queryUpdateEmployee(employeeList.getEmployee()));
    }

    /**
     * Updates a {@link EmployeeRole} object.
     * 
     * @param employeeRole
     *            is the object
     * @return
     */
    public int queryUpdateEmployeeRole(final EmployeeRole employeeRole) {
	return queryUpdateEmployeerole(employeeRole.getId(),
		employeeRole.getLabeling(), employeeRole.getArrangement());
    }

    /**
     * Updates a {@link Equipment} object.
     * 
     * @param equipment
     *            is the object
     * @return
     */
    public int queryUpdateEquipment(final Equipment equipment) {
	return queryUpdateEquipment(equipment.getId(), equipment.getType(),
		equipment.getSize(), equipment.getIdentification());
    }

    /**
     * Updates a {@link EquipmentList} object.
     * 
     * @param equipmentList
     *            is the object
     * @return
     */
    public int queryUpdateEquipmentList(final EquipmentList equipmentList) {
	return queryUpdateEquipmentlist(equipmentList.getId(),
		equipmentList.getNumber(),
		queryUpdateEquipment(equipmentList.getEquipment()));
    }

    /**
     * Updates a {@link ExtraBooking} object.
     * 
     * @param extraBooking
     *            is the object
     * @return
     */
    public int queryUpdateExtraBooking(final ExtraBooking extraBooking) {
	return queryUpdateExtrabooking(extraBooking.getId(),
		extraBooking.getName(), extraBooking.getName(),
		queryUpdateSite(extraBooking.getSite()));
    }

    /**
     * Updates a {@link ExtraBookingList} object.
     * 
     * @param extraBookingList
     *            is the object
     * @return
     */
    public int queryUpdateExtraBookingList(final ExtraBookingList extraBookingList) {
	return queryUpdateExtrabookinglist(extraBookingList.getId(),
		extraBookingList.getNumber(),
		queryUpdateSite(extraBookingList.getSite()));
    }

    /**
     * Updates a {@link Guest} object.
     * 
     * @param guest
     *            is the object
     * @return
     */
    public int queryUpdateGuest(final Guest guest) {
	return queryUpdateGuest(guest.getId(),
		queryUpdatePerson(guest.getPerson()),
		queryUpdateVisitorsTaxClass(guest.getVisitorsTaxClass()));
    }

    /**
     * Updates a {@link GuestList} object.
     * 
     * @param guestList
     *            is the object
     * @return
     */
    public int queryUpdateGuestList(final GuestList guestList) {
	return queryUpdateGuestlist(guestList.getId(), guestList.getNumber(),
		queryUpdateGuest(guestList.getGuest()));
    }

    /**
     * Updates a {@link Person} object.
     * 
     * @param person
     *            is the object
     * @return
     */
    public int queryUpdatePerson(final Person person) {
	return queryUpdatePerson(person.getId(), person.getIdentificationNumber(),
		person.getName(), person.getFirstName(),
		queryUpdateAddress(person.getAddress()), person.getDateOfBirth());
    }

    /**
     * Updates a {@link Pitch} object.
     * 
     * @param pitch
     *            is the object
     * @return
     */
    public int queryUpdatePitch(final Pitch pitch) {
	return queryUpdatePitch(pitch.getId(), pitch.getDistrict(),
		pitch.getType(), pitch.getLength(), pitch.getWidth(),
		pitch.getNatureOfSoil(), queryUpdateSite(pitch.getDeliveryPoint()),
		pitch.getCharacteristics());
    }

    /**
     * Updates a {@link PitchBooking} object.
     * 
     * @param pitchBooking
     *            is the object
     * @return
     */
    public int queryUpdatePitchBooking(final PitchBooking pitchBooking) {
	return queryUpdatePitchbooking(pitchBooking.getId(),
		queryUpdatePitch(pitchBooking.getPitch()),
		pitchBooking.isElectricity());
    }

    /**
     * Updates a {@link PitchBookingList} object.
     * 
     * @param pitchBookingList
     *            is the object
     * @return
     */
    public int queryUpdatePitchBookingList(final PitchBookingList pitchBookingList) {
	return queryUpdatePitchbookinglist(pitchBookingList.getId(),
		pitchBookingList.getNumber(),
		queryUpdatePitchBooking(pitchBookingList.getPitchBooking()));
    }

    /**
     * Updates a {@link PitchList} object.
     * 
     * @param pitchList
     *            is the object
     * @return
     */
    public int queryUpdatePitchList(final PitchList pitchList) {
	return queryUpdatePitchlist(pitchList.getId(), pitchList.getNumber(),
		queryUpdatePitch(pitchList.getPitch()));
    }

    /**
     * Updates a {@link Service} object.
     * 
     * @param service
     *            is the object
     * @return
     */
    public int queryUpdateService(final Service service) {
	return queryUpdateService(service.getId(),
		queryUpdatePitch(service.getPitch()),
		queryUpdateSite(service.getSite()),
		queryUpdateEmployeeRole(service.getEmployeeRole()),
		service.getDescription(), service.getCreationDate(),
		service.getPriority(), service.getDoneDate());
    }

    /**
     * Updates a {@link ServiceList} object.
     * 
     * @param serviceList
     *            is the object
     * @return
     */
    public int queryUpdateServiceList(final ServiceList serviceList) {
	return queryUpdateServicelist(serviceList.getId(), serviceList.getNumber(),
		queryUpdateService(serviceList.getService()));
    }

    /**
     * Updates a {@link Site} object.
     * 
     * @param site
     *            is the object
     * @return
     */
    public int queryUpdateSite(final Site site) {
	return queryUpdateSite(site.getId(), site.getLabeling(), site.getType(),
		site.getOpeningHours(), site.getDescription());
    }

    /**
     * Updates a {@link SiteList} object.
     * 
     * @param siteList
     *            is the object
     * @return
     */
    public int queryUpdateSiteList(final SiteList siteList) {
	return queryUpdateSitelist(siteList.getId(), siteList.getNumber(),
		queryUpdateSite(siteList.getSite()));
    }

    /**
     * Updates a {@link Town} object.
     * 
     * @param town
     *            is the object
     * @return
     */
    public int queryUpdateTown(final Town town) {
	return queryUpdateTown(town.getId(), town.getName(), town.getPostalCode(),
		queryUpdateCountry(town.getCountry()));
    }

    /**
     * Updates a {@link VisitorsTaxClass} object.
     * 
     * @param visitorsTaxClass
     *            is the object
     * @return
     */
    public int queryUpdateVisitorsTaxClass(final VisitorsTaxClass visitorsTaxClass) {
	return queryUpdateVisitorstaxclass(visitorsTaxClass.getId(),
		visitorsTaxClass.getLabeling(), visitorsTaxClass.getPrice());
    }

    /**
     * Insert {@link Address} data into database.
     * 
     * @param street
     *            name of the street
     * @param houseNumber
     *            name of the house number
     * @param town_ID
     *            id of the {@link Town}
     * @return
     */
    private int queryInsertAddress(final String street, final String houseNumber,
	    final int town_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "address(street, houseNumber, town_ID) " + "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, street);
	    statement.setString(2, houseNumber);
	    statement.setInt(3, town_ID);

	    statement.executeUpdate();
	    // final int affectedRows = statement.executeUpdate();
	    // if (affectedRows == 0) {
	    // // TODO
	    // // throw new
	    // // SQLException("Creating user failed, no rows affected.");
	    // }
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Bill} data into database.
     * 
     * @param number
     *            of the bill
     * @param billitem_ID
     *            is the id of the {@link BillItem}
     * @param multiplier
     *            is
     * @return
     */
    private int queryInsertBill(final int number, final int billitem_ID,
	    final int multiplier) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "bill(number, billitem_ID, multiplier) " + "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, billitem_ID);
	    statement.setInt(3, multiplier);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link BillItem} data into database.
     * 
     * @param labeling
     *            is the labeling
     * @param priceBusySeason
     *            is the price of the busy season
     * @param priceLowSeason
     *            is the price of the low season
     * @return
     */
    private int queryInsertBillitem(final String labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "billitem(labeling, priceBusySeason, priceLowSeason) "
		+ "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, labeling);
	    statement.setFloat(2, priceBusySeason.returnValue());
	    statement.setFloat(3, priceLowSeason.returnValue());

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Booking} data into database.
     * 
     * @param responsiblePerson_ID
     *            is the id of the responsible {@link Person}
     * @param fellowTravelersList_number
     *            is the id of the fellow {@link GuestList}
     * @param from
     *            is the arrival {@link Date}
     * @param until
     *            is the leaving {@link Date}
     * @param equipmentList_number
     *            is the id of th {@link EquipmentList}
     * @param pitchBookingList_number
     *            is the id of the {@link PitchBookingList}
     * @param extraBookingList_number
     *            is the id of the {@link ExtraBookingList}
     * @param bill_number
     *            is the id of the {@link Bill}
     * @param chipCardList_number
     *            is the id of the {@link ChipCardList}
     * @return
     */
    private int queryInsertBooking(final int responsiblePerson_ID,
	    final int fellowTravelersList_number, final Date from,
	    final Date until, final int equipmentList_number,
	    final int pitchBookingList_number, final int extraBookingList_number,
	    final int bill_number, final int chipCardList_number) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "booking(responsiblePerson_ID, fellowTravelersList_number, from, until, equipmentList_number, pitchBookingList_number, extraBookingList_number, bill_number, chipCardList_number) "
		+ "VALUES ();";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, responsiblePerson_ID);
	    statement.setInt(2, fellowTravelersList_number);
	    statement.setLong(3, from.getTime());
	    statement.setLong(4, until.getTime());
	    statement.setInt(5, equipmentList_number);
	    statement.setInt(6, pitchBookingList_number);
	    statement.setInt(7, extraBookingList_number);
	    statement.setInt(8, bill_number);
	    statement.setInt(9, chipCardList_number);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link BookingList} data into database.
     * 
     * @param number
     *            of the list
     * @param booking_ID
     *            is the id of the {@link Booking}
     * @return
     */
    private int queryInsertBookinglist(final int number, final int booking_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "bookinglist(number, booking_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, booking_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link ChipCard} data into database.
     * 
     * @param validFrom
     *            the {@link Date} from when the card is valide
     * @param validTo
     *            the {@link Date} the when the card is invalide
     * @return
     */
    private int queryInsertChipcard(final Date validFrom, final Date validTo) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "chipcard(validFrom, validTo) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setLong(1, validFrom.getTime());
	    statement.setLong(2, validTo.getTime());

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link ChipCardList} data into database.
     * 
     * @param number
     *            of the list
     * @param chipCard_ID
     *            is the id of the {@link ChipCard}
     * @return
     */
    private int queryInsertChipcardlist(final int number, final int chipCard_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "chipcardlist(number, chipCard_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, chipCard_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Country} data into database.
     * 
     * @param name
     *            of the {@link Country}
     * @param acronym
     *            of the {@link Country}
     * @return
     */
    private int queryInsertCountry(final String name, final String acronym) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "country(name, acronym) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, name);
	    statement.setString(2, acronym);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Employee} data into database.
     * 
     * @param person_ID
     *            is the ID of the {@link Person}
     * @param employeeRole_ID
     *            is the id of the {@link EmployeeRole}
     * @param userName
     *            is the user name of the user login
     * @param password
     *            is the password of the user login
     * @param blocked
     *            is true if the user is blocked
     * @param chipCard_ID
     *            is the id of the {@link ChipCard}
     * @return
     */
    private int queryInsertEmployee(final int person_ID, final int employeeRole_ID,
	    final String userName, final String password, final boolean blocked,
	    final int chipCard_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "employee(person_ID, employeeRole_ID, userName, password, blocked, chipCard_ID) "
		+ "VALUES (?, ?, ?, ?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, person_ID);
	    statement.setInt(2, employeeRole_ID);
	    statement.setString(3, userName);
	    statement.setString(4, password);
	    statement.setBoolean(5, blocked);
	    statement.setInt(6, chipCard_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link EmployeeRole} data into database.
     * 
     * @param labeling
     *            is the labeling
     * @param arrangement
     *            is the arrangement
     * @return
     */
    private int queryInsertEmployeerole(final String labeling,
	    final String arrangement) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "employeerole(labeling, arrangement) " + "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, labeling);
	    statement.setString(2, arrangement);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Equipment} data into database.
     * 
     * @param type
     *            is the {@link Equipment_Type}
     * @param size
     *            is the size of the {@link Equipment}
     * @param identification
     *            is the identification of the {@link Equipment}
     * @return
     */
    private int queryInsertEquipment(final String type, final String size,
	    final String identification) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "equipment(type, size, identification) " + "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, type);
	    statement.setString(2, size);
	    statement.setString(3, identification);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link EquipmentList} data into database.
     * 
     * @param number
     *            of the list
     * @param equipment_ID
     *            is the id of the {@link Equipment}
     * @return
     */
    private int queryInsertEquipmentlist(final int number, final int equipment_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "equipmentlist(number, equipment_ID) " + "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, equipment_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link ExtraBooking} data into database.
     * 
     * @param name
     *            of the {@link ExtraBooking}
     * @param labeling
     *            is the labeling
     * @param site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryInsertExtrabooking(final String name, final String labeling,
	    final int site_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "extrabooking(name, labeling, site_ID) " + "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, name);
	    statement.setString(2, labeling);
	    statement.setInt(3, site_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link ExtraBookingList} data into database.
     * 
     * @param number
     *            of the list
     * @param site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryInsertExtrabookinglist(final int number, final int site_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "extrabookinglist(number, site_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, site_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Guest} data into database.
     * 
     * @param person_ID
     *            is the ID of the {@link Person}
     * @param visitorsTaxClass_ID
     *            is the id of the {@link VisitorsTaxClass}
     * @return
     */
    private int queryInsertGuest(final int person_ID, final int visitorsTaxClass_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "guest(person_ID, visitorsTaxClass_ID) " + "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, person_ID);
	    statement.setInt(2, visitorsTaxClass_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link GuestList} data into database.
     * 
     * @param number
     *            of the list
     * @param guest_ID
     *            is the id of the {@link Guest}
     * @return
     */
    private int queryInsertGuestlist(final int number, final int guest_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "guestlist(number, guest_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, guest_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Person} data into database.
     * 
     * @param identificationNumber
     *            of the {@link Person}
     * @param name
     *            is the name
     * @param firstName
     *            is the first name
     * @param address_ID
     *            is the id of the {@link Address}
     * @param dateOfBirth
     *            is the {@link Date} of birth
     * @return
     */
    private int queryInsertPerson(final String identificationNumber,
	    final String name, final String firstName, final int address_ID,
	    final Date dateOfBirth) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "person(identificationNumber, name, firstName, address_ID, dateOfBirth) "
		+ "VALUES (?, ?, ?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, identificationNumber);
	    statement.setString(2, name);
	    statement.setString(3, firstName);
	    statement.setInt(4, address_ID);
	    statement.setLong(5, dateOfBirth.getTime());

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Pitch} data into database.
     * 
     * @param district
     *            is the area where the {@link Pitch} is
     * @param type
     *            is the {@link Pitch_Type}
     * @param length
     *            of the {@link Pitch}
     * @param width
     *            of the {@link Pitch}
     * @param natureOfSoil
     *            is the {@link Pitch_NatureOfSoil}
     * @param deliveryPoint_ID
     *            is the id of the delivery {@link Site}
     * @param characteristics
     *            are the characteristics of the {@link Site}
     * @return
     */
    private int queryInsertPitch(final String district, final String type,
	    final int length, final int width, final String natureOfSoil,
	    final int deliveryPoint_ID, final String characteristics) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "pitch(district, type, length, width, natureOfSoil, deliveryPoint_ID, characteristics) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, district);
	    statement.setString(1, type);
	    statement.setInt(1, length);
	    statement.setInt(1, width);
	    statement.setString(1, natureOfSoil);
	    statement.setInt(1, deliveryPoint_ID);
	    statement.setString(1, characteristics);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link PitchBooking} data into database.
     * 
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @param electricity
     *            is true if the {@link Pitch} has an energy supply
     * @return
     */
    private int queryInsertPitchbooking(final int pitch_ID,
	    final boolean electricity) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "pitchbooking(pitch_ID, electricity) " + "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, pitch_ID);
	    statement.setBoolean(2, electricity);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link PitchBookingList} data into database.
     * 
     * @param number
     *            of the list
     * @param pitchBooking_ID
     *            is the id of the {@link PitchBooking}
     * @return
     */
    private int queryInsertPitchbookinglist(final int number,
	    final int pitchBooking_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "pitchbookinglist(number, pitchBooking_ID) " + "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, pitchBooking_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link PitchList} data into database.
     * 
     * @param number
     *            of the list
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @return
     */
    private int queryInsertPitchlist(final int number, final int pitch_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "pitchlist(number, pitch_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, pitch_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Service} data into database.
     * 
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @param site_ID
     *            is the id of the {@link Site}
     * @param emplyeeRole_ID
     *            is the id of the {@link EmployeeRole}
     * @param description
     *            is the description of the {@link Service}
     * @param creationDate
     *            is the {@link Date} when the {@link Service} was created
     * @param priority
     *            is the priority of the {@link Service}
     * @param doneDate
     *            is the {@link Date} when the {@link Service} was done
     * @return
     */
    private int queryInsertService(final int pitch_ID, final int site_ID,
	    final int emplyeeRole_ID, final String description,
	    final Date creationDate, final int priority, final Date doneDate) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "service(pitch_ID, site_ID, emplyeeRole_ID, description, creationDate, priority, doneDate) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, pitch_ID);
	    statement.setInt(2, site_ID);
	    statement.setInt(3, emplyeeRole_ID);
	    statement.setString(4, description);
	    statement.setLong(5, creationDate.getTime());
	    statement.setInt(6, priority);
	    statement.setLong(7, doneDate.getTime());

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link ServiceList} data into database.
     * 
     * @param number
     *            of the list
     * @param service_ID
     *            is the id of the {@link Service}
     * @return
     */
    private int queryInsertServicelist(final int number, final int service_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "servicelist(number, service_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, service_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Site} data into database.
     * 
     * @param labeling
     *            is the labeling
     * @param typ
     *            is the {@link Site_Type}
     * @param openingHours
     *            describes the hours when the {@link Site} is open
     * @param description
     *            gives some more information about the {@link Site}
     * @return
     */
    private int queryInsertSite(final String labeling, final String typ,
	    final String openingHours, final String description) {
	PreparedStatement statement;
	final String query = "INSERT INTO "
		+ "site(labeling, typ, openingHours, description) "
		+ "VALUES (?, ?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, labeling);
	    statement.setString(2, typ);
	    statement.setString(3, openingHours);
	    statement.setString(4, description);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link SiteList} data into database.
     * 
     * @param number
     *            of the list
     * @param site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryInsertSitelist(final int number, final int site_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "sitelist(number, site_ID) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setInt(1, number);
	    statement.setInt(2, site_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link Town} data into database.
     * 
     * @param name
     *            of the {@link Town}
     * @param postalCode
     *            of the {@link Town}
     * @param country_ID
     *            is the id of the {@link Country}
     * @return
     */
    private int queryInsertTown(final String name, final String postalCode,
	    final int country_ID) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "town(name, postalCode, country_ID) "
		+ "VALUES (?, ?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, name);
	    statement.setString(2, postalCode);
	    statement.setInt(3, country_ID);

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Inserts {@link VisitorsTaxClass} data into database.
     * 
     * @param labeling
     *            is the {@link VisitorsTaxClass_Labeling}
     * @param price
     *            is the price of the {@link VisitorsTaxClass}
     * @return
     */
    private int queryInsertVisitorstaxclass(final String labeling, final Euro price) {
	PreparedStatement statement;
	final String query = "INSERT INTO " + "visitorstaxclass(labeling, price) "
		+ "VALUES (?, ?);";
	try {
	    statement = conncetion.prepareStatement(query,
		    Statement.RETURN_GENERATED_KEYS);

	    statement.setString(1, labeling);
	    statement.setFloat(2, price.returnValue());

	    statement.executeUpdate();
	    final ResultSet result = statement.getGeneratedKeys();
	    if (result.next()) {
		return result.getInt("id");
	    }
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Selects {@link Address}es from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Address> querySelectAddresses(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM address"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Address> ret = new ArrayList<Address>();

	    while (result.next()) {
		final String street = result.getString("street");
		final String houseNumber = result.getString("houseNumber");
		final int town_ID = result.getInt("town_ID");

		ret.add(new Address(id, street, houseNumber,
			querySelectTown(town_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Bill}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<BillItem> querySelectBillitems(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM billitem"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<BillItem> ret = new ArrayList<BillItem>();

	    while (result.next()) {
		final String labeling = result.getString("labeling");
		final Euro priceBusySeason = new Euro(
			result.getFloat("priceBusySeason"));
		final Euro priceLowSeason = new Euro(
			result.getFloat("priceLowSeason"));

		ret.add(new BillItem(id, labeling, priceBusySeason, priceLowSeason));
	    }
	    return ret;

	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Bill}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Bill> querySelectBills(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM bill"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Bill> ret = new ArrayList<Bill>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int billItem_ID = result.getInt("billItem_ID");
		final int multiplier = result.getInt("multiplier");

		ret.add(new Bill(id, number, querySelectBillitem(billItem_ID),
			multiplier));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	    return null;
	}
    }

    /**
     * Selects {@link BookingList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<BookingList> querySelectBookinglists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM extrabookinglist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<BookingList> ret = new ArrayList<BookingList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int booking_ID = result.getInt("booking_ID");

		ret.add(new BookingList(id, querySelectBooking(booking_ID), number));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Booking}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Booking> querySelectBookings(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM booking"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Booking> ret = new ArrayList<Booking>();

	    while (result.next()) {

		final int responsiblePerson_ID = result.getInt("responsiblePerson_ID");
		final int fellowTravelersList_number = result.getInt("fellowTravelersList_number");
		final Date from = new Date(result.getLong("from"));
		final Date until = new Date(result.getLong("until"));
		final int equipmentList_number = result.getInt("equipmentList_number");
		final int pitchBookingList_number = result.getInt("pitchBookingList_number");
		final int extraBookingList_number = result.getInt("extraBookingList_number");
		final int bill_number = result.getInt("bill_number");
		final int chipCardList_number = result.getInt("chipCardList_number");

		ret.add(new Booking(id, querySelectBill(bill_number),
			querySelectChipcardlist(chipCardList_number),
			querySelectEquipmentlist(equipmentList_number),
			querySelectExtrabookinglist(extraBookingList_number),
			querySelectGuestlist(fellowTravelersList_number), from,
			querySelectPitchbookinglist(pitchBookingList_number),
			querySelectGuest(responsiblePerson_ID), until));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link ChipCardList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<ChipCardList> querySelectChipcardlists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM chipcardlist"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<ChipCardList> ret = new ArrayList<ChipCardList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int chipCard_ID = result.getInt("chipCard_ID");

		ret.add(new ChipCardList(id, number,
			querySelectChipcard(chipCard_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link ChipCard}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<ChipCard> querySelectChipcards(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM chipcard"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<ChipCard> ret = new ArrayList<ChipCard>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final Date validFrom = new Date(result.getLong("validFrom"));
		final Date validTo = new Date(result.getLong("validTo"));
		ret.add(new ChipCard(id, number, validFrom, validTo));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Country}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Country> querySelectCountrys(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM country"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Country> ret = new ArrayList<Country>();

	    while (result.next()) {
		final String acronym = result.getString("acronym");
		final String name = result.getString("name");

		ret.add(new Country(id, acronym, name));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link EmployeeList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<EmployeeList> querySelectEmployeelists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM employeelist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<EmployeeList> ret = new ArrayList<EmployeeList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int booking_ID = result.getInt("booking_ID");

		ret.add(new EmployeeList(id, querySelectEmployee(booking_ID),
			number));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link EmployeeRole}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<EmployeeRole> querySelectEmployeeroles(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM employeerole "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<EmployeeRole> ret = new ArrayList<EmployeeRole>();

	    while (result.next()) {
		final String labeling = result.getString("labeling");
		final String arrangement = result.getString("arrangement");

		ret.add(new EmployeeRole(id, arrangement, labeling));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Employee}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Employee> querySelectEmployees(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM employee"
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Employee> ret = new ArrayList<Employee>();

	    while (result.next()) {
		final boolean blocked = result.getBoolean("blocked");
		final int person_ID = result.getInt("person_ID");
		final int chipCard_ID = result.getInt("chipCard_ID");
		final String password = result.getString("password");
		final String userName = result.getString("userName");
		final int employeeRole_ID = result.getInt("employeeRole_ID");

		ret.add(new Employee(id, querySelectPerson(person_ID), blocked,
			querySelectChipcard(chipCard_ID), password,
			querySelectEmployeerole(employeeRole_ID), userName));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link EquipmentList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<EquipmentList> querySelectEquipmentlists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM equipmentlist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<EquipmentList> ret = new ArrayList<EquipmentList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int equipment_ID = result.getInt("equipment_ID");

		ret.add(new EquipmentList(id, number,
			querySelectEquipment(equipment_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Equipment}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Equipment> querySelectEquipments(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM equipment "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Equipment> ret = new ArrayList<Equipment>();

	    while (result.next()) {
		final String identification = result.getString("identification");
		final String size = result.getString("size");
		final String type = result.getString("type");

		ret.add(new Equipment(id, identification, size, type));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link ExtraBookingList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<ExtraBookingList> querySelectExtrabookinglists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM extrabookinglist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<ExtraBookingList> ret = new ArrayList<ExtraBookingList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int site_ID = result.getInt("site_ID");

		ret.add(new ExtraBookingList(id, number, querySelectSite(site_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link ExtraBooking}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<ExtraBooking> querySelectExtrabookings(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM extrabooking "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<ExtraBooking> ret = new ArrayList<ExtraBooking>();

	    while (result.next()) {
		final String labeling = result.getString("labeling");
		final String name = result.getString("name");
		final int site_ID = result.getInt("site_ID");

		ret.add(new ExtraBooking(id, labeling, name,
			querySelectSite(site_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link GuestList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<GuestList> querySelectGuestlists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM guestlist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<GuestList> ret = new ArrayList<GuestList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int guest_ID = result.getInt("guest_ID");

		ret.add(new GuestList(id, querySelectGuest(guest_ID), number));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Guest}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Guest> querySelectGuests(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM guest "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Guest> ret = new ArrayList<Guest>();

	    while (result.next()) {
		final int person_ID = result.getInt("person_ID");
		final int visitorsTaxClass_ID = result.getInt("visitorsTaxClass_ID");

		ret.add(new Guest(id, querySelectPerson(person_ID),
			querySelectVisitorstaxclass(visitorsTaxClass_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Person}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Person> querySelectPersons(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM person "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Person> ret = new ArrayList<Person>();

	    while (result.next()) {
		final String name = result.getString("name");
		final String firstName = result.getString("firstName");
		final String identificationNumber = result.getString("identificationNumber");
		final int address_ID = result.getInt("address_ID");
		final Date dateOfBirth = new Date(result.getLong("dateOfBirth"));

		ret.add(new Person(id, querySelectAddress(address_ID), dateOfBirth,
			firstName, identificationNumber, name));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link PitchBookingList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<PitchBookingList> querySelectPitchbookinglists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM pitch "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<PitchBookingList> ret = new ArrayList<PitchBookingList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int pitchBooking_ID = result.getInt("pitchBooking_ID");

		ret.add(new PitchBookingList(id, number,
			querySelectPitchbooking(pitchBooking_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link PitchBooking}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<PitchBooking> querySelectPitchbookings(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM pitchbooking "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<PitchBooking> ret = new ArrayList<PitchBooking>();

	    while (result.next()) {
		final int pitch_ID = result.getInt("pitch_ID");
		final int electricity = result.getInt("electricity");

		ret.add(new PitchBooking(id, (electricity == 1 ? true : false),
			querySelectPitch(pitch_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link PitchList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<PitchList> querySelectPitchlists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM extrabookinglist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<PitchList> ret = new ArrayList<PitchList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int pitch_ID = result.getInt("pitch_ID");

		ret.add(new PitchList(id, number, querySelectPitch(pitch_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Pitch}es from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Pitch> querySelectPitchs(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM guestlist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Pitch> ret = new ArrayList<Pitch>();

	    while (result.next()) {
		final String characteristics = result.getString("characteristics");
		final String district = result.getString("district");
		final String natureOfSoil = result.getString("natureOfSoil");
		final String type = result.getString("type");
		final int deliveryPoint_ID = result.getInt("deliveryPoint_ID");
		final int length = result.getInt("length");
		final int width = result.getInt("width");

		ret.add(new Pitch(id, characteristics,
			querySelectSite(deliveryPoint_ID), district, length,
			natureOfSoil, type, width));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link ServiceList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<ServiceList> querySelectServicelists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM servicelist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<ServiceList> ret = new ArrayList<ServiceList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int service_ID = result.getInt("service_ID");

		ret.add(new ServiceList(id, number, querySelectService(service_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Service}es from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Service> querySelectServices(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM service "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Service> ret = new ArrayList<Service>();

	    while (result.next()) {
		final Date creationDate = new Date(result.getLong("creationDate"));
		final Date doneDate = new Date(result.getLong("doneDate"));
		final String description = result.getString("description");
		final int employeeRole_ID = result.getInt("employeeRole_ID");
		final int pitch_ID = result.getInt("pitch_ID");
		final int priority = result.getInt("priority");
		final int serviceNumber = result.getInt("serviceNumber");
		final int site_ID = result.getInt("site_ID");

		ret.add(new Service(id, creationDate, description, doneDate,
			querySelectEmployeerole(employeeRole_ID),
			querySelectPitch(pitch_ID), priority, serviceNumber,
			querySelectSite(site_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link SiteList}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<SiteList> querySelectSitelists(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM extrabookinglist "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<SiteList> ret = new ArrayList<SiteList>();

	    while (result.next()) {
		final int number = result.getInt("number");
		final int site_ID = result.getInt("site_ID");

		ret.add(new SiteList(id, number, querySelectSite(site_ID)));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Site}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Site> querySelectSites(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM site "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Site> ret = new ArrayList<Site>();

	    while (result.next()) {
		final String labeling = result.getString("labeling");
		final String type = result.getString("type");
		final String openingHours = result.getString("openingHours");
		final String description = result.getString("description");

		ret.add(new Site(id, description, labeling, openingHours, type));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link Town}s from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<Town> querySelectTowns(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM town "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<Town> ret = new ArrayList<Town>();

	    while (result.next()) {
		final String name = result.getString("name");
		final String postalCode = result.getString("postalCode");
		final int country_ID = result.getInt("country_ID");

		ret.add(new Town(id, querySelectCountry(country_ID), name,
			postalCode));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Selects {@link VisitorsTaxClass}es from the database.
     * 
     * @param id
     *            of the entry
     * @return
     */
    private List<VisitorsTaxClass> querySelectVisitorstaxclasss(final int id) {
	PreparedStatement statement;
	final String query = "SELECT * FROM visitorstaxclass "
		+ (id == 0 ? ";" : " WHERE id='" + id + "';");
	try {
	    statement = conncetion.prepareStatement(query);

	    final ResultSet result = statement.executeQuery();
	    final List<VisitorsTaxClass> ret = new ArrayList<VisitorsTaxClass>();

	    while (result.next()) {
		final String labeling = result.getString("labeling");
		final Euro price = new Euro(result.getFloat("price"));

		ret.add(new VisitorsTaxClass(id, labeling, price));
	    }
	    return ret;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return null;
    }

    /**
     * Updates the {@link Address} data.
     * 
     * @param id
     *            of the entry
     * @param street
     *            name of the street
     * @param houseNumber
     *            number of the house
     * @param town_ID
     *            id of the {@link Town}
     * @return
     */
    private int queryUpdateAddress(final int id, final String street,
	    final String houseNumber, final int town_ID) {
	PreparedStatement statement;
	final String query = "UPDATE address "
		+ "SET street=?, houseNumber=?, town_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, street);
	    statement.setString(2, houseNumber);
	    statement.setInt(3, town_ID);
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Bill} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param billitem_ID
     *            id of the {@link BillItem}
     * @param multiplier
     *            is the count of the {@link BillItem}
     * @return
     */
    private int queryUpdateBill(final int id, final int number,
	    final int billitem_ID, final int multiplier) {
	PreparedStatement statement;
	final String query = "UPDATE bill "
		+ "SET number=?, billitem_ID=?, multiplier=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, billitem_ID);
	    statement.setInt(3, multiplier);
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link BillItem} object.
     * 
     * @param id
     *            of the entry
     * @param labeling
     *            is the labeling
     * @param priceBusySeason
     *            is the price for the busy season
     * @param priceLowSeason
     *            is the price for the low season
     * @return
     */
    private int queryUpdateBillitem(final int id, final String labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	PreparedStatement statement;
	final String query = "UPDATE billitem "
		+ "SET labeling=?, priceBusySeason=?, priceLowSeason=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, labeling);
	    statement.setFloat(2, priceBusySeason.returnValue());
	    statement.setFloat(3, priceLowSeason.returnValue());
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates a {@link Booking} object.
     * 
     * @param booking
     *            is the object
     * @return
     */
    private int queryUpdateBooking(final Booking booking) {
	return queryUpdateBooking(booking.getId(),
		queryUpdateGuest(booking.getResponsiblePerson()),
		queryUpdateGuestList(booking.getFellowTravelers()),
		booking.getFrom(), booking.getUntil(),
		queryUpdateEquipmentList(booking.getEquipment()),
		queryUpdatePitchBookingList(booking.getPitchBooking()),
		queryUpdateExtraBookingList(booking.getExtraBooking()),
		queryUpdateBill(booking.getBill()),
		queryUpdateChipCardList(booking.getChipCard()));
    }

    /**
     * Updates the {@link Booking} object.
     * 
     * @param id
     *            of the entry
     * @param responsiblePerson_ID
     *            is the id of the responsible {@link Person}
     * @param fellowTravelersList_number
     *            is the id of the fellow {@link GuestList}
     * @param from
     *            is the arrival {@link Date}
     * @param until
     *            is the leaving {@link Date}
     * @param equipmentList_number
     *            is the id of the {@link EquipmentList}
     * @param pitchBookingList_number
     *            is the id of the {@link PitchBookingList}
     * @param extraBookingList_number
     *            is the id of the {@link ExtraBookingList}
     * @param bill_number
     *            is the id of the {@link Bill}
     * @param chipCardList_number
     *            is the id of the {@link ChipCardList}
     * @return
     */
    private int queryUpdateBooking(final int id, final int responsiblePerson_ID,
	    final int fellowTravelersList_number, final Date from,
	    final Date until, final int equipmentList_number,
	    final int pitchBookingList_number, final int extraBookingList_number,
	    final int bill_number, final int chipCardList_number) {
	PreparedStatement statement;
	final String query = "UPDATE booking "
		+ "SET responsiblePerson_ID=?, fellowTravelersList_number=?, from=?, until=?, equipmentList_number=?, pitchBookingList_number=?, extraBookingList_number=?, bill_number=?, chipCardList_number=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, responsiblePerson_ID);
	    statement.setInt(2, fellowTravelersList_number);
	    statement.setLong(3, from.getTime());
	    statement.setLong(4, until.getTime());
	    statement.setInt(5, equipmentList_number);
	    statement.setInt(6, pitchBookingList_number);
	    statement.setInt(7, extraBookingList_number);
	    statement.setInt(8, bill_number);
	    statement.setInt(9, chipCardList_number);
	    statement.setInt(10, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link BookingList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param booking_ID
     *            is the id of the {@link Booking}
     * @return
     */
    private int queryUpdateBookinglist(final int id, final int number,
	    final int booking_ID) {
	PreparedStatement statement;
	final String query = "UPDATE bookinglist " + "SET number=?, booking_ID=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, booking_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link ChipCard} data.
     * 
     * @param id
     *            of the entry
     * @param validFrom
     *            the {@link Date} from when the {@link ChipCard} is valide
     * @param validTo
     *            the {@link Date} to when the {@link ChipCard} is invalide
     * @return
     */
    private int queryUpdateChipcard(final int id, final Date validFrom,
	    final Date validTo) {
	PreparedStatement statement;
	final String query = "UPDATE chipcard " + "SET validFrom=?, validTo=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setLong(1, validFrom.getTime());
	    statement.setLong(2, validTo.getTime());
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link ChipCardList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the {@link ChipCardList}
     * @param chipCard_ID
     *            is the id of the {@link ChipCard}
     * @return
     */
    private int queryUpdateChipcardlist(final int id, final int number,
	    final int chipCard_ID) {
	PreparedStatement statement;
	final String query = "UPDATE chipcardlist "
		+ "SET number=?, chipCard_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, chipCard_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Country} data.
     * 
     * @param id
     *            of the entry
     * @param name
     *            of the {@link Country}
     * @param acronym
     *            of the {@link Country}
     * @return
     */
    private int queryUpdateCountry(final int id, final String name,
	    final String acronym) {
	PreparedStatement statement;
	final String query = "UPDATE country " + "SET name=?, acronym=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, name);
	    statement.setString(2, acronym);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Employee} data.
     * 
     * @param id
     *            of the entry
     * 
     * @param person_ID
     *            is the ID of the {@link Person}
     * @param employeeRole_ID
     *            is the id of the {@link EmployeeRole}
     * @param userName
     *            is the name of the user login
     * @param password
     *            is the password of the user login
     * @param blocked
     *            is if the user is blocked or not
     * @param chipCard_ID
     *            is the d of the {@link ChipCard}
     * @return
     */
    private int queryUpdateEmployee(final int id, final int person_ID,
	    final int employeeRole_ID, final String userName,
	    final String password, final boolean blocked, final int chipCard_ID) {
	PreparedStatement statement;
	final String query = "UPDATE employee "
		+ "SET person_ID=?, employeeRole_ID=?, userName=?, password=?, blocked=?, chipCard_ID=? "
		+ "WHERE id=?;";

	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, person_ID);
	    statement.setInt(2, employeeRole_ID);
	    statement.setString(3, userName);
	    statement.setString(4, password);
	    statement.setBoolean(5, blocked);
	    statement.setInt(6, chipCard_ID);
	    statement.setInt(7, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link EmployeeList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param employee_ID
     *            is the id of the {@link Employee}
     * @return
     */
    private int queryUpdateEmployeelist(final int id, final int number,
	    final int employee_ID) {
	PreparedStatement statement;
	final String query = "UPDATE employeelist "
		+ "SET number=?, employee_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, employee_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link EmployeeRole} data.
     * 
     * @param id
     *            of the entry
     * @param labeling
     *            is the labeling of the {@link EmployeeRole}
     * @param arrangement
     *            is the arrangement
     * @return
     */
    private int queryUpdateEmployeerole(final int id, final String labeling,
	    final String arrangement) {
	PreparedStatement statement;
	final String query = "UPDATE employeerole "
		+ "SET labeling=?, statement=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, labeling);
	    statement.setString(2, arrangement);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Equipment} data.
     * 
     * @param id
     *            of the entry
     * @param type
     *            is the {@link Equipment_Type}
     * @param size
     *            of the {@link Equipment}
     * @param identification
     *            is the identification
     * @return
     */
    private int queryUpdateEquipment(final int id, final String type,
	    final String size, final String identification) {
	PreparedStatement statement;
	final String query = "UPDATE equipment "
		+ "SET type=?, size=?, identification=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, type);
	    statement.setString(2, size);
	    statement.setString(3, identification);
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link EquipmentList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param equipment_ID
     *            is the id of the {@link Equipment}
     * @return
     */
    private int queryUpdateEquipmentlist(final int id, final int number,
	    final int equipment_ID) {
	PreparedStatement statement;
	final String query = "UPDATE equipmentlist "
		+ "SET number=?, equipment_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, equipment_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link ExtraBooking} data.
     * 
     * @param id
     *            of the entry
     * @param name
     *            of the {@link ExtraBooking}
     * @param labeling
     *            is the labeling
     * @param site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryUpdateExtrabooking(final int id, final String name,
	    final String labeling, final int site_ID) {
	PreparedStatement statement;
	final String query = "UPDATE extrabooking "
		+ "SET name=?, labeling=?, site_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, name);
	    statement.setString(2, labeling);
	    statement.setInt(3, site_ID);
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link ExtraBookingList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryUpdateExtrabookinglist(final int id, final int number,
	    final int site_ID) {
	PreparedStatement statement;
	final String query = "UPDATE extrabookinglist "
		+ "SET number=?, site_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, site_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Guest} data.
     * 
     * @param id
     *            of the entry
     * @param person_ID
     *            is the ID of the {@link Person}
     * @param visitorsTaxClass_ID
     *            is the id of the {@link VisitorsTaxClass}
     * @return
     */
    private int queryUpdateGuest(final int id, final int person_ID,
	    final int visitorsTaxClass_ID) {
	PreparedStatement statement;
	final String query = "UPDATE guest "
		+ "SET person_ID=?, visitorsTaxClass_ID=?,  " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, person_ID);
	    statement.setInt(2, visitorsTaxClass_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link GuestList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param guest_ID
     *            is the id of the {@link Guest}
     * @return
     */
    private int queryUpdateGuestlist(final int id, final int number,
	    final int guest_ID) {
	PreparedStatement statement;
	final String query = "UPDATE guestlist " + "SET number=?, guest_ID=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, guest_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Person} data.
     * 
     * @param id
     *            of the entry
     * @param identificationNumber
     *            is the identification number of the {@link Person}
     * @param name
     *            is the name of the {@link Person}
     * @param firstName
     *            is the last name of the {@link Person}
     * @param address_ID
     *            is the id of the {@link Address}
     * @param dateOfBirth
     *            is the date of birth of the {@link Person}
     * @return
     */
    private int queryUpdatePerson(final int id, final String identificationNumber,
	    final String name, final String firstName, final int address_ID,
	    final Date dateOfBirth) {
	PreparedStatement statement;
	final String query = "UPDATE person "
		+ "SET identificationNumber=?, name=?, firstName=?, address_ID=?, dateOfBirth=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, identificationNumber);
	    statement.setString(2, name);
	    statement.setString(3, firstName);
	    statement.setInt(4, address_ID);
	    statement.setLong(5, dateOfBirth.getTime());
	    statement.setInt(6, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Pitch} data.
     * 
     * @param id
     *            of the entry
     * @param district
     *            is the area where the {@link Pitch} is
     * @param type
     *            is the {@link Pitch_Type}
     * @param length
     *            is the length
     * @param width
     *            is the width
     * @param natureOfSoil
     *            is the {@link Pitch_NatureOfSoil}
     * @param deliveryPoint_ID
     *            is the id of the delivery point {@link Site}
     * @param characteristics
     *            are some characteristics of the {@link Pitch}
     * @return
     */
    private int queryUpdatePitch(final int id, final String district,
	    final String type, final int length, final int width,
	    final String natureOfSoil, final int deliveryPoint_ID,
	    final String characteristics) {
	PreparedStatement statement;
	final String query = "UPDATE pitch "
		+ "SET district=?, type=?, length=?, width=?, natureOfSoil=?, deliveryPoint_ID=?, characteristics=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, district);
	    statement.setString(2, type);
	    statement.setInt(3, length);
	    statement.setInt(4, width);
	    statement.setString(5, natureOfSoil);
	    statement.setInt(6, deliveryPoint_ID);
	    statement.setString(7, characteristics);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link PitchBooking} data.
     * 
     * @param id
     *            of the entry
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @param electricity
     *            is true when the {@link Pitch} has an energy supply
     * @return
     */
    private int queryUpdatePitchbooking(final int id, final int pitch_ID,
	    final boolean electricity) {
	PreparedStatement statement;
	final String query = "UPDATE pitchbooking "
		+ "SET pitch_ID=?, electricity=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, pitch_ID);
	    statement.setBoolean(2, electricity);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link PitchBookingList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param pitchBooking_ID
     *            is the id of the {@link PitchBooking}
     * @return
     */
    private int queryUpdatePitchbookinglist(final int id, final int number,
	    final int pitchBooking_ID) {
	PreparedStatement statement;
	final String query = "UPDATE pitchbookinglist "
		+ "SET number=?, pitchBooking_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, pitchBooking_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link PitchList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @return
     */
    private int queryUpdatePitchlist(final int id, final int number,
	    final int pitch_ID) {
	PreparedStatement statement;
	final String query = "UPDATE bookinglist " + "SET number=?, pitch_ID=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, pitch_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Service} data.
     * 
     * @param id
     *            of the entry
     * @param pitch_ID
     *            is the id of the {@link Pitch}
     * @param site_ID
     *            is the id of the {@link Site}
     * @param emplyeeRole_ID
     *            is the id of the {@link EmployeeRole}
     * @param description
     *            describes the {@link Service}
     * @param creationDate
     *            is the {@link Date} when the entry was created
     * @param priority
     *            is the priority of the {@link Service}
     * @param doneDate
     *            is the {@link Date} when the {@link Service} was done
     * @return
     */
    private int queryUpdateService(final int id, final int pitch_ID,
	    final int site_ID, final int employeeRole_ID, final String description,
	    final Date creationDate, final int priority, final Date doneDate) {
	PreparedStatement statement;
	final String query = "UPDATE service "
		+ "SET pitch_ID=?, site_ID=?, emplyeeRole_ID=?, description=?, creationDate=?, priority=?, doneDate=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, pitch_ID);
	    statement.setInt(2, site_ID);
	    statement.setInt(3, employeeRole_ID);
	    statement.setString(4, description);
	    statement.setLong(5, creationDate.getTime());
	    statement.setInt(6, priority);
	    statement.setLong(7, doneDate.getTime());
	    statement.setInt(8, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link ServiceList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param service_ID
     *            is the id of the {@link Service}
     * @return
     */
    private int queryUpdateServicelist(final int id, final int number,
	    final int service_ID) {
	PreparedStatement statement;
	final String query = "UPDATE servicelist " + "SET number=?, service_ID=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, service_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Site} data.
     * 
     * @param id
     *            of the entry
     * @param labeling
     *            is the labeling
     * @param typ
     *            is the {@link Site_Type}
     * @param openingHours
     *            describes the hours when the {@link Site} is open
     * @param description
     *            gives some more information
     * @return
     */
    private int queryUpdateSite(final int id, final String labeling,
	    final String typ, final String openingHours, final String description) {
	PreparedStatement statement;
	final String query = "UPDATE site "
		+ "SET labeling=?, typ=?, openingHours=?, description=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, labeling);
	    statement.setString(2, typ);
	    statement.setString(3, openingHours);
	    statement.setString(4, description);
	    statement.setInt(5, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link SiteList} data.
     * 
     * @param id
     *            of the entry
     * @param number
     *            of the list
     * @param Site_ID
     *            is the id of the {@link Site}
     * @return
     */
    private int queryUpdateSitelist(final int id, final int number,
	    final int Site_ID) {
	PreparedStatement statement;
	final String query = "UPDATE bookinglist " + "SET number=?, Site_ID=? "
		+ "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setInt(1, number);
	    statement.setInt(2, Site_ID);
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link Town} data.
     * 
     * @param id
     *            of the entry
     * @param name
     *            of the {@link Town}
     * @param postalCode
     *            of the {@link Town}
     * @param country_ID
     *            is the id of the {@link Country}
     * @return
     */
    private int queryUpdateTown(final int id, final String name,
	    final String postalCode, final int country_ID) {
	PreparedStatement statement;
	final String query = "UPDATE town "
		+ "SET name=?, postalCode=?, country_ID=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, name);
	    statement.setString(2, postalCode);
	    statement.setInt(3, country_ID);
	    statement.setInt(4, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    /**
     * Updates the {@link VisitorsTaxClass} data
     * 
     * @param id
     *            of the entry
     * @param labeling
     *            is the labeling
     * @param price
     *            is the price of the {@link VisitorsTaxClass}
     * @return
     */
    private int queryUpdateVisitorstaxclass(final int id, final String labeling,
	    final Euro price) {
	PreparedStatement statement;
	final String query = "UPDATE visitorstaxclass "
		+ "SET labeling=?, price=? " + "WHERE id=?;";
	try {
	    statement = conncetion.prepareStatement(query);

	    statement.setString(1, labeling);
	    statement.setFloat(2, price.returnValue());
	    statement.setInt(3, id);

	    statement.executeUpdate();
	    return id;
	} catch (final SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	return 0;
    }

    private Connection conncetion;
}
