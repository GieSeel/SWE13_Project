package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;

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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#entry2object(java.util.HashMap)
     */
    @Override
    protected DataObject entry2object(final HashMap<String, Object> entry) {
	final String tableName = getTableName();
	int id;
	String area;
	String characteristics;
	Site deliveryPoint;
	int height;
	Pitch_NatureOfSoil natureOfSoil;
	Pitch_Type type;
	int width;
	int[] xCoords;
	int[] yCoords;

	id = (int) entry.get("id");
	area = (String) entry.get("area");
	characteristics = (String) entry.get("characteristics");
	deliveryPoint = (Site) SiteMgr.getInstance().objectGet(
		(int) entry.get("deliveryPoint"), tableName, id);
	height = (int) entry.get("height");
	natureOfSoil = Pitch_NatureOfSoil.values()[(int) entry.get("natureOfSoil")];
	xCoords = (int[]) entry.get("xCoords");
	yCoords = (int[]) entry.get("yCoords");
	type = Pitch_Type.values()[(int) entry.get("type")];
	width = (int) entry.get("width");

	return new Pitch(id, type, area, deliveryPoint, characteristics,
		natureOfSoil, width, height, xCoords, yCoords);
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
	return new Pitch().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectAdd(int,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectAdd(final int id, final DataObject dataObject) {
	final Pitch object = castObject(dataObject);
	final String tableName = object.getTableName();

	object.getDeliveryPoint().addUsage(tableName, id);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectInsert(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectInsert(final DataObject dataObject) {
	final Pitch object = castObject(dataObject);

	SiteMgr.getInstance().objectInsert(object.getDeliveryPoint());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectRemove(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectRemove(final DataObject dataObject) {
	final Pitch object = castObject(dataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Site site = object.getDeliveryPoint();
	site.delUsage(tableName, id);
	SiteMgr.getInstance().objectRemove(site);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#subObjectUpdate(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject,
     *      de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    protected void subObjectUpdate(final DataObject dataObject,
	    final DataObject newDataObject) {
	final Pitch object = castObject(dataObject);
	final Pitch newObject = castObject(newDataObject);
	final int id = object.getId();
	final String tableName = object.getTableName();

	final Site site = object.getDeliveryPoint();
	site.delUsage(tableName, id);
	SiteMgr.getInstance().objectUpdate(site, newObject.getDeliveryPoint());
    }

    /**
     * Cast {@link DataObject} to {@link Pitch} object.
     * 
     * @param dataObject
     *            the {@link DataObject}
     * @return the {@link Pitch} object
     */
    private Pitch castObject(final DataObject dataObject) {
	return (Pitch) dataObject;
    }
}