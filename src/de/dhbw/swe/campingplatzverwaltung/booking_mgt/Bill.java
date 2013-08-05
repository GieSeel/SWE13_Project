package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class Bill {
    public Bill(final int id, final int number, final BillItem billItem,
	    final int multiplier) {
	this.id = id;
	this.number = number;
	this.billItem = billItem;
	this.multiplier = multiplier;
    }

    public BillItem getBillItem() {
	return billItem;
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
