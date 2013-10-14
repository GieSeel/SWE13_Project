package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum Pitch_Type {

    CAMPERPITCH(LanguageProperties.TYPE_PITCH_CAMPER), PARKINGPLACE(
	    LanguageProperties.TYPE_PITCH_PARKING), TENTPITCH(
	    LanguageProperties.TYPE_PITCH_TENT);

    private Pitch_Type(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
