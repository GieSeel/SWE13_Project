package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.Address;
import de.dhbw.swe.camping_site_mgt.common.AddressMgr;
import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Country;
import de.dhbw.swe.camping_site_mgt.common.CountryMgr;
import de.dhbw.swe.camping_site_mgt.common.Town;
import de.dhbw.swe.camping_site_mgt.common.TownMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Guest} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class GuestMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param thePersonMgr
     *            the {@link PersonMgr}
     * @param theVisitorsTaxClassMgr
     *            the {@link VisitorsTaxClassMgr}
     */
    public GuestMgr(final AccessableDatabase db, AddressMgr theAddressMgr, TownMgr theTownMgr, CountryMgr theCountryMgr) {
	super(db);
	addressMgr = theAddressMgr;
	townMgr = theTownMgr;
	countryMgr = theCountryMgr;
//	personMgr = thePersonMgr;
//	visitorsTaxClassMgr = theVisitorsTaxClassMgr;
	
	load();
    }

    @Override
    public String getTableName() {
	return new Guest().getTableName();
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
//	subMgr.add(personMgr);
//	subMgr.add(visitorsTaxClassMgr);
	subMgr.add(addressMgr);
	subMgr.add(townMgr);
	subMgr.add(countryMgr);
	return subMgr;
    }

//    @Override
//    protected DataObject map2DataObject(final HashMap<String, Object> map) {
//	int id = 0;
//	if (map.containsKey("id")) {
//	    id = (int) map.get("id");
//	}
//	final Person person = (Person) map.get("person");
//	// TODO consistency
//	final VisitorsTaxClass visitorsTaxClass = (VisitorsTaxClass) map.get("visitorstaxclass");
//
//	return new Guest(id, person, visitorsTaxClass);
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
	
//	return new Person(id, identification_number, name, first_name, date_of_birth, address, town, country);
	return new Guest(id, identification_number, name, first_name, date_of_birth, address, town, country);
    }

//    /** The {@link PersonMgr}. */
//    private final PersonMgr personMgr;

//    /** The {@link VisitorsTaxClassMgr}. */
//    private final VisitorsTaxClassMgr visitorsTaxClassMgr;
    
    private AddressMgr addressMgr;
    private TownMgr townMgr;
    private CountryMgr countryMgr;
}
