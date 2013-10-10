package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country extends BaseDataObject {

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
	super(id);
	this.acronym = acronym;
	this.name = name;
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Country object = (Country) dataObject;
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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "country";
    }

    private final String acronym;
    private final String name;
}
