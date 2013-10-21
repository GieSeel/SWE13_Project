package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
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
    public BookingMgr(final AccessableDatabase db, final BillMgr theBillMgr,
	    final GuestMgr theGuestMgr) {
	super(db);
	billMgr = theBillMgr;
	guestMgr = theGuestMgr;
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
	subMgr.add(billMgr);
	subMgr.add(guestMgr);
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
	final int[] chipCards = (int[]) map.get("chipCards");
	final int[] equipments = (int[]) map.get("equipments");
	final int[] extraBookings = (int[]) map.get("extraBookings");
	final int[] fellowGuests = (int[]) map.get("fellowGuests");
	final Date from = (Date) map.get("from");
	final int[] pitchBookings = (int[]) map.get("pitchBookings");
	final Date until = (Date) map.get("until");

	final Bill bill = (Bill) map.get("bill");
	final Guest guest = (Guest) map.get("guest");
	// TODO responsibleGuest => guest ?

	return new Booking(id, bill, chipCards, equipments, extraBookings,
		fellowGuests, from, pitchBookings, guest, until);
    }

    private final BillMgr billMgr;
    private final GuestMgr guestMgr;
}
