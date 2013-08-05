package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import de.dhbw.swe.campingplatzverwaltung.common.ChipCard;

public class ChipCardList {
    public ChipCardList(final int id, final int number, final ChipCard chipCard) {
	super();
	this.id = id;
	this.number = number;
	this.chipCard = chipCard;
    }

    public ChipCard getChipCard() {
	return chipCard;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    private final ChipCard chipCard;
    private final int id;

    private final int number;
}
