package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.Usage;

public class EmployeeRole {

    /**
     * Constructor.
     * 
     */
    public EmployeeRole() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param id
     * @param labeling
     */
    public EmployeeRole(final int id, final String arrangement,
	    final String labeling) {
	this.arrangement = arrangement;
	this.id = id;
	this.labeling = labeling;
	this.usage = new Usage();
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param labeling
     */
    public EmployeeRole(final String arrangement, final String labeling) {
	this(0, arrangement, labeling);
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
	final EmployeeRole object = (EmployeeRole) obj;
	if (this.arrangement.equals(object.getArrangement())
		&& this.labeling.equals(object.getLabeling())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the arrangement.
     * 
     * @return the arrangement
     */
    public String getArrangement() {
	return arrangement;
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

    private final String arrangement;
    private int id;
    private final String labeling;
    private Usage usage;
}
