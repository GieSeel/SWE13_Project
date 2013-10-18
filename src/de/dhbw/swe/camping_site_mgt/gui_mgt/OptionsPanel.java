package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_components.CJLabel;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

public class OptionsPanel extends JPanel {

    /** The default serial version UID. */
    private static final long serialVersionUID = 1L;

    public OptionsPanel() {
	initLanguageOptions();
    }

    /**
     * Registers a {@link LanguageListener}
     * 
     * @param listener
     *            the {@link LanguageListener}
     */
    public void register(final LanguageListener listener) {
	delegate.register(listener);
    }

    /**
     * Unregisters a {@link LanguageListener}
     * 
     * @param listener
     *            the {@link LanguageListener}
     */
    public void unregister(final LanguageListener listener) {
	delegate.unregister(listener);
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

    /**
     *
     */
    private void initLanguageOptions() {
	add(getLanguageLabel());
	final ButtonGroup bg = new ButtonGroup();

	final LanguageMgr languageMgr = LanguageMgr.getInstance();
	final HashMap<String, Language> languages = languageMgr.getLanguages();
	// setLayout(new GridLayout(languages.keySet().size() + 1, 1));
	for (final Language language : languages.values()) {
	    final JRadioButton languageRb = new JRadioButton(language.getName());
	    languageRb.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(final ActionEvent e) {
		    if (!languageMgr.getSelectedLanguage().getName().equalsIgnoreCase(
			    language.getName())) {
			logger.info("Changing language from: "
				+ languageMgr.getSelectedLanguage().getName()
				+ " to: " + language.getName());
			languageMgr.setLanguage(language.getName());
			delegate.getDelegator().languageChanged();
		    }
		}
	    });
	    bg.add(languageRb);
	    add(languageRb);
	    if (language.getName().equals(
		    languageMgr.getSelectedLanguage().getName())) {
		bg.setSelected(languageRb.getModel(), true);
	    }
	}
    }

    private final Delegate<LanguageListener> delegate = new Delegate<>(
	    LanguageListener.class);

    private final CampingLogger logger = CampingLogger.getLogger(OptionsPanel.class);
}
