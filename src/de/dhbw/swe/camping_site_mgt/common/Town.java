package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Town extends BaseDataObject {

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
	super(id);
	this.name = name;
	this.postalCode = postalCode;
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Town object = (Town) dataObject;
	if (this.name.equals(object.getName())
		&& this.postalCode.equals(object.getPostalCode())) {
	    setId(object.getId());
	    setUsage(object.getUsage());
	    return true;
	}
	return false;
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "town";
    }

    private final String name;
    private final String postalCode;
}
