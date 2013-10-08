package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.Euro;

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
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    private int id;
    private final String labeling;
    private final Euro price;
}