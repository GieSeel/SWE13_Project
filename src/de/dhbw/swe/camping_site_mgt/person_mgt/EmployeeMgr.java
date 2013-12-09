package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Employee} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeMgr extends BaseDataObjectMgr {
    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theChipCardMgr
     *            the {@link ChipcardMgr}
     * @param theEmployeeRoleMgr
     *            the {@link EmployeeRoleMgr}
     * @param thePersonMgr
     *            the {@link PersonMgr}
     */
    public EmployeeMgr(final AccessableDatabase db, AddressMgr theAddressMgr, TownMgr theTownMgr, CountryMgr theCountryMgr, final ChipcardMgr theChipCardMgr,
    	    final EmployeeRoleMgr theEmployeeRoleMgr) {
    	super(db);
    	addressMgr = theAddressMgr;
    	townMgr = theTownMgr;
    	countryMgr = theCountryMgr;
	chipCardMgr = theChipCardMgr;
	employeeRoleMgr = theEmployeeRoleMgr;
//	personMgr = thePersonMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Employee().getTableName();
    }

    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(addressMgr);
	subMgr.add(townMgr);
	subMgr.add(countryMgr);
	subMgr.add(chipCardMgr);
	subMgr.add(employeeRoleMgr);
//	subMgr.add(personMgr);
	return subMgr;
    }

//    @Override
//    protected DataObject map2DataObject(final HashMap<String, Object> map) {
//	int id = 0;
//	if (map.containsKey("id")) {
//	    id = (int) map.get("id");
//	}
//
//	final Person person = (Person) map.get("person");
//
//	return new Employee(id, blocked, chipCard, password, person, role, userName);
//    }

    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	String identification_number = (String) map.get("identification_number");
	String name = (String) map.get("name");
	String first_name = (String) map.get("first_name");
	Date date_of_birth = (Date) map.get("date_of_birth");
	Address address = (Address) map.get("address");
	Town town = (Town) map.get("town");
	Country country = (Country) map.get("country");
	
	final boolean blocked = (boolean) map.get("blocked");
	final String password = (String) map.get("password");
	final String user_name = (String) map.get("user_name");
	
	final Chipcard chipcard = (Chipcard) map.get("chip_card");
	final EmployeeRole employee_role = (EmployeeRole) map.get("employee_role");
	
//	return new Person(id, identification_number, name, first_name, date_of_birth, address, town, country);
	return new Employee(id, identification_number, name, first_name, date_of_birth, address, town, country, employee_role, user_name, password, chipcard, blocked);
    }

    /** The {@link ChipcardMgr}. */
    private final ChipcardMgr chipCardMgr;

    /** The {@link EmployeeRoleMgr}. */
    private final EmployeeRoleMgr employeeRoleMgr;
    
    private AddressMgr addressMgr;
    private TownMgr townMgr;
    private CountryMgr countryMgr;

//    /** The {@link PersonMgr}. */
//    private final PersonMgr personMgr;
}
