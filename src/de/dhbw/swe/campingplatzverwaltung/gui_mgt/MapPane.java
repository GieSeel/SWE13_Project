package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

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

	public int[] getxPoints() {
	    return xPoints;
	}

	public int[] getyPoints() {
	    return yPoints;
	}

	public void setAreaName(final String areaName) {
	    this.areaName = areaName;
	}

	public void setxPoints(final int[] xPoints) {
	    this.xPoints = xPoints;
	}

	public void setyPoints(final int[] yPoints) {
	    this.yPoints = yPoints;
	}

	String areaName;
	int[] xPoints;
	int[] yPoints;

    }

    private static final float SCALE_FACTOR = 0.6f;

    /**   */
    private static final long serialVersionUID = 1L;

    public MapPane(final String mapPicturePath) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	img = toolkit.getImage(mapPicturePath);
	currentMap = -1;
	addMouseListener(this);
	alpha = 0.1f;
	fill = Color.BLUE;

	final Dimension screenSize = toolkit.getScreenSize();
	final Dimension mapSize = new Dimension(
		(int) (screenSize.width * SCALE_FACTOR),
		(int) (screenSize.height * SCALE_FACTOR));
	setPreferredSize(mapSize);

	img.getScaledInstance(mapSize.width, mapSize.height, Image.SCALE_SMOOTH);

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
	g2.drawImage(img, 0, 0, this);

	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		this.alpha));
	g2.setColor(this.fill);
	if (this.currentMap > -1) {
	    g2.fillPolygon(this.polys.get(this.currentMap));
	}
    }

    public void setFill(final Color color) {
	this.fill = color;
    }

    public void setFill(final Color color, final float alpha) {
	this.fill = color;
	this.alpha = alpha;
    }

    public void setPolygons(final Vector<Polygon> polygons) {
	this.polys = polygons;
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

    private Image getScaledImage(final Image image) {
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final Dimension mapSize = new Dimension(
		(int) (screenSize.width * SCALE_FACTOR),
		(int) (screenSize.height * SCALE_FACTOR));

	return image.getScaledInstance(mapSize.width, mapSize.height,
		Image.SCALE_FAST);
    }

    private void setDefaultAreaPolygons() {
	polys = new Vector<>();
	final Vector<AreaCoordinate> areaCoordinates = getAreaCoordinates();
	int[] xPoints;
	int[] yPoints;
	for (final AreaCoordinate areaCord : areaCoordinates) {
	    xPoints = areaCord.getxPoints();
	    yPoints = areaCord.getyPoints();
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

    private final Image img;

    private Vector<Polygon> polys;
}
