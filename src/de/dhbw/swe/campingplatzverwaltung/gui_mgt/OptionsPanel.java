package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.util.HashMap;

import javax.swing.*;

import de.dhbw.swe.campingplatzverwaltung.common.language_components.CJLabel;
import de.dhbw.swe.campingplatzverwaltung.common.language_mgt.*;

public class OptionsPanel extends JPanel {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public OptionsPanel() {
	add(getLanguageLabel());
	final ButtonGroup bg = new ButtonGroup();

	final LanguageMgr languageMgr = LanguageMgr.getInstance();
	final HashMap<String, Language> languages = languageMgr.getLanguages();
	// setLayout(new GridLayout(languages.keySet().size() + 1, 1));
	for (final Language language : languages.values()) {
	    final JRadioButton languageRb = new JRadioButton(language.getName());
	    bg.add(languageRb);
	    add(languageRb);
	    if (language.getName().equals(
		    languageMgr.getSelectedLanguage().getName())) {
		bg.setSelected(languageRb.getModel(), true);
	    }
	}
    }

    /**
     * @return the {@link CJLabel} for the languages.
     */
    private CJLabel getLanguageLabel() {
	return new CJLabel(LanguageMgr.getInstance().get(
		LanguageProperties.GUI_LANGUAGE)
		+ ":") {

	    /** The default serial version UID. */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void languageChanged() {
		this.setText(LanguageMgr.getInstance().get(
			LanguageProperties.GUI_LANGUAGE)
			+ ":");
	    }
	};
    }
}
