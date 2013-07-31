package de.dhbw.swe.campingplatzverwaltung.common;

public class Euro {
    /**
     * Constructor.
     * 
     * @param value
     *            contains the euro value and the cent value
     */
    public Euro(final float value) {

    }

    /**
     * Constructor.
     * 
     * @param euroValue
     */
    public Euro(final int euroValue) {
	this.euroValue = euroValue;
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
	this.euroValue = euroValue;
	this.centValue = centValue;
    }

    /**
     * Gives the saved value.
     * 
     * @return
     */
    public float returnValue() {
	return 0;
    }

    /**
     * Gives the saved value as {@link String}. {@inheritDoc}.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return null;
    }

    private int centValue;

    private int euroValue;

}
