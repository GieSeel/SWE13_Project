package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
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
    public GuestMgr(final AccessableDatabase db, final PersonMgr thePersonMgr,
	    final VisitorsTaxClassMgr theVisitorsTaxClassMgr) {
	super(db);
	personMgr = thePersonMgr;
	visitorsTaxClassMgr = theVisitorsTaxClassMgr;
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
	subMgr.add(personMgr);
	subMgr.add(visitorsTaxClassMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final Person person = (Person) map.get("person");
	final VisitorsTaxClass visitorsTaxClass = (VisitorsTaxClass) map.get("visitorstaxclass");

	return new Guest(id, person, visitorsTaxClass);
    }

    /** The {@link PersonMgr}. */
    private final PersonMgr personMgr;

    /** The {@link VisitorsTaxClassMgr}. */
    private final VisitorsTaxClassMgr visitorsTaxClassMgr;
}
