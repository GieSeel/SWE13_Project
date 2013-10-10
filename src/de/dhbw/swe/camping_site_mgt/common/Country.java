package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country implements DataObject {

    /**
     * Constructor for empty object.
     * 
     */
    public Country() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param acronym
     * @param name
     */
    public Country(final int id, final String acronym, final String name) {
	this.id = id;
	this.acronym = acronym;
	this.name = name;
	this.usage = new Usage();
    }

    /**
     * Constructor.
     * 
     * @param acronym
     * @param name
     */
    public Country(final String acronym, final String name) {
	this(0, acronym, name);
    }

    /**
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    @Override
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
	final Country object = (Country) obj;
	if (this.acronym.equals(object.getAcronym())
		&& this.name.equals(object.getName())) {
	    setId(object.getId());
	    setUsage(object.getUsage());
	    return true;
	}
	return false;
    }

    /**
     * Returns the acronym.
     * 
     * @return the acronym
     */
    public String getAcronym() {
	return acronym;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    @Override
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "country";
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
    @Override
    public boolean isInUse() {
	return usage.isInUse();
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    @Override
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

    private final String acronym;
    private int id;
    private final String name;
    private Usage usage;
}