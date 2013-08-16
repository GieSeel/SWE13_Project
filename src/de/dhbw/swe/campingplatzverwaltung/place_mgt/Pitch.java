package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Pitch {

    public Pitch(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.characteristics = (String) elements.get("characteristics");
	this.deliveryPoint = DatabaseController.getInstance().querySelectSite(
		(int) elements.get("deliveryPoint"));
	this.district = (String) elements.get("district");
	this.length = (int) elements.get("length");
	this.natureOfSoil = (String) elements.get("natureOfSoil");
	this.type = (String) elements.get("type");
	this.width = (int) elements.get("width");
    }

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

    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String district, final int length, final String natureOfSoil,
	    final String type, final int width) {
	super();
	this.id = 0;
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

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.characteristics);
	data.add(this.district);
	data.add(this.length);
	data.add(this.natureOfSoil);
	data.add(this.type);
	data.add(this.width);
	data.addAll(this.deliveryPoint.getData());
	return data;
    }

    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    public String getDistrict() {
	return district;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("characteristics", this.characteristics);
	elements.put(
		"deliveryPoint",
		DatabaseController.getInstance().queryInsertUpdateSite(
			this.deliveryPoint));
	elements.put("district", this.district);
	elements.put("length", this.length);
	elements.put("natureOfSoil", this.natureOfSoil);
	elements.put("type", this.type);
	elements.put("width", this.width);

	return elements;
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
    // private final Pitch_NatureOfSoil natureOfSoil;
    private final String natureOfSoil;
    // private final Pitch_Type type;
    private final String type;
    private final int width;
}
