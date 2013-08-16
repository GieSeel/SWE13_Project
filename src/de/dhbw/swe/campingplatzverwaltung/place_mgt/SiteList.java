package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class SiteList {
    public SiteList(final HashMap<String, Object> elements) {
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.site = DatabaseController.getInstance().querySelectSite(
		(int) elements.get("site_ID"));
    }

    public SiteList(final int id, final int number, final Site site) {
	super();
	this.id = id;
	this.number = number;
	this.site = site;
    }

    public SiteList(final int number, final Site site) {
	super();
	this.id = 0;
	this.number = number;
	this.site = site;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.site.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put("site_ID",
		DatabaseController.getInstance().queryInsertUpdateSite(this.site));
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Site getSite() {
	return site;
    }

    private final int id;
    private final int number;
    private final Site site;
}
