package de.dhbw.swe.campingplatzverwaltung;

import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;

/**
 * Insert description for Campingplatzverwaltung
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Campingplatzverwaltung {

    /**   */
    private final static LanguageMgr lm = LanguageMgr.getInstance();

    private static LanguageProperties lp;

    public static void main(final String[] args) {
	System.out.println(lm.get(lp.MAP));
    }

}
