package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class BillItem extends BaseDataObject {
    /**
     * Constructor.
     * 
     */
    public BillItem() {
	this(0, new BillElement(), 0);
    }

    /**
     * Constructor.
     * 
     * @param current_price
     * @param billItem
     * @param multiplier
     */
    public BillItem(final int current_price, final BillElement billItem, final int multiplier) {
	this(0, current_price, billItem, multiplier);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param current_price
     * @param billItem
     * @param multiplier
     */
    public BillItem(final int id, final int current_price, final BillElement billItem,
	    final int multiplier) {
	super(id);
	this.current_price = current_price;
	this.billElement = billItem;
	this.multiplier = multiplier;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final BillItem object = (BillItem) dataObject;
	if (this.billElement.equals(object.getBillItem())
		&& this.multiplier == object.getMultiplier()
		&& this.current_price == object.getCurrentPrice()) {
	    return true;
	}
	return false;
    }

    public BillElement getBillItem() {
	return billElement;
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
     * Returns the current_price.
     * 
     * @return the current_price
     */
    public int getCurrentPrice() {
	return current_price;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "booking_bill_item";
    }

    private final BillElement billElement;
    private final int multiplier;
    private final int current_price;
}
