package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.Chipcard;
import de.dhbw.swe.camping_site_mgt.common.ChipcardMgr;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.DurationMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.person_mgt.Guest;
import de.dhbw.swe.camping_site_mgt.person_mgt.GuestMgr;

public class BookingMgr extends BaseDataObjectMgr {

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     */
    public BookingMgr(final AccessableDatabase db, final GuestMgr theGuestMgr, PitchBookingMgr thePitchBookingMgr, EquipmentMgr theEquipmentMgr, ExtraBookingMgr theExtraBookingMgr, ChipcardMgr theChipcardMgr, DurationMgr theDurationMgr) {
//    	public BookingMgr(final AccessableDatabase db, final GuestMgr theGuestMgr, PitchBookingMgr thePitchBookingMgr, ExtraBookingMgr theExtraBookingMgr, ChipcardMgr theChipcardMgr, DurationMgr theDurationMgr) {
	super(db);
//	billMgr = theBillMgr;
	equipmentMgr = theEquipmentMgr;
	guestMgr = theGuestMgr;
    pitchBookingMgr = thePitchBookingMgr;
    extraBookingMgr = theExtraBookingMgr;
    chipcardMgr = theChipcardMgr;	
    durationMgr = theDurationMgr;	
	load();
    }

    @Override
    public String getTableName() {
	return new Booking().getTableName();
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
//	subMgr.add(billMgr);
	subMgr.add(guestMgr);
	subMgr.add(pitchBookingMgr);
	subMgr.add(equipmentMgr);
	subMgr.add(extraBookingMgr);
	subMgr.add(chipcardMgr);
	subMgr.add(durationMgr);
	return subMgr;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#map2DataObject(java.util.HashMap)
     */
    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
		int id = 0;
		if (map.containsKey("id")) {
		    id = (int) map.get("id");
		}
	    Guest guest = (Guest) map.get("guest");
	    Duration duration = (Duration) map.get("duration");
	    Vector<PitchBooking> pitchBookings = (Vector<PitchBooking>) map.get("pitchBookings");
	    Vector<Guest> fellowGuests = (Vector<Guest>) map.get("fellowGuests");
	    Vector<Equipment> equipments = (Vector<Equipment>) map.get("equipments");
	    Vector<ExtraBooking> extraBookings = (Vector<ExtraBooking>) map.get("extraBookings");
	    Vector<Chipcard> chipcards = (Vector<Chipcard>) map.get("chipcards");
	    Vector<BillItem> bill = (Vector<BillItem>) map.get("bill");
	
		return new Booking(id, guest, duration, pitchBookings, fellowGuests, equipments, extraBookings, chipcards, bill); 
    }

//    private final BillItemMgr billMgr;
    private final GuestMgr guestMgr;
    private PitchBookingMgr pitchBookingMgr;
    private EquipmentMgr equipmentMgr;
    private ExtraBookingMgr extraBookingMgr;
    private ChipcardMgr chipcardMgr;
    private DurationMgr durationMgr;
}
