package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.Euro;

public class BillItem {
    public BillItem(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.labeling = (String) elements.get("labeling");
	this.priceBusySeason = new Euro((float) elements.get("priceBusySeason"));
	this.priceLowSeason = new Euro((float) elements.get("priceLowSeason"));
    }

    public BillItem(final int id, final String labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
    }

    public BillItem(final String labeling, final Euro priceBusySeason,
	    final Euro priceLowSeason) {
	super();
	this.id = 0;
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.labeling);
	data.add(this.priceBusySeason.toString());
	data.add(this.priceLowSeason.toString());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("labeling", this.labeling);
	elements.put("priceBusySeason", this.priceBusySeason.returnValue());
	elements.put("priceLowSeason", this.priceLowSeason.returnValue());
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public Euro getPriceBusySeason() {
	return priceBusySeason;
    }

    public Euro getPriceLowSeason() {
	return priceLowSeason;
    }

    private final int id;
    // private final BillItem_Labeling labeling;
    private final String labeling;
    private final Euro priceBusySeason;
    private final Euro priceLowSeason;
}
