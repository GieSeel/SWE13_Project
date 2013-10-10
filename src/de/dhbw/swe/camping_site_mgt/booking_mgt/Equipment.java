package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Equipment extends BaseDataObject {

    public Equipment() {
	this(null, null, null);
    }

    public Equipment(final int id, final String identification, final String size,
	    final Equipment_Type type) {
	super(id);
	this.identification = identification;
	this.size = size;
	this.type = type;
    }

    public Equipment(final String identification, final String size,
	    final Equipment_Type type) {
	this(0, identification, size, type);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Equipment object = (Equipment) dataObject;
	if (this.identification.equals(object.getIdentification())
		&& this.size.equals(object.getSize())
		&& this.type.equals(object.getType())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the identification.
     * 
     * @return the identification
     */
    public String getIdentification() {
	return identification;
    }

    /**
     * Returns the size.
     * 
     * @return the size
     */
    public String getSize() {
	return size;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "equipment";
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public Equipment_Type getType() {
	return type;
    }

    private final String identification;
    private final String size;
    private final Equipment_Type type;
}
