package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import de.dhbw.swe.campingplatzverwaltung.place_mgt.Site;

public class ExtraBookingList {
    public ExtraBookingList(final int id, final int number, final Site site) {
	super();
	this.id = id;
	this.number = number;
	this.site = site;
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
