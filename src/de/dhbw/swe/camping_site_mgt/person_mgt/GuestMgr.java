package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Guest} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class GuestMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static GuestMgr instance;

    /** The {@link PersonMgr}. */
    private static PersonMgr personMgr = PersonMgr.getInstance();

    /** The {@link VisitorsTaxClassMgr}. */
    private static VisitorsTaxClassMgr visitorsTaxClassMgr = VisitorsTaxClassMgr.getInstance();

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized GuestMgr getInstance() {
	if (instance == null) {
	    instance = new GuestMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private GuestMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Guest().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#evenUpdateInUse()
     */
    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getLogger()
     */
    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getSubMgr()
     */
    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(personMgr);
	subMgr.add(visitorsTaxClassMgr);
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
	final Person person = (Person) map.get("person");
	final VisitorsTaxClass visitorsTaxClass = (VisitorsTaxClass) map.get("visitorsTaxClass");

	return new Guest(id, person, visitorsTaxClass);
    }
}
