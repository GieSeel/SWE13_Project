package de.dhbw.swe.campingplatzverwaltung.gui_mgt.map;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

import de.dhbw.swe.campingplatzverwaltung.common.ResourceLoader;
import de.dhbw.swe.campingplatzverwaltung.gui_mgt.Gui;
import de.dhbw.swe.campingplatzverwaltung.gui_mgt.map.*;

public class MapPane extends JScrollPane implements MouseListener {

    /** The percentage of the space of screen covered by the map. */
    private static final float MAP_SCREEN_COVERAGE = 0.80f;

    /**   */
    private static final long serialVersionUID = 1L;

    public MapPane(final String mapImagePath) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	img = getMapImage(mapImagePath);
	final Dimension screenSize = toolkit.getScreenSize();
	Gui.setScaleFactor((screenSize.width * MAP_SCREEN_COVERAGE)
		/ img.getWidth());
	imgScaled = getScaledImage(img);
	currentMap = -1;
	addMouseListener(this);
	alpha = 0.1f;
	fill = Color.BLUE;

	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * Gui.getScaleFactor()),
		(int) (img.getHeight() * Gui.getScaleFactor()));
	setPreferredSize(mapSize);

	setDefaultAreaPolygons();
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }

    @Override
    public void mouseExited(final MouseEvent arg0) {
    }

    @Override
    public void mousePressed(final MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
	System.out.println(e.getX() + "  " + e.getY());
	for (int i = 0; i < polys.size(); i++) {
	    if (polys.get(i).contains(e.getX(), e.getY())) {
		this.firePropertyChange("CurrentMap", currentMap, i);
		currentMap = i;
		repaint();
		break;
	    }
	}
    }

    @Override
    public void paint(final Graphics g) {
	final Graphics2D g2 = (Graphics2D) g;
	g2.drawImage(imgScaled, 0, 0, null);

	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	g2.setColor(fill);
	if (currentMap > -1) {
	    g2.fillPolygon(polys.get(currentMap));
	}
    }

    public void setFill(final Color color) {
	fill = color;
    }

    public void setFill(final Color color, final float alpha) {
	fill = color;
	this.alpha = alpha;
    }

    public void setPolygons(final Vector<Polygon> polygons) {
	polys = polygons;
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
	polys = new Vector<>();
	final Vector<AreaCoordinate> areaCoordinates = new MapAreas().getAreaCoordinates();
	int[] xPoints;
	int[] yPoints;
	for (final AreaCoordinate areaCord : areaCoordinates) {
	    xPoints = areaCord.getScaledxPoints();
	    yPoints = areaCord.getScaledyPoints();
	    if (xPoints.length == yPoints.length) {
		final Polygon area = new Polygon(xPoints, yPoints, xPoints.length);
		polys.add(area);
	    } else {
		System.out.println("ERROR: Not same amout of coordinates for area "
			+ areaCord.getAreaName() + ". " + xPoints.length
			+ " X pints and " + yPoints.length + " Y points");
	    }
	}
    }

    private float alpha;

    private int currentMap;

    private Color fill;

    private final BufferedImage img;

    private final Image imgScaled;

    private Vector<Polygon> polys;

}
