package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum EmployeeRole_Arrangement {
    ADMINISTRATOR(LanguageProperties.ARRANGEMENT_EMPLOYEEROLE_ADMINISTRATOR), LABORATORY_ASSISTANT(
	    LanguageProperties.ARRANGEMENT_EMPLOYEEROLE_LABORATORYASSISTANT), RECEPTION_STAFF(
	    LanguageProperties.ARRANGEMENT_EMPLOYEEROLE_RECEPTIONSTAFF);

    private EmployeeRole_Arrangement(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}