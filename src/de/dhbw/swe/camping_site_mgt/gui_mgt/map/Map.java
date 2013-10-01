package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.dhbw.swe.camping_site_mgt.common.ResourceLoader;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;
import de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar.*;

public class Map extends JPanel {

    private class MapMouseListener extends MouseAdapter {
	@Override
	public void mouseReleased(final MouseEvent e) {
	    wasDoubleClick = false;
	    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
		wasDoubleClick = true;
	    }

	    for (final Area area : areas) {
		statusBar.cleanupStatus();

		if (area.getPoly().contains(e.getX(), e.getY())) {
		    if (selectedArea == area && !wasDoubleClick) {
			selectedArea = null;
			break;
		    }
		    selectedArea = area;
		    statusBar.setStatus(buildAreaSelectedInfo());
		    break;
		}

	    }
	    repaint();

	    if (wasDoubleClick) {
		zoomIn();
	    }
	}

	private String buildAreaSelectedInfo() {
	    final StringBuilder info = new StringBuilder();
	    info.append(lm.get(lp.AREA));
	    info.append(" " + selectedArea.getName());
	    info.append(" " + lm.get(lp.SELECTED));
	    return info.toString();
	}

	private boolean wasDoubleClick;
    }

    private class MapMouseMotionListener extends MouseMotionAdapter {
	@Override
	public void mouseMoved(final MouseEvent e) {
	    highlightedArea = null;
	    for (final Area area : areas) {
		statusBar.cleanupHoverInfo();
		setCurserDefault();

		if (area.getPoly().contains(e.getX(), e.getY())) {
		    highlightedArea = area;
		    statusBar.setHoverInfo(buildAreaHoverInfo());
		    setCurserHand();
		    break;
		}

	    }
	    repaint();
	}

	private String buildAreaHoverInfo() {
	    final StringBuilder hoverInfo = new StringBuilder();
	    hoverInfo.append(lm.get(lp.AREA));
	    hoverInfo.append(" " + highlightedArea.getName());

	    if (highlightedArea == selectedArea) {
		return hoverInfo.toString();
	    }

	    hoverInfo.append(" (" + lm.get(lp.CLICK_TO_SELECT) + " & ");
	    hoverInfo.append(lm.get(lp.ADDITIONAL_INFO) + " | ");
	    hoverInfo.append(lm.get(lp.HOW_TO_ZOOM) + ")");
	    return hoverInfo.toString();
	}
    }

    /** The opacity factor. */
    private static float ALPHA = 0.3f;

    /** The {@link LanguageMgr}. */
    private static LanguageMgr lm = LanguageMgr.getInstance();

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Map.class);

    /** The {@link LanguageProperties}. */
    private static LanguageProperties lp;

    /** The percentage of the space of screen covered by the map. */
    private static final float MAP_SCREEN_COVERAGE = 0.80f;

    /**   */
    private static final long serialVersionUID = 1L;

    public Map(final String mapImagePath) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	img = getMapImage(mapImagePath);
	final Dimension screenSize = toolkit.getScreenSize();
	Gui.setScaleFactor((screenSize.width * MAP_SCREEN_COVERAGE)
		/ img.getWidth());
	imgScaledOverview = getScaledImage(img);
	imgScaled = getScaledImage(img);

	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * Gui.getScaleFactor()),
		(int) (img.getHeight() * Gui.getScaleFactor()));
	setPreferredSize(mapSize);

	areas = new MapAreas().getAreas();

	addMouseListener(new MapMouseListener());
	addMouseMotionListener(new MapMouseMotionListener());
    }

    @Override
    public void paint(final Graphics g) {
	final Graphics2D g2 = (Graphics2D) g;
	if (!zoomedIn) {
	    g2.drawImage(imgScaledOverview, 0, 0, null);

	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		    ALPHA));
	    g2.setColor(Color.GRAY);

	    if (highlightedArea != null) {
		g2.fillPolygon(highlightedArea.getPoly());
	    }

	    g2.setColor(Color.BLUE);

	    if (selectedArea != null) {
		g2.fillPolygon(selectedArea.getPoly());
	    }
	} else {
	    g2.drawImage(imgScaled, 0, 0, null);

	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		    ALPHA));
	}
    }

    /**
     * Gets the map image.
     * 
     * @param mapImagePath
     *            the images path
     * @return the {@link BufferedImage}
     */
    private BufferedImage getMapImage(final String mapImagePath) {
	try {
	    return ImageIO.read(ResourceLoader.load(mapImagePath));
	} catch (final IOException e) {
	    logger.error("Can´t read file \"" + mapImagePath + "\"!");
	}
	return null;
    }

    private Image getScaledImage(final BufferedImage image) {
	final Dimension mapSize = new Dimension(
		(int) (image.getWidth() * Gui.getScaleFactor()),
		(int) (image.getHeight() * Gui.getScaleFactor()));

	return image.getScaledInstance(mapSize.width, mapSize.height,
		BufferedImage.SCALE_FAST);
    }

    private void setCurserDefault() {
	final Cursor cursor = Cursor.getDefaultCursor();
	setCursor(cursor);
    }

    private void setCurserHand() {
	final Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	setCursor(cursor);
    }

    private void zoomIn() {
	zoomedIn = true;
	final Rectangle frame = selectedArea.getAreaFrame();
	final float sf = Gui.getScaleFactor();
	imgScaled = img.getSubimage((int) (frame.x / sf), (int) (frame.y / sf),
		(int) (frame.width / sf), (int) (frame.height / sf));
    }

    /** The available areas. */
    private final Vector<Area> areas;

    /** The highlighted {@link Area}. */
    private Area highlightedArea = null;

    /** The {@link BufferedImage} of the map. */
    private final BufferedImage img;

    /** The scaled {@link Image}. */
    private Image imgScaled;

    /** The scaled overview {@link Image} */
    private final Image imgScaledOverview;

    /** The selected {@link Area}. */
    private Area selectedArea = null;

    /** The access interface to the status bar. */
    private final StatusBarInterface statusBar = StatusBarController.getInstance();

    private boolean zoomedIn = false;
}
