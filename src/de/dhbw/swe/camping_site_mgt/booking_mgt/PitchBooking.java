package de.dhbw.swe.camping_site_mgt.booking_mgt;


import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;

public class PitchBooking extends BaseDataObject {
    
	public PitchBooking() {
	this(new Pitch(), new Duration());
    }
    
    public PitchBooking(final Pitch pitch, final Duration duration) {
	this(0, pitch, duration);
    }

    public PitchBooking(final int id, final Pitch pitch, final Duration duration) {
	super(id);
	this.pitch = pitch;
	this.duration = duration;
	this.electricity = false; // TODO electricity verwenden
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final PitchBooking object = (PitchBooking) dataObject;
	if (this.electricity == object.isElectricity()
		&& this.pitch.equals(object.getPitch())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the pitch.
     * 
     * @return the pitch
     */
    public Pitch getPitch() {
	return pitch;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "booking_pitch_booking";
    }

    /**
     * Returns the electricity.
     * 
     * @return the electricity
     */
    public boolean isElectricity() {
	return electricity;
    }

    public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

    private final boolean electricity;
    private Duration duration;
	private final Pitch pitch;
}
