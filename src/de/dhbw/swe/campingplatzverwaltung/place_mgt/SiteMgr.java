package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class SiteMgr {
    /**
     * Edits the entry with the number.
     * 
     * @param number
     *            the ID of the entry
     * @param labeling
     *            name of the entry
     * @param siteType
     *            {@link SiteType} of the site
     * @param openingHours
     *            time when the {@link Site} is open
     * @param description
     *            of the entry
     * @return
     */
    public boolean edit(final int number, final String labeling,
	    final SiteType siteType, final String openingHours,
	    final String description) {
	return false;
    }

    /**
     * Exports all {@link Site}s.
     * 
     * @return
     */
    public boolean exportAll() {
	return false;
    }

    /**
     * Import all {@link Site}s.
     * 
     * @return
     */
    public boolean importAll() {
	return false;
    }

    /**
     * Loads all {@link Site}s from Database.
     * 
     * @return
     */
    public boolean laodAll() {
	return false;
    }

    /**
     * TODO Beschreibung richtig? Saves all {@link Site}s to Database.
     * 
     * @return
     */
    public boolean save() {
	return false;
    }

    /**
     * Finds the {@link Site} with the number as ID.
     * 
     * @param number
     *            is the ID of the entry
     * @return
     */
    public Site search(final int number) {
	return null;
    }

    /**
     * Find a {@link SiteList} of {@link Site}s with the given parameters.
     * 
     * @param labeling
     * @return
     */
    public SiteList search(final String labeling, final SiteType siteType,
	    final String openingHours, final String description) {
	return null;
    }

    private SiteList sites;

}
