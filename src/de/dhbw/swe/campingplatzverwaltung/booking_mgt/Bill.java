package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Bill {
    public Bill(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.number = (int) elements.get("number");
	this.billItem = DatabaseController.getInstance().querySelectBillItem(
		(int) elements.get("billItem_ID"));
	this.multiplier = (int) elements.get("multiplier");
    }

    public Bill(final int number, final BillItem billItem, final int multiplier) {
	super();
	this.id = 0;
	this.number = number;
	this.billItem = billItem;
	this.multiplier = multiplier;
    }

    public Bill(final int id, final int number, final BillItem billItem,
	    final int multiplier) {
	super();
	this.id = id;
	this.number = number;
	this.billItem = billItem;
	this.multiplier = multiplier;
    }

    public BillItem getBillItem() {
	return billItem;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.multiplier);
	data.add(this.number);
	data.addAll(this.billItem.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("number", this.number);
	elements.put(
		"billItem_ID",
		DatabaseController.getInstance().queryInsertUpdateBillItem(
			this.billItem));
	elements.put("multiplier", this.multiplier);
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getMultiplier() {
	return multiplier;
    }

    public int getNumber() {
	return number;
    }

    private final BillItem billItem;
    private final int id;
    private final int multiplier;
    private final int number;
}
