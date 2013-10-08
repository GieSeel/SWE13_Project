package de.dhbw.swe.camping_site_mgt.common;

import java.util.Date;

public class ChipCard {

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
	this.id = id;
	this.validFrom = validFrom;
	this.validTo = validTo;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() {
	return id;
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

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    private int id;
    private final Date validFrom;
    private final Date validTo;
}
