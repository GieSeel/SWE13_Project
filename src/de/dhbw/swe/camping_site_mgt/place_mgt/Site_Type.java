package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum Site_Type {

    ACTIVITY(LanguageProperties.TYPE_SITE_ACTIVITY), DELIVERYPOINT(
	    LanguageProperties.TYPE_SITE_DELIVERYPOINT), ELECTRICALPOWER(
	    LanguageProperties.TYPE_SITE_ELECTRICALPOWER), ENTRANCE(
	    LanguageProperties.TYPE_SITE_ENTRANCE), FIREPLACE(
	    LanguageProperties.TYPE_SITE_FIREPLACE), OTHER(
	    LanguageProperties.TYPE_SITE_OTHER), RECEPTION(
	    LanguageProperties.TYPE_SITE_RECEPTION), SALE(
	    LanguageProperties.TYPE_SITE_SALE), SANITARYFACILLITIES(
	    LanguageProperties.TYPE_SITE_SANITARYFACILLITIES), SERVICE(
	    LanguageProperties.TYPE_SITE_SERVICE), UTILLITYROOM(
	    LanguageProperties.TYPE_SITE_UTILLITYROOM);

    private Site_Type(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
