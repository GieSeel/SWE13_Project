package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class VisitorsTaxClass extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public VisitorsTaxClass() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param labeling
     * @param price
     */
    public VisitorsTaxClass(final int id, final String labeling, final Euro price) {
	super(id);
	this.labeling = labeling;
	this.price = price;
    }

    /**
     * Constructor.
     * 
     * @param labeling
     * @param price
     */
    public VisitorsTaxClass(final String labeling, final Euro price) {
	this(0, labeling, price);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final VisitorsTaxClass object = (VisitorsTaxClass) dataObject;
	if (object.labeling.equals(object.getLabeling())
		&& this.price.equals(object.getPrice())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the labeling.
     * 
     * @return the labeling
     */
    public String getLabeling() {
	return labeling;
    }

    /**
     * Returns the price.
     * 
     * @return the price
     */
    public Euro getPrice() {
	return price;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "visitorstaxclass";
    }

    private final String labeling;

    private final Euro price;
}