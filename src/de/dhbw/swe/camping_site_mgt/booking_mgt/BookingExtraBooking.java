package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class BookingExtraBooking extends BaseDataObject {
    public BookingExtraBooking() {
    	this(new ExtraBooking(), new Duration());
    }

    public BookingExtraBooking(final int id, ExtraBooking extraBooking, Duration duration){
    	super(id);
	this.extraBooking = extraBooking;
	this.duration = duration;
    }

    public BookingExtraBooking(ExtraBooking extraBooking, Duration duration){
    	this(0, extraBooking, duration);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
//	final BookingExtraBooking object = (BookingExtraBooking) dataObject;
//	if (this.labeling.equals(object.getLabeling())
//		&& this.name.equals(object.getName())
//		&& this.site.equals(object.getSite())) {
//	    return true;
//	}
	return false;
    }


	/**
	 * @return the extraBooking
	 */
	public ExtraBooking getExtraBooking() {
		return extraBooking;
	}

	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
	}

	/**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "booking_extra_booking";
    }

    private ExtraBooking extraBooking;
    private Duration duration;
}
