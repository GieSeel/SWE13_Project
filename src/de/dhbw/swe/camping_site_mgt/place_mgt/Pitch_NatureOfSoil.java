package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageMgr;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.LanguageProperties;

public enum Pitch_NatureOfSoil {
    GRASS(LanguageProperties.NATUREOFSOIL_PITCH_GRASS), GRAVEL(
	    LanguageProperties.NATUREOFSOIL_PITCH_GRAVEL), SAND(
	    LanguageProperties.NATUREOFSOIL_PITCH_SAND), SOIL(
	    LanguageProperties.NATUREOFSOIL_PITCH_SOIL);

    private Pitch_NatureOfSoil(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
