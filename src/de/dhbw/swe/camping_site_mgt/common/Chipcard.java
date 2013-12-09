package de.dhbw.swe.camping_site_mgt.common;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Chipcard extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Chipcard() {
	this(new Duration());
    }

    /**
     * Constructor.
     * 
     * @param validFrom
     * @param validTo
     */
    public Chipcard(final Duration duration) {
	this(0, duration);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param duration
     */
    public Chipcard(final int id, final Duration duration) {
	super(id);
	this.duration = duration;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
//	final Chipcard object = (Chipcard) dataObject;
//	if (this.validFrom.equals(object.getValidFrom())
//		&& this.validTo.equals(object.getValidTo())) {
//	    return true;
//	}
	return false;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "chipcard";
    }

    
    /**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	private final Duration duration;
}
