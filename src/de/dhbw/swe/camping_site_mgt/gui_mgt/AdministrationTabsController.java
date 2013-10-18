package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.ResourceLoader;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * the controller class for administrative parts.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class AdministrationTabsController implements Displayable {
    /**
     * Constructor.
     */
    public AdministrationTabsController() {
	view = new JTabbedPane();
	tabs = new HashMap<>();
    }

    /**
     * Adds a new administration tab.
     * 
     * @param name
     *            the name of the tab
     * @param content
     *            the content for the tab
     * @return if adding was successful
     */
    public boolean addTab(final String name, final JComponent content) {
	return addTab(name, null, content);
    }

    /**
     * Adds a new administration tab.
     * 
     * @param name
     *            the name of the tab
     * @param the
     *            {@link Icon}
     * @param content
     *            the content for the tab
     * @return if adding was successful
     */
    public boolean addTab(final String name, final String iconPath,
	    final JComponent content) {
	if (!tabs.containsKey(name)) {
	    tabs.put(name, content);
	    view.addTab(name, getIcon(iconPath), content);
	    return true;
	}
	return false;
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    private Icon getIcon(final String iconPath) {
	if (iconPath == null) {
	    return null;
	}
	try {
	    final InputStream input = ResourceLoader.load(iconPath);
	    if (input == null) {
		return null;
	    }
	    final BufferedImage bufferedImage = ImageIO.read(input);
	    if (bufferedImage == null) {
		return null;
	    }
	    return new ImageIcon(bufferedImage);
	} catch (final IOException e) {
	    logger.error("Can´t read file \"" + iconPath + "\"!");
	}
	return null;
    }

    private final CampingLogger logger = CampingLogger.getLogger(OptionsPanel.class);

    /** The views. */
    private final HashMap<String, JComponent> tabs;

    /** The view. */
    private final JTabbedPane view;
}
