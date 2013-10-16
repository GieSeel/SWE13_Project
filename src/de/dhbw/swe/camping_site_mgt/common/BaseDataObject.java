/**
 * Comments for file BaseDataObject.java
 *
 * @author   GieSeel
 *
 * Project:    Campingplatz Verwaltung
 * Company:    GieSeel
 * $Revision:  $
 *
 * Unclassified
 *
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

/**
 * Insert description for BaseDataObject
 *
 * @author GieSeel
 * @version 1.0
 */
/**
 * Insert description for BaseDataObject
 * 
 * @author GieSeel
 * @version 1.0
 */
public abstract class BaseDataObject implements DataObject {

    /**
     * Constructor.
     * 
     * @param id
     */
    public BaseDataObject(final int id) {
	this.id = id;
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
     * Compares two object without id.
     * 
     * @param dataObject
     *            the {@link DataObject};
     * @return true if the objects are the same
     */
    abstract public boolean equals(DataObject dataObject);

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object object) {
	return equals((DataObject) object);
    }

    /**
     * @return the id
     */
    @Override
    public int getId() {
	return id;
    }

    /**
     * @return the table name of the {@link Town} object.
     */
    @Override
    abstract public String getTableName();

    /**
     * @return the {@link Usage}.
     */
    public Usage getUsage() {
	return usage;
    }

    /**
     * @return true if object is still in use
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
     * Sets the {@link Usage}.
     * 
     * @param usage
     *            the {@link Usage} to set
     */
    public void setUsage(final Usage usage) {
	this.usage = usage;
    }

    private int id;
    private Usage usage;
}
