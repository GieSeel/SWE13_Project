package de.dhbw.swe.camping_site_mgt.common;

public class Town {

    /**
     * Constructor for empty object.
     * 
     */
    public Town() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param name
     * @param postalCode
     */
    public Town(final int id, final String name, final String postalCode) {
	this.id = id;
	this.name = name;
	this.postalCode = postalCode;
	this.usage = new Usage();
    }

    /**
     * Constructor.
     * 
     * @param name
     * @param postalCode
     */
    public Town(final String name, final String postalCode) {
	this(0, name, postalCode);
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
	final Town object = (Town) obj;
	if (this.name.equals(object.getName())
		&& this.postalCode.equals(object.getPostalCode())) {
	    setId(object.getId());
	    setUsage(object.getUsage());
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
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the postalCode.
     * 
     * @return the postalCode
     */
    public String getPostalCode() {
	return postalCode;
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
    private final String name;
    private final String postalCode;
    private Usage usage;
}
