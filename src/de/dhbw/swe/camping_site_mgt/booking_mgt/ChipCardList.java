package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.ChipCard;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

@Deprecated
public class ChipCardList {
    public ChipCardList() {
	super();
	this.id = 0;
	this.number = 0;
	this.chipCard = null;
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

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();

	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put(
		"chipCard_ID",
		DatabaseController.getInstance().queryInsertUpdateChipCard(
			this.chipCard));
	return objects;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "chipcardlist_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));

	// objects.putAll(this.chipCard.getTableData(className));

	return objects;
    }

    public ChipCardList setDatabaseData(final HashMap<String, Object> objects) {
	this.chipCard = DatabaseController.getInstance().querySelectChipCard(
		(int) objects.get("chipCard_ID"));
	setData(objects);
	return this;
    }

    public ChipCardList setTableData(final HashMap<String, Object> objects) {
	final String className = "chipcardlist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), chipcardMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("chipcard_")) {
		chipcardMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	// this.chipCard = new ChipCard().setTableData(chipcardMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
    }

    private ChipCard chipCard;
    private int id;
    private int number;
}
