package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class BillItem extends BaseDataObject {
    public BillItem() {
	this(null, null, null);
    }

    public BillItem(final BillItem_Labeling labeling, final Euro priceBusySeason,
	    final Euro priceLowSeason) {
	this(0, labeling, priceBusySeason, priceLowSeason);
    }

    public BillItem(final int id, final BillItem_Labeling labeling,
	    final Euro priceBusySeason, final Euro priceLowSeason) {
	super(id);
	this.labeling = labeling;
	this.priceBusySeason = priceBusySeason;
	this.priceLowSeason = priceLowSeason;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final BillItem object = (BillItem) dataObject;
	if (this.labeling.equals(object.getLabeling())
		&& this.priceBusySeason.equals(object.getPriceBusySeason())
		&& this.priceLowSeason.equals(object.getPriceLowSeason())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the labeling.
     * 
     * @return the labeling
     */
    public BillItem_Labeling getLabeling() {
	return labeling;
    }

    /**
     * Returns the priceBusySeason.
     * 
     * @return the priceBusySeason
     */
    public Euro getPriceBusySeason() {
	return priceBusySeason;
    }

    /**
     * Returns the priceLowSeason.
     * 
     * @return the priceLowSeason
     */
    public Euro getPriceLowSeason() {
	return priceLowSeason;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "billitem";
    }

    private final BillItem_Labeling labeling;
    private final Euro priceBusySeason;
    private final Euro priceLowSeason;
}
