package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Site extends BaseDataObject {

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
     * @param opening_hours
     * @param type
     */
    public Site(final int id, final String description, final String labeling,
	    final String opening_hours, final Site_Type type) {
	super(id);
	this.description = description;
	this.labeling = labeling;
	this.opening_hours = opening_hours;
	this.type = type;
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param labeling
     * @param opening_hours
     * @param type
     */
    public Site(final String description, final String labeling,
	    final String opening_hours, final Site_Type type) {
	this(0, description, labeling, opening_hours, type);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Site object = (Site) dataObject;
	if (this.description.equals(object.getDescription())
		&& this.labeling.equals(object.getLabeling())
		&& this.opening_hours.equals(object.getOpeningHours())
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
	return opening_hours;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "site";
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public Site_Type getType() {
	return type;
    }

    private final String description;
    private final String labeling;
    private final String opening_hours;
    private final Site_Type type;
}
