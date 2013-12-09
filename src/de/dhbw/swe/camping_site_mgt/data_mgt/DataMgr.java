package de.dhbw.swe.camping_site_mgt.data_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.booking_mgt.BillElementMgr;
import de.dhbw.swe.camping_site_mgt.booking_mgt.BillItemMgr;
import de.dhbw.swe.camping_site_mgt.booking_mgt.BookingMgr;
import de.dhbw.swe.camping_site_mgt.booking_mgt.EquipmentMgr;
import de.dhbw.swe.camping_site_mgt.booking_mgt.ExtraBookingMgr;
import de.dhbw.swe.camping_site_mgt.booking_mgt.PitchBookingMgr;
import de.dhbw.swe.camping_site_mgt.common.AddressMgr;
import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.ChipcardMgr;
import de.dhbw.swe.camping_site_mgt.common.CountryMgr;
import de.dhbw.swe.camping_site_mgt.common.DurationMgr;
import de.dhbw.swe.camping_site_mgt.common.TownMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeMgr;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRoleMgr;
import de.dhbw.swe.camping_site_mgt.person_mgt.GuestMgr;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;
import de.dhbw.swe.camping_site_mgt.person_mgt.VisitorsTaxClassMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.DeliverypointMgr;
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
	
	//////////////////////////////////
//	
//	BaseDataObjectMgr addressMgr = new AddressMgr(db);
//	BaseDataObjectMgr countryMgr = new CountryMgr(db);
//	BaseDataObjectMgr deliverypointMgr = new DeliverypointMgr(db);
//	BaseDataObjectMgr employeeRoleMgr = new EmployeeRoleMgr(db);
//	BaseDataObjectMgr equipmentMgr = new EquipmentMgr(db);
//	BaseDataObjectMgr siteMgr = new SiteMgr(db);
//	BaseDataObjectMgr townMgr = new TownMgr(db);
//	BaseDataObjectMgr durationMgr = new DurationMgr(db);
//
//	BaseDataObjectMgr chipcardMgr = new ChipcardMgr(db, (DurationMgr) durationMgr);
////	BaseDataObjectMgr billItemMgr = new BillItemMgr(db, theBillElementMgr);
//	BaseDataObjectMgr guestMgr = new GuestMgr(db, (AddressMgr) addressMgr, (TownMgr)townMgr, (CountryMgr) countryMgr);
//	BaseDataObjectMgr extraBookingMgr = new ExtraBookingMgr(db, (SiteMgr) siteMgr);
//	BaseDataObjectMgr employeeMgr = new EmployeeMgr(db, (AddressMgr) addressMgr, (TownMgr)townMgr, (CountryMgr) countryMgr, (ChipcardMgr) chipcardMgr, (EmployeeRoleMgr) employeeRoleMgr);
////	/* TODO -- in guest und employeemgr verlegen */ BaseDataObjectMgr personMgr = new PersonMgr(db, (AddressMgr) addressMgr, (TownMgr)townMgr, (CountryMgr) countryMgr);
//	BaseDataObjectMgr pitchMgr = new PitchMgr(db, (DeliverypointMgr) deliverypointMgr);
//	BaseDataObjectMgr serviceMgr = new ServiceMgr(db, (EmployeeRoleMgr) employeeRoleMgr, (PitchMgr) pitchMgr, (SiteMgr) siteMgr, (DeliverypointMgr) deliverypointMgr, (DurationMgr) durationMgr);
//	BaseDataObjectMgr pitchBookingMgr = new PitchBookingMgr(db, (PitchMgr) pitchMgr, (DurationMgr) durationMgr);
//	BaseDataObjectMgr bookingMgr = new BookingMgr(db, (GuestMgr) guestMgr, (PitchBookingMgr) pitchBookingMgr, (EquipmentMgr) equipmentMgr, (ExtraBookingMgr) extraBookingMgr, (ChipcardMgr) chipcardMgr, (DurationMgr) durationMgr);
////	BaseDataObjectMgr bookingMgr = new BookingMgr(db, (GuestMgr) guestMgr, (PitchBookingMgr) pitchBookingMgr, (ExtraBookingMgr) extraBookingMgr, (ChipcardMgr) chipcardMgr);
//	
//	
//	mgrs.put(AddressMgr.class, addressMgr);
//	mgrs.put(CountryMgr.class, countryMgr);
//	mgrs.put(DeliverypointMgr.class, deliverypointMgr);
//	mgrs.put(EmployeeRoleMgr.class, employeeMgr);
//	mgrs.put(EquipmentMgr.class, equipmentMgr);
//	mgrs.put(SiteMgr.class, siteMgr);
//	mgrs.put(TownMgr.class, townMgr);
//	mgrs.put(DurationMgr.class, durationMgr);
//	
//	mgrs.put(ChipcardMgr.class, chipcardMgr);
////	mgrs.put(BillItemMgr.class, billItemMgr);
//	mgrs.put(GuestMgr.class, guestMgr);
//	mgrs.put(ExtraBookingMgr.class, extraBookingMgr);
//	mgrs.put(EmployeeMgr.class, employeeMgr);
////	mgrs.put(PersonMgr.class, personMgr);
//	mgrs.put(PitchMgr.class, pitchMgr);
//	mgrs.put(PitchBookingMgr.class, pitchBookingMgr);
//	mgrs.put(ServiceMgr.class, serviceMgr);
//	mgrs.put(BookingMgr.class, bookingMgr);
//	
	//////////////////////////////////////////////////
	

