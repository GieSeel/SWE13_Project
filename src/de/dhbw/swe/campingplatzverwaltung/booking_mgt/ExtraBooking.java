package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import de.dhbw.swe.campingplatzverwaltung.place_mgt.Site;

public class ExtraBooking {
    public ExtraBooking(final int id, final String labeling, final String name,
	    final Site site) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.name = name;
	this.site = site;
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
