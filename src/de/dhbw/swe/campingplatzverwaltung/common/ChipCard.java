package de.dhbw.swe.campingplatzverwaltung.common;

import java.util.Date;

public class ChipCard {
    public ChipCard(final int id, final int number, final Date validFrom,
	    final Date validTo) {
	this.id = id;
	this.number = number;
	this.validFrom = validFrom;
	this.validTo = validTo;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public Date getValidFrom() {
	return validFrom;
    }

    public Date getValidTo() {
	return validTo;
    }

    private final int id;
    private final int number;
    private final Date validFrom;
    private final Date validTo;
}
