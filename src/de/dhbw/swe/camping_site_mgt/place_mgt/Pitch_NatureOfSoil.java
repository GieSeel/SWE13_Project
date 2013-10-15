package de.dhbw.swe.camping_site_mgt.place_mgt;

import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;

public enum Pitch_NatureOfSoil {
    GRASS(LanguageProperties.NATUREOFSOIL_PITCH_GRASS), GRAVEL(
	    LanguageProperties.NATUREOFSOIL_PITCH_GRAVEL), SAND(
	    LanguageProperties.NATUREOFSOIL_PITCH_SAND), SOIL(
	    LanguageProperties.NATUREOFSOIL_PITCH_SOIL);

    private Pitch_NatureOfSoil(final String value) {
	this.value = LanguageMgr.getInstance().get(value);
    }

    public String getDisplayName() {
	return value.substring(0, 1).toUpperCase()
		+ value.substring(1, value.length());
    }

    @Override
    public String toString() {
	return value;
    }

    private final String value;
}
