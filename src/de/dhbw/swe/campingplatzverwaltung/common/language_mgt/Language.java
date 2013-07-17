package de.dhbw.swe.campingplatzverwaltung.common.language_mgt;

import java.io.*;
import java.util.HashMap;

public class Language {

    /**
     * Constructor.
     * 
     * @param theLanguageFile
     */
    public Language(final File languageFile) {
	final String fileName = languageFile.getName();
	name = fileName.substring(0, fileName.lastIndexOf('.'));
	parseLanguageFile(languageFile);
    }

    public String get(final String languageProperty) {
	if (snippets.containsKey(languageProperty)) {
	    return snippets.get(languageProperty);
	}
	return languageProperty;
    }

    public String getName() {
	return name;
    }

    private void parseLanguageFile(final File languageFile) {
	try {
	    final BufferedReader csvReader = new BufferedReader(new FileReader(
		    languageFile));
	    String line = "";
	    snippets = new HashMap<>();
	    while ((line = csvReader.readLine()) != null) {
		if (!line.startsWith(LanguageProperties.JUST_A_COMMENT)) {
		    final String[] splittedLine = line.split(";");
		    if (splittedLine.length > 1) {
			snippets.put(splittedLine[0], splittedLine[1]);
		    }
		}
	    }
	    csvReader.close();
	} catch (final FileNotFoundException e) {
	    e.printStackTrace();
	} catch (final IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * The language name.
     */
    private final String name;

    /**
     * The Language snippets.
     */
    private HashMap<String, String> snippets;
}
