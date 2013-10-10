package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Bill extends BaseDataObject {
    /**
     * Constructor.
     * 
     */
    public Bill() {
	this(0, new BillItem(), 0);
    }

    /**
     * Constructor.
     * 
     * @param number
     * @param billItem
     * @param multiplier
     */
    public Bill(final int number, final BillItem billItem, final int multiplier) {
	this(0, number, billItem, multiplier);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param number
     * @param billItem
     * @param multiplier
     */
    public Bill(final int id, final int number, final BillItem billItem,
	    final int multiplier) {
	super(id);
	this.number = number;
	this.billItem = billItem;
	this.multiplier = multiplier;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Bill object = (Bill) dataObject;
	if (this.billItem.equals(object.getBillItem())
		&& this.multiplier == object.getMultiplier()
		&& this.number == object.getNumber()) {
	    return true;
	}
	return false;
    }

    public BillItem getBillItem() {
	return billItem;
    }

    /**
     * Returns the multiplier.
     * 
     * @return the multiplier
     */
    public int getMultiplier() {
	return multiplier;
    }

    /**
     * Returns the number.
     * 
     * @return the number
     */
    public int getNumber() {
	return number;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "bill";
    }

    private final BillItem billItem;
    private final int multiplier;
    private final int number;
}
