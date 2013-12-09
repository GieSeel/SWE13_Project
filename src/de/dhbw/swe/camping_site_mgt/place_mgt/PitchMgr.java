package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.IntArrayParser;
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

    /** The {@link DeliverypointMgr}. */
    private DeliverypointMgr deliverypointMgr;

    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theDeliverypointMgr
     *            the {@link DeliverypointMgr}
     */
    public PitchMgr(final AccessableDatabase db, final DeliverypointMgr theDeliverypointMgr) {
	super(db);
	deliverypointMgr = theDeliverypointMgr;
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
	subMgr.add(deliverypointMgr);
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

	final Pitch_NatureOfSoil nature_of_soil;
	final int nature_of_soilOrdinal = (int) map.get("nature_of_soil");
	nature_of_soil = Pitch_NatureOfSoil.values()[nature_of_soilOrdinal];

	final int[] x_coords = IntArrayParser.parseArray((String) map.get("x_coords"));
	final int[] y_coords = IntArrayParser.parseArray((String) map.get("y_coords"));

	final Pitch_Type type;
	final int typeOrdinal = (int) map.get("type");
	type = Pitch_Type.values()[typeOrdinal];

	final int width = (int) map.get("width");

	final Deliverypoint deliveryPoint = (Deliverypoint) map.get("deliverypoint");

	return new Pitch(id, type, area, deliveryPoint, characteristics,
		nature_of_soil, width, height, x_coords, y_coords);
    }
}