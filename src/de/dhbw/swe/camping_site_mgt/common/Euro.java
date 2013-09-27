package de.dhbw.swe.camping_site_mgt.common;

public class Euro {
    /**
     * Constructor.
     * 
     * @param value
     *            is a float value
     */
    public Euro(final float value) {
	super();
	final String[] values = String.valueOf(value).split("[.,]");
	this.euroValue = new Integer(values[0]);
	this.centValue = new Integer(values[1]);
    }

    /**
     * Constructor.
     * 
     * @param euroValue
     *            is just the euro value
     */
    public Euro(final int euroValue) {
	super();
	this.euroValue = euroValue;
	this.centValue = 0;
    }

    /**
     * Constructor.
     * 
     * @param euroValue
     *            is the euro value
     * @param centValue
     *            is the cent value
     */
    public Euro(final int euroValue, final int centValue) {
	super();
	this.euroValue = euroValue;
	this.centValue = centValue;
    }

    /**
     * Constructor.
     * 
     * @param value
     *            is a float value as string
     */
    public Euro(final String value) {
	super();
	final String[] values = value.split("[.,]");
	this.euroValue = new Integer(values[0]);
	this.centValue = new Integer(values[1]);
    }

    /**
     * Compares both objects.
     * 
     * @param obj
     *            is the object to compare with
     * @return
     */
    public boolean isTheSame(final Euro obj) {
	if (this.returnValue() != obj.returnValue()) {
	    return false;
	}
	return true;
    }

    /**
     * Returns the saved value.
     * 
     * @return
     */
    public float returnValue() {
	return Float.parseFloat(euroValue + "." + centValue);
    }

    /**
     * Gives the saved value as {@link String}. {@inheritDoc}.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return euroValue + "," + centValue;
    }

    private final int centValue;
    private final int euroValue;
}
