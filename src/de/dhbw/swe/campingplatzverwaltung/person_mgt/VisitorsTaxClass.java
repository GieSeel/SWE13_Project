package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import de.dhbw.swe.campingplatzverwaltung.common.Euro;

public class VisitorsTaxClass {

    public VisitorsTaxClass(final int id, final String labeling, final Euro price) {
	super();
	this.id = id;
	this.labeling = labeling;
	this.price = price;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    public Euro getPrice() {
	return price;
    }

    private final int id;
    private final String labeling;
    private final Euro price;
    // private final VisitorsTaxClass_Labeling labeling;

}
