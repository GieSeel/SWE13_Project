package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.ChipCard;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class ChipCardList {
    public ChipCardList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.chipCard = DatabaseController.getInstance().querySelectChipCard(
		(int) elements.get("chipCard_ID"));
    }

    public ChipCardList(final int number, final ChipCard chipCard) {
	super();
	this.id = 0;
	this.number = number;
	this.chipCard = chipCard;
    }

    public ChipCardList(final int id, final int number, final ChipCard chipCard) {
	super();
	this.id = id;
	this.number = number;
	this.chipCard = chipCard;
    }

    public ChipCard getChipCard() {
	return chipCard;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.chipCard.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();

	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put(
		"chipCard_ID",
		DatabaseController.getInstance().queryInsertUpdateChipCard(
			this.chipCard));
	return elements;
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
