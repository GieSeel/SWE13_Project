package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.Usage;

public class Site {

    /**
     * Constructor.
     * 
     */
    public Site() {
	this(null, null, null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param description
     * @param labeling
     * @param openingHours
     * @param type
     */
    public Site(final int id, final String description, final String labeling,
	    final String openingHours, final String type) {
	this.description = description;
	this.id = id;
	this.labeling = labeling;
	this.openingHours = openingHours;
	this.type = type;
	this.usage = new Usage();
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param labeling
     * @param openingHours
     * @param type
     */
    public Site(final String description, final String labeling,
	    final String openingHours, final String type) {
	this(0, description, labeling, openingHours, type);
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
	final Site object = (Site) obj;
	if (this.description.equals(object.getDescription())
		&& this.labeling.equals(object.getLabeling())
		&& this.openingHours.equals(object.getOpeningHours())
		&& this.type.equals(object.getType())) {
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
     * Returns the openingHours.
     * 
     * @return the openingHours
     */
    public String getOpeningHours() {
	return openingHours;
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public String getType() {
	return type;
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

    private final String description;
    private int id;
    private final String labeling;
    private final String openingHours;
    private final String type;
    private Usage usage;
}
