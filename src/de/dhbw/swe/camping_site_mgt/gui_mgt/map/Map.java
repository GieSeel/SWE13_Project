package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.dhbw.swe.camping_site_mgt.common.ResourceLoader;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;

public class Map extends JPanel implements MouseListener, MouseMotionListener {

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
	imgScaled = getScaledImage(img);
	alpha = 0.1f;

	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * Gui.getScaleFactor()),
		(int) (img.getHeight() * Gui.getScaleFactor()));
	setPreferredSize(mapSize);

	setDefaultAreaPolygons();

	addMouseListener(this);
	addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }

    @Override
    public void mouseExited(final MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
	highlightedArea = -1;
	for (int i = 0; i < areas.size(); i++) {
	    if (areas.get(i).contains(e.getX(), e.getY())) {
		// this.firePropertyChange("CurrentMap", highlightedArea, i);
		highlightedArea = i;
		break;
	    }
	}
	repaint();
    }

    @Override
    public void mousePressed(final MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
	for (int i = 0; i < areas.size(); i++) {
	    if (areas.get(i).contains(e.getX(), e.getY())) {
		// this.firePropertyChange("CurrentMap", highlightedArea, i);
		highlightedArea = i;
		selectedArea = i;
		break;
	    }
	}
	repaint();
    }

    @Override
    public void paint(final Graphics g) {
	final Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(imgScaled, 0, 0, null);

	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	g2.setColor(Color.GRAY);
	if (highlightedArea > -1) {
	    g2.fillPolygon(areas.get(highlightedArea));
	}
	g2.setColor(Color.BLUE);
	if (selectedArea > -1) {
	    g2.fillPolygon(areas.get(selectedArea));
	}
    }

    public void setPolygons(final Vector<Polygon> polygons) {
	areas = polygons;
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
	    System.err.println("Can´t read file \"" + mapImagePath + "\"!");
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

    private void setDefaultAreaPolygons() {
	areas = new Vector<>();
	final Vector<AreaCoordinate> areaCoordinates = new MapAreas().getAreaCoordinates();
	int[] xPoints;
	int[] yPoints;
	for (final AreaCoordinate areaCord : areaCoordinates) {
	    xPoints = areaCord.getScaledxPoints();
	    yPoints = areaCord.getScaledyPoints();
	    if (xPoints.length == yPoints.length) {
		final Polygon area = new Polygon(xPoints, yPoints, xPoints.length);
		areas.add(area);
	    } else {
		System.out.println("ERROR: Not same amout of coordinates for area "
			+ areaCord.getAreaName() + ". " + xPoints.length
			+ " X pints and " + yPoints.length + " Y points");
	    }
	}
    }

    private final float alpha;

    private Vector<Polygon> areas;

    /** The highlighted area. */
    private int highlightedArea = -1;

    private final BufferedImage img;

    private final Image imgScaled;

    /** The selected area. */
    private int selectedArea = -1;
}
