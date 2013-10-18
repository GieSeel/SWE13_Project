package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Site} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class SiteMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static SiteMgr instance;

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized SiteMgr getInstance() {
	if (instance == null) {
	    instance = new SiteMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private SiteMgr() {
	super();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Site().getTableName();
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
	return null;
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
	final String description = (String) map.get("description");
	final String labeling = (String) map.get("labeling");
	final String openingHours = (String) map.get("openingHours");
	final Site_Type type = Site_Type.values()[(int) map.get("type")];

	return new Site(id, description, labeling, openingHours, type);
    }
}