package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class Site {
    public Site(final int id, final String description, final String labeling,
	    final String openingHours, final String type) {
	super();
	this.id = id;
	this.description = description;
	this.labeling = labeling;
	this.openingHours = openingHours;
	this.type = type;
    }

    public String getDescription() {
	return description;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public String getOpeningHours() {
	return openingHours;
    }

    public String getType() {
	return type;
    }

    private final String description;
    private final int id;
    private final String labeling;
    private final String openingHours;
    private final String type;
    // private final Site_Type type;
}
