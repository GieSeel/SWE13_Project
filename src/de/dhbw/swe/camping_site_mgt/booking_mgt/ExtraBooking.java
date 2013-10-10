package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class ExtraBooking extends BaseDataObject {
    public ExtraBooking() {
	this(null, null, new Site());
    }

    public ExtraBooking(final int id, final String labeling, final String name,
	    final Site site) {
	super(id);
	this.labeling = labeling;
	this.name = name;
	this.site = site;
    }

    public ExtraBooking(final String labeling, final String name, final Site site) {
	this(0, labeling, name, site);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final ExtraBooking object = (ExtraBooking) dataObject;
	if (this.labeling.equals(object.getLabeling())
		&& this.name.equals(object.getName())
		&& this.site.equals(object.getSite())) {
	    return true;
	}
	return false;
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
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the site.
     * 
     * @return the site
     */
    public Site getSite() {
	return site;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "extrabooking";
    }

    private final String labeling;
    private final String name;
    private final Site site;
}
