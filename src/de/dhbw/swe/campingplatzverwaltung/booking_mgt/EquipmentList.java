package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class EquipmentList {
    public EquipmentList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.equipment = DatabaseController.getInstance().querySelectEquipment(
		(int) elements.get("equipment_ID"));
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.equipment.getData());
	return data;
    }

    public Equipment getEquipment() {
	return equipment;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put(
		"equipment_ID",
		DatabaseController.getInstance().queryInsertUpdateEquipment(
			this.equipment));
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    private final Equipment equipment;
    private final int id;
    private final int number;
}
