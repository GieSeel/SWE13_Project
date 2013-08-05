package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class EquipmentList {
    public EquipmentList(final int id, final int number, final Equipment equipment) {
	super();
	this.id = id;
	this.number = number;
	this.equipment = equipment;
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

    private final Equipment equipment;
    private final int id;
    private final int number;
}
