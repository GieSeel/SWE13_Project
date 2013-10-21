package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Pitch} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class PitchMgr extends BaseDataObjectMgr {

    /** The {@link SiteMgr}. */
    private static SiteMgr siteMgr;

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theSiteMgr
     *            the {@link SiteMgr}
     */
    public PitchMgr(final AccessableDatabase db, final SiteMgr theSiteMgr) {
	super(db);
	siteMgr = theSiteMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Pitch().getTableName();
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
	subMgr.add(siteMgr);
	return subMgr;
    }

    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final String area = (String) map.get("area");
	final String characteristics = (String) map.get("characteristics");
	final int height = (int) map.get("height");

	final Pitch_NatureOfSoil natureOfSoil;
	final int natureOfSoilOrdinal = (int) map.get("natureOfSoil");
	natureOfSoil = Pitch_NatureOfSoil.values()[natureOfSoilOrdinal];

	final int[] xCoords = (int[]) map.get("xCoords");
	final int[] yCoords = (int[]) map.get("yCoords");

	final Pitch_Type type;
	final int typeOrdinal = (int) map.get("type");
	type = Pitch_Type.values()[typeOrdinal];

	final int width = (int) map.get("width");

	final Site deliveryPoint = (Site) map.get("site");

	return new Pitch(id, type, area, deliveryPoint, characteristics,
		natureOfSoil, width, height, xCoords, yCoords);
    }
}