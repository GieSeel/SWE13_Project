package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Pitch} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PitchMgr extends BaseDataObjectMgr {
    /** The singleton instance. */
    private static PitchMgr instance;

    /** The {@link SiteMgr}. */
    private static SiteMgr siteMgr = SiteMgr.getInstance();

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized PitchMgr getInstance() {
	if (instance == null) {
	    instance = new PitchMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private PitchMgr() {
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Pitch().getTableName();
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
	subMgr.add(siteMgr);
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
	final String area = (String) map.get("area");
	final String characteristics = (String) map.get("characteristics");
	final int height = (int) map.get("height");
	final Pitch_NatureOfSoil natureOfSoil = Pitch_NatureOfSoil.values()[(int) map.get("natureOfSoil")];
	final int[] xCoords = (int[]) map.get("xCoords");
	final int[] yCoords = (int[]) map.get("yCoords");
	final Pitch_Type type = Pitch_Type.values()[(int) map.get("type")];
	final int width = (int) map.get("width");

	final Site deliveryPoint = (Site) map.get("deliveryPoint");

	return new Pitch(id, type, area, deliveryPoint, characteristics,
		natureOfSoil, width, height, xCoords, yCoords);
    }
}