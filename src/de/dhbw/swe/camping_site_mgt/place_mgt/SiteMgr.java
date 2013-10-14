package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;

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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	int id;
	String description;
	String labeling;
	String openingHours;
	Site_Type type;

	id = (int) entry.get("id");
	description = (String) entry.get("description");
	labeling = (String) entry.get("labeling");
	openingHours = (String) entry.get("openingHours");
	type = Site_Type.values()[(int) entry.get("type")];

	return new Site(id, description, labeling, openingHours, type);
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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    protected String getTableName() {
	return new Site().getTableName();
    }
}