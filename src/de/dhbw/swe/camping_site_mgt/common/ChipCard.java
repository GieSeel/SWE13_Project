package de.dhbw.swe.camping_site_mgt.common;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class ChipCard extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public ChipCard() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param validFrom
     * @param validTo
     */
    public ChipCard(final Date validFrom, final Date validTo) {
	this(0, validFrom, validTo);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param validFrom
     * @param validTo
     */
    public ChipCard(final int id, final Date validFrom, final Date validTo) {
	super(id);
	this.validFrom = validFrom;
	this.validTo = validTo;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final ChipCard object = (ChipCard) dataObject;
	if (this.validFrom.equals(object.getValidFrom())
		&& this.validTo.equals(object.getValidTo())) {
	    return true;
	}
	return false;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "chipcard";
    }

    /**
     * Returns the validFrom.
     * 
     * @return the validFrom
     */
    public Date getValidFrom() {
	return validFrom;
    }

    /**
     * Returns the validTo.
     * 
     * @return the validTo
     */
    public Date getValidTo() {
	return validTo;
    }

    private final Date validFrom;

    private final Date validTo;
}
