package de.dhbw.swe.camping_site_mgt.data_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.booking_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.person_mgt.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.SiteMgr;
import de.dhbw.swe.camping_site_mgt.service_mgt.ServiceMgr;

public class DataMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}.
     */
    public DataMgr(final AccessableDatabase db) {
	mgrs = new HashMap<>();

	final BaseDataObjectMgr billItemMgr = new BillItemMgr(db);
	final BaseDataObjectMgr chipCardMgr = new ChipCardMgr(db);
	final BaseDataObjectMgr countryMgr = new CountryMgr(db);
	final BaseDataObjectMgr employeeRoleMgr = new EmployeeRoleMgr(db);
	final BaseDataObjectMgr pitchBookingMgr = new PitchBookingMgr(db);
	final BaseDataObjectMgr siteMgr = new SiteMgr(db);
	final BaseDataObjectMgr townMgr = new TownMgr(db);
	final BaseDataObjectMgr visitorsTaxClassMgr = new VisitorsTaxClassMgr(db);

	mgrs.put(BillItemMgr.class, billItemMgr);
	mgrs.put(ChipCardMgr.class, chipCardMgr);
	mgrs.put(CountryMgr.class, countryMgr);
	mgrs.put(EmployeeRoleMgr.class, employeeRoleMgr);
	mgrs.put(PitchBookingMgr.class, pitchBookingMgr);
	mgrs.put(SiteMgr.class, siteMgr);
	mgrs.put(TownMgr.class, townMgr);
	mgrs.put(VisitorsTaxClassMgr.class, visitorsTaxClassMgr);

	final BaseDataObjectMgr billMgr = new BillMgr(db, (BillItemMgr) billItemMgr);
	final BaseDataObjectMgr pitchMgr = new PitchMgr(db, (SiteMgr) siteMgr);
	final BaseDataObjectMgr extraBookingMgr = new ExtraBookingMgr(db,
		(SiteMgr) siteMgr);
	final BaseDataObjectMgr serviceMgr = new ServiceMgr(db,
		(PitchMgr) pitchMgr, (SiteMgr) siteMgr);
	final BaseDataObjectMgr personMgr = new PersonMgr(db,
		(CountryMgr) countryMgr, (TownMgr) townMgr);
	final BaseDataObjectMgr employeeMgr = new EmployeeMgr(db,
		(ChipCardMgr) chipCardMgr, (EmployeeRoleMgr) employeeRoleMgr,
		(PersonMgr) personMgr);
	final BaseDataObjectMgr guestMgr = new GuestMgr(db, (PersonMgr) personMgr,
		(VisitorsTaxClassMgr) visitorsTaxClassMgr);

	mgrs.put(BillMgr.class, billMgr);
	mgrs.put(PitchMgr.class, pitchMgr);
	mgrs.put(ExtraBookingMgr.class, extraBookingMgr);
	mgrs.put(ServiceMgr.class, serviceMgr);
	mgrs.put(PersonMgr.class, personMgr);
	mgrs.put(EmployeeMgr.class, employeeMgr);
	mgrs.put(GuestMgr.class, guestMgr);
    }

    /**
     * @return all available {@link BaseDataObjectMgr}.
     */
    public HashMap<Class<?>, BaseDataObjectMgr> getDataObjectMgrs() {
	return mgrs;
    }

    /** The {@link BaseDataObjectMgr}s. */
    private final HashMap<Class<?>, BaseDataObjectMgr> mgrs;
}
