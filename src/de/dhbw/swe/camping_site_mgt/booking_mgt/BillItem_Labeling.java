package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum BillItem_Labeling {
    ADULT(LanguageProperties.LABELING_BILLITEM_ADULT), CAMPERPITCH(
	    LanguageProperties.LABELING_BILLITEM_CAMPERPITCH), CHILD(
	    LanguageProperties.LABELING_BILLITEM_CHILD), ELECTRICITY(
	    LanguageProperties.LABELING_BILLITEM_ELECTRICITY), ICEBOX(
	    LanguageProperties.LABELING_BILLITEM_ICEBOX), PARKINGPLACE(
	    LanguageProperties.LABELING_BILLITEM_PARKINGPLACE), TENTPITCH(
	    LanguageProperties.LABELING_BILLITEM_TENTPITCH), VISITORSTAXCLASSS(
	    LanguageProperties.LABELING_BILLITEM_VISITORSTAXCLASSS), VISITORSTAXCLASSS_REDUCED(
	    LanguageProperties.LABELING_BILLITEM_VISITORSTAXCLASSS_REDUCED);

    private BillItem_Labeling(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
