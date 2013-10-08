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
	this.usage = new Usage();
    }

    /**
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void addUsage(final String parentTableName, final int parentID) {
	usage.addUsage(parentTableName, parentID);
    }

    /**
     * Deletes entry from usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void delUsage(final String parentTableName, final int parentID) {
	usage.delUsage(parentTableName, parentID);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final ChipCard object = (ChipCard) obj;
	if (this.validFrom.equals(object.getValidFrom())
		&& this.validTo.equals(object.getValidTo())) {
	    return true;
	}
	return false;
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
     * Returns the usage.
     * 
     * @return the usage
     */
    public Usage getUsage() {
	return usage;
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
     * Checks if the object is still in use.
     * 
     * @return true if it's still in use
     */
    public boolean isInUse() {
	return usage.isInUse();
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
    private final Usage usage;
    private final Date validFrom;
    private final Date validTo;
}
