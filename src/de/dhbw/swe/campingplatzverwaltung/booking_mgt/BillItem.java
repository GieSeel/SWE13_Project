package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import de.dhbw.swe.campingplatzverwaltung.common.Euro;

public class BillItem {
    public BillItem(final int id, final String labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	this.id = id;
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
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
