package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.Usage;

public class VisitorsTaxClass {

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
	this.id = id;
	this.labeling = labeling;
	this.price = price;
	this.usage = new Usage();
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
	final VisitorsTaxClass object = (VisitorsTaxClass) obj;
	if (object.labeling.equals(object.getLabeling())
		&& this.price.equals(object.getPrice())) {
	    setId(object.getId());
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
     * Returns the usage.
     * 
     * @return the usage
     */
    public Usage getUsage() {
	return usage;
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

    /**
     * Sets the usage.
     * 
     * @param usage
     *            the usage to set
     */
    public void setUsage(final Usage usage) {
	this.usage = usage;
    }

    private int id;
    private final String labeling;
    private final Euro price;
    private Usage usage;
}