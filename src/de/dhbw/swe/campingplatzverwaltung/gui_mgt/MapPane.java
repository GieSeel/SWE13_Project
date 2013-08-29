package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

public class MapPane extends JScrollPane implements MouseListener {

    private class AreaCoordinate {
	public AreaCoordinate(final String areaName, final int[] xPoints,
		final int[] yPoints) {
	    this.xPoints = xPoints;
	    this.yPoints = yPoints;
	    this.areaName = areaName;
	}

	public String getAreaName() {
	    return areaName;
	}

	public int[] getScaledxPoints() {
	    final int[] scaledXPoints = new int[getxPoints().length];
	    for (int n = 0; n < getxPoints().length; n++) {
		scaledXPoints[n] = (int) (getxPoints()[n] * scaleFactor);
	    }
	    return scaledXPoints;
	}

	public int[] getScaledyPoints() {
	    final int[] scaledYPoints = new int[getyPoints().length];
	    for (int n = 0; n < getyPoints().length; n++) {
		scaledYPoints[n] = (int) (getyPoints()[n] * scaleFactor);
	    }
	    return scaledYPoints;
	}

	public int[] getxPoints() {
	    return xPoints;
	}

	public int[] getyPoints() {
	    return yPoints;
	}

	String areaName;
	int[] xPoints;

	int[] yPoints;
    }

    /** The percentage of the space of screen covered by the map. */
    private static final float MAP_SCREEN_COVERAGE = 0.69f;

    /**   */
    private static final long serialVersionUID = 1L;

    public MapPane(final String mapPicturePath) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	try {
	    img = ImageIO.read(new File(mapPicturePath));
	} catch (final IOException e) {
	    e.printStackTrace();
	}
	final Dimension screenSize = toolkit.getScreenSize();
	scaleFactor = (screenSize.width * MAP_SCREEN_COVERAGE) / img.getWidth();
	imgScaled = getScaledImage(img);
	currentMap = -1;
	addMouseListener(this);
	alpha = 0.1f;
	fill = Color.BLUE;

	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * scaleFactor),
		(int) (img.getHeight() * scaleFactor));
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

    private Vector<AreaCoordinate> getAreaCoordinates() {
	final Vector<AreaCoordinate> areaCoordinates = new Vector<>();
	areaCoordinates.add(new AreaCoordinate("G", new int[] { 716, 815, 813, 808,
		767, 756, 697, 619, 632, 652, 684, 661, 567, 396, 383, 405, 397,
		438, 436, 474, 461, 489, 615, 649, 699 }, new int[] { 7, 28, 257,
		308, 447, 448, 415, 487, 507, 497, 535, 546, 569, 569, 535, 532,
		509, 502, 493, 487, 459, 439, 325, 277, 104 }));
	areaCoordinates.add(new AreaCoordinate("H", new int[] { 824, 1026, 1009,
		771, 764, 806, 819 },
		new int[] { 266, 297, 537, 510, 485, 364, 327 }));
	areaCoordinates.add(new AreaCoordinate("F", new int[] { 555, 886, 841, 795,
		756, 677, 564, 544, 536, 538 }, new int[] { 718, 677, 583, 531,
		515, 554, 587, 605, 633, 658 }));
	areaCoordinates.add(new AreaCoordinate("I", new int[] { 1041, 1129, 1145,
		1154, 1160, 1175, 1179, 1178, 1018, 1030, 1073, 1072, 1035 },
		new int[] { 191, 202, 482, 485, 510, 511, 533, 552, 544, 363, 361,
			292, 291 }));
	return areaCoordinates;
    }

    private Image getScaledImage(final BufferedImage image) {
	final Dimension mapSize = new Dimension(
		(int) (image.getWidth() * scaleFactor),
		(int) (image.getHeight() * scaleFactor));

	return image.getScaledInstance(mapSize.width, mapSize.height,
		BufferedImage.SCALE_FAST);
    }

    private void setDefaultAreaPolygons() {
	polys = new Vector<>();
	final Vector<AreaCoordinate> areaCoordinates = getAreaCoordinates();
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

    private BufferedImage img;

    private final Image imgScaled;

    private Vector<Polygon> polys;

    private final float scaleFactor;
}
