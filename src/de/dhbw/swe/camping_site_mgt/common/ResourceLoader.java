package de.dhbw.swe.camping_site_mgt.common;

import java.io.InputStream;

/**
 * Class to access project resources.
 * 
 * @author GieSeel
 * @version 1.0
 */
final public class ResourceLoader {
    /**
     * Loads a resource from the resource folder.
     * 
     * @param path
     *            the path of the resource
     * @return the resource as {@link InputStream}
     */
    public static InputStream load(final String path) {
	InputStream input = ResourceLoader.class.getResourceAsStream(path);

	if (input == null) {
	    input = ResourceLoader.class.getResourceAsStream("/" + path);
	}

	return input;
    }
}
