package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.Site;

public class ExtraBooking {
    public ExtraBooking(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.labeling = (String) elements.get("labeling");
	this.name = (String) elements.get("name");
	this.site = DatabaseController.getInstance().querySelectSite(
		(int) elements.get("site_ID"));
    }

    public ExtraBooking(final int id, final String labeling, final String name,
	    final Site site) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.name = name;
	this.site = site;
    }

    public ExtraBooking(final String labeling, final String name, final Site site) {
	super();
	this.id = 0;
	this.labeling = labeling;
	this.name = name;
	this.site = site;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.labeling);
	data.add(this.name);
	data.addAll(this.site.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("labeling", this.labeling);
	elements.put("name", this.name);
	elements.put("site_ID",
		DatabaseController.getInstance().queryInsertUpdateSite(this.site));
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public String getName() {
	return name;
    }

    public Site getSite() {
	return site;
    }

    private final int id;
    private final String labeling;
    private final String name;
    private final Site site;
}
