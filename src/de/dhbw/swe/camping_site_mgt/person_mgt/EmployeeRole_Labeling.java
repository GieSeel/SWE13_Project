package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum EmployeeRole_Labeling {
    CARETAKER(LanguageProperties.LABELING_EMPLOYEEROLE_CARETAKER), CLEANER(
	    LanguageProperties.LABELING_EMPLOYEEROLE_CLEANER), CONDUCTOR(
	    LanguageProperties.LABELING_EMPLOYEEROLE_CONDUCTOR), GROUNDKEEPER(
	    LanguageProperties.LABELING_EMPLOYEEROLE_GROUNDKEEPER), RECEPTION(
	    LanguageProperties.LABELING_EMPLOYEEROLE_RECEPTION);

    private EmployeeRole_Labeling(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
