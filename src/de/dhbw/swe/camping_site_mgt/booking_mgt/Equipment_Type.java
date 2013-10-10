package de.dhbw.swe.camping_site_mgt.booking_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum Equipment_Type {
    BIKE(LanguageProperties.TYPE_EQUIPMENT_BIKE), BOAT(
	    LanguageProperties.TYPE_EQUIPMENT_BOAT), CAMPER(
	    LanguageProperties.TYPE_EQUIPMENT_CAMPER), CAR(
	    LanguageProperties.TYPE_EQUIPMENT_CAR), CARAVAN(
	    LanguageProperties.TYPE_EQUIPMENT_CARAVAN), MOTORBIKE(
	    LanguageProperties.TYPE_EQUIPMENT_MOTORBIKE), OTHER(
	    LanguageProperties.TYPE_EQUIPMENT_OTHER), TENT(
	    LanguageProperties.TYPE_EQUIPMENT_TENT);

    private Equipment_Type(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
} // CAMPER = MOTORHOME
