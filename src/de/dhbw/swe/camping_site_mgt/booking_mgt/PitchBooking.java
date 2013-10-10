package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;

public class PitchBooking extends BaseDataObject {
    public PitchBooking() {
	this(false, new Pitch());
    }

    public PitchBooking(final boolean electricity, final Pitch pitch) {
	this(0, electricity, pitch);
    }

    public PitchBooking(final int id, final boolean electricity, final Pitch pitch) {
	super(id);
	this.electricity = electricity;
	this.pitch = pitch;
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
	return "pitchbooking";
    }

    /**
     * Returns the electricity.
     * 
     * @return the electricity
     */
    public boolean isElectricity() {
	return electricity;
    }

    private final boolean electricity;
    private final Pitch pitch;
}
