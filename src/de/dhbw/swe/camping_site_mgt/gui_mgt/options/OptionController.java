package de.dhbw.swe.camping_site_mgt.gui_mgt.options;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.common.LanguageListener;
import de.dhbw.swe.camping_site_mgt.gui_mgt.*;

public class OptionController implements Displayable {

    public OptionController() {
	view = new OptionsPanel();
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    /**
     * Registers a {@link LanguageListener}
     * 
     * @param listener
     *            the {@link LanguageListener}
     */
    public void register(final LanguageListener listener) {
	view.register(listener);
    }

    /**
     * Unregisters a {@link LanguageListener}
     * 
     * @param listener
     *            the {@link LanguageListener}
     */
    public void unregister(final LanguageListener listener) {
	view.unregister(listener);
    }

    private final OptionsPanel view;

}
