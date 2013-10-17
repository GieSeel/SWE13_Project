package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;

public enum Search_Subjects {
    COUNTRIES("Countries"), // BOOKINGS(LanguageProperties.SUBJECT_BOOKINGS),
    // GUESTS(LanguageProperties.SUBJECT_GUESTS),
    PERSONS("Persons");

    private Search_Subjects(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
