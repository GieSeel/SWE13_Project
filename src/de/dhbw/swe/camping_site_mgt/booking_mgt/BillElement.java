package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

@Deprecated
public class BillElement extends BaseDataObject {
    public BillElement() {
	this(null, null, null);
    }

    public BillElement(final BillElement_Labeling labeling, final Integer price_busy_season,
	    final Integer price_low_season) {
	this(0, labeling, price_busy_season, price_low_season);
    }

    public BillElement(final int id, final BillElement_Labeling labeling,
	    final Integer price_busy_season, final Integer price_low_season) {
	super(id);
	this.labeling = labeling;
	this.price_busy_season = price_busy_season;
	this.price_low_season = price_low_season;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final BillElement object = (BillElement) dataObject;
	if (this.labeling.equals(object.getLabeling())
		&& this.price_busy_season.equals(object.getPriceBusySeason())
		&& this.price_low_season.equals(object.getPriceLowSeason())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the labeling.
     * 
     * @return the labeling
     */
    public BillElement_Labeling getLabeling() {
	return labeling;
    }

    /**
     * Returns the price_busy_season.
     * 
     * @return the price_busy_season
     */
    public Integer getPriceBusySeason() {
	return price_busy_season;
    }

    /**
     * Returns the price_low_season.
     * 
     * @return the price_low_season
     */
    public Integer getPriceLowSeason() {
	return price_low_season;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "bill_item";
    }

    private final BillElement_Labeling labeling;
    private final Integer price_busy_season;
    private final Integer price_low_season;
}