//	final BaseDataObjectMgr billItemMgr = new BillElementMgr(db);
//	final BaseDataObjectMgr chipCardMgr = new ChipcardMgr(db);
//	final BaseDataObjectMgr countryMgr = new CountryMgr(db);
//	final BaseDataObjectMgr employeeRoleMgr = new EmployeeRoleMgr(db);
//	final BaseDataObjectMgr pitchBookingMgr = new PitchBookingMgr(db);
//	final BaseDataObjectMgr siteMgr = new SiteMgr(db);
//	final BaseDataObjectMgr townMgr = new TownMgr(db);
//	final BaseDataObjectMgr visitorsTaxClassMgr = new VisitorsTaxClassMgr(db);
//
//	mgrs.put(BillElementMgr.class, billItemMgr);
//	mgrs.put(ChipcardMgr.class, chipCardMgr);
//	mgrs.put(CountryMgr.class, countryMgr);
//	mgrs.put(EmployeeRoleMgr.class, employeeRoleMgr);
//	mgrs.put(PitchBookingMgr.class, pitchBookingMgr);
//	mgrs.put(SiteMgr.class, siteMgr);
//	mgrs.put(TownMgr.class, townMgr);
//	mgrs.put(VisitorsTaxClassMgr.class, visitorsTaxClassMgr);
//
//	final BaseDataObjectMgr billMgr = new BillItemMgr(db, (BillElementMgr) billItemMgr);
//	final BaseDataObjectMgr pitchMgr = new PitchMgr(db, (SiteMgr) siteMgr);
//	final BaseDataObjectMgr extraBookingMgr = new ExtraBookingMgr(db,
//		(SiteMgr) siteMgr);
//	final BaseDataObjectMgr serviceMgr = new ServiceMgr(db,
//		(PitchMgr) pitchMgr, (SiteMgr) siteMgr);
//	final BaseDataObjectMgr personMgr = new PersonMgr(db,
//		(CountryMgr) countryMgr, (TownMgr) townMgr);
//	final BaseDataObjectMgr employeeMgr = new EmployeeMgr(db,
//		(ChipcardMgr) chipCardMgr, (EmployeeRoleMgr) employeeRoleMgr,
//		(PersonMgr) personMgr);
//	final BaseDataObjectMgr guestMgr = new GuestMgr(db, (PersonMgr) personMgr,
//		(VisitorsTaxClassMgr) visitorsTaxClassMgr);
//
//	mgrs.put(BillItemMgr.class, billMgr);
//	mgrs.put(PitchMgr.class, pitchMgr);
//	mgrs.put(ExtraBookingMgr.class, extraBookingMgr);
//	mgrs.put(ServiceMgr.class, serviceMgr);
//	mgrs.put(PersonMgr.class, personMgr);
//	mgrs.put(EmployeeMgr.class, employeeMgr);
//	mgrs.put(GuestMgr.class, guestMgr);
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
