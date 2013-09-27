package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Bill {
    public Bill() {
	super();
	this.id = 0;
	this.number = 0;
	this.billItem = null;
	this.multiplier = 0;
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

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("number", this.number);
	objects.put(
		"billItem_ID",
		DatabaseController.getInstance().queryInsertUpdateBillItem(
			this.billItem));
	objects.put("multiplier", this.multiplier);
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "bill_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "multiplier", new Integer(this.multiplier));
	objects.put(className + "number", new Integer(this.number));

	objects.putAll(this.billItem.getTableData(className));

	return objects;
    }

    public Bill setDatabaseData(final HashMap<String, Object> objects) {
	this.billItem = DatabaseController.getInstance().querySelectBillItem(
		(int) objects.get("billItem_ID"));
	setData(objects);
	return this;
    }

    public Bill setTableData(final HashMap<String, Object> objects) {
	final String className = "bill_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), billitemMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("billitem_")) {
		billitemMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.billItem = new BillItem().setTableData(billitemMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.number = (int) objects.get("number");
	this.multiplier = (int) objects.get("multiplier");
    }

    private BillItem billItem;
    private int id;
    private int multiplier;
    private int number;
}
