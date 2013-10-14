package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum VisitorsTaxClass_Labeling {
    BUSYSEASON(LanguageProperties.LABELING_VISITORSTAXCLASSS_BUSYSEASON), BUSYSEASON_REDUCED(
	    LanguageProperties.LABELING_VISITORSTAXCLASSS_BUSYSEASONREDUCED), LOWSEASON(
	    LanguageProperties.LABELING_VISITORSTAXCLASSS_LOWSEASON), LOWSEASON_REDUCED(
	    LanguageProperties.LABELING_VISITORSTAXCLASSS_LOWSEASONREDUCED);

    private VisitorsTaxClass_Labeling(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
