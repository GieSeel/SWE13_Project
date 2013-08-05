package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class Pitch {

    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String district, final int length,
	    final String natureOfSoil, final String type, final int width) {
	super();
	this.id = id;
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.district = district;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
    }

    public String getCharacteristics() {
	return characteristics;
    }

    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    public String getDistrict() {
	return district;
    }

    public int getId() {
	return id;
    }

    public int getLength() {
	return length;
    }

    public String getNatureOfSoil() {
	return natureOfSoil;
    }

    public String getType() {
	return type;
    }

    public int getWidth() {
	return width;
    }

    private final String characteristics;
    private final Site deliveryPoint;
    private final String district;
    private final int id;
    private final int length;
    private final String natureOfSoil;
    private final String type;
    // private final Pitch_NatureOfSoil natureOfSoil;
    // private final Pitch_Type type;
    private final int width;
}
