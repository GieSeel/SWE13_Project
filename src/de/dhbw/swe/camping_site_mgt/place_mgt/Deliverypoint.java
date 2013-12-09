package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Deliverypoint extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Deliverypoint() {
	this(0, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param description
     */
    public Deliverypoint(final int id, final String description) {
	super(id);
	this.description = description;
    }


    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Deliverypoint object = (Deliverypoint) dataObject;
	if (this.description.equals(object.getDescription()) && this.getId() ==  object.getId()) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "deliverypoint";
    }

    private final String description;
}
