package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

@Deprecated
public class EquipmentList {
    public EquipmentList() {
	super();
	this.id = 0;
	this.number = 0;
	this.equipment = null;
    }

    public EquipmentList(final int number, final Equipment equipment) {
	super();
	this.id = 0;
	this.number = number;
	this.equipment = equipment;
    }

    public EquipmentList(final int id, final int number, final Equipment equipment) {
	super();
	this.id = id;
	this.number = number;
	this.equipment = equipment;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put(
		"equipment_ID",
		DatabaseController.getInstance().queryInsertUpdateEquipment(
			this.equipment));
	return objects;
    }

    public Equipment getEquipment() {
	return equipment;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "equipmentlist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.id));

	// objects.putAll(this.equipment.getTableData(className));

	return objects;
    }

    public EquipmentList setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.equipment = db.querySelectEquipment((int) objects.get("equipment_ID"));
	setData(objects);
	return this;
    }

    public EquipmentList setTableData(final HashMap<String, Object> objects) {
	final String className = "equipmentlist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), equipmentMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("equipment_")) {
		equipmentMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	// this.equipment = new Equipment().setTableData(equipmentMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private Equipment equipment;
    private int id;
    private int number;
}
