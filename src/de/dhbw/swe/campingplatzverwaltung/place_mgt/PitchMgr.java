package de.dhbw.swe.campingplatzverwaltung.place_mgt;

import de.dhbw.swe.campingplatzverwaltung.place_mgt.Pitch.NatureOfSoil;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.Pitch.PitchType;

public class PitchMgr {
    /**
     * Edits the entry with the number.
     * 
     * @param pitchNumber
     *            is the number of the entry
     * @param district
     *            is a grouped area
     * @param pitchType
     *            the {@link PitchType} of the entry
     * @param length
     *            is a indication of size
     * @param width
     *            is a indication of size
     * @param natureOfSoil
     *            is the {@link NatureOfSoil} of the entry
     * @param deliveryPoint
     *            is a {@link Site} for the electrical Power
     * @param characteristics
     *            are more information about the entry
     * @return
     */
    public boolean edit(final int pitchNumber, final String district,
	    final PitchType pitchType, final int length, final int width,
	    final NatureOfSoil natureOfSoil, final Site deliveryPoint,
	    final String characteristics) {
	return false;
    }

    /**
     * Exports all entries.
     * 
     * @return
     */
    public boolean exportAll() {
	return false;
    }

    /**
     * Imports all entries.
     * 
     * @return
     */
    public boolean importAll() {
	return false;
    }

    /**
     * Loads all entries from database.
     * 
     * @return
     */
    public boolean loadAll() {
	return false;
    }

    /**
     * Saves all entries from database.
     * 
     * @return
     */
    public boolean save() {
	return false;
    }

    /**
     * Searches for the {@link Pitch} with the number as ID.
     * 
     * @param pitchNumber
     * @return
     */
    public Pitch search(final int pitchNumber) {
	return null;
    }

    /**
     * Searches for the {@link Pitch} with the given parameters.
     * 
     * @param pitchNumber
     * @param district
     * @param pitchType
     * @param length
     * @param width
     * @param natureOfSoil
     * @param deliveryPoint
     * @param characteristics
     * @return
     */
    public PitchList search(final int pitchNumber, final String district,
	    final PitchType pitchType, final int length, final int width,
	    final NatureOfSoil natureOfSoil, final Site deliveryPoint,
	    final String characteristics) {
	return null;
    }

    private PitchList pitches;

}
