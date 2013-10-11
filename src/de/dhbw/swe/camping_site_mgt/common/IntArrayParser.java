package de.dhbw.swe.camping_site_mgt.common;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A parser for {@link Integer} {@link Array}s.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class IntArrayParser {
    /**
     * Parses {@link Integer} {@link Array} to String in Pattern
     * <code>['Integer', 'Integer', 'Integer']</code>
     * 
     * @param integers
     *            the {@link Integer}s
     * @return the {@link Array} as {@link String}
     */
    public static String parseArray(final int[] integers) {
	return Arrays.toString(integers);
    }

    /**
     * Parses {@link String} in pattern:
     * <code>['Integer', 'Integer', 'Integer']</code>
     * 
     * @param str
     *            the {@link String} to be parsed
     * @return the parsed Integer {@link Array}
     */
    public static int[] parseArray(final String str) {
	String trimedStr = str.replaceAll(" ", "");
	trimedStr = trimedStr.substring(1, trimedStr.length() - 1);

	final String[] integerStrings = trimedStr.split(",");
	final int[] integers = new int[integerStrings.length];

	for (int i = 0; i < integerStrings.length; i++) {
	    integers[i] = Integer.parseInt(integerStrings[i]);
	}

	return integers;
    }
}
