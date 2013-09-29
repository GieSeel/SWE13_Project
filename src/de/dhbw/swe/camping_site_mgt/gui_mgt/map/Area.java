package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.Polygon;

import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;

public class Area {
    /** The {@link CampingLogger}. */
    private static final CampingLogger logger = CampingLogger.getLogger(Area.class);

    public Area(final String areaName, final int[] xPoints, final int[] yPoints) {
	this.xPoints = xPoints;
	this.yPoints = yPoints;
	this.areaName = areaName;
	initPoly();
    }

    public String getName() {
	return areaName;
    }

    public Polygon getPoly() {
	return polygon;
    }

    public int[] getScaledxPoints() {
	final int[] scaledXPoints = new int[getxPoints().length];
	for (int n = 0; n < getxPoints().length; n++) {
	    scaledXPoints[n] = (int) (getxPoints()[n] * Gui.getScaleFactor());
	}
	return scaledXPoints;
    }

    public int[] getScaledyPoints() {
	final int[] scaledYPoints = new int[getyPoints().length];
	for (int n = 0; n < getyPoints().length; n++) {
	    scaledYPoints[n] = (int) (getyPoints()[n] * Gui.getScaleFactor());
	}
	return scaledYPoints;
    }

    public int[] getxPoints() {
	return xPoints;
    }

    public int[] getyPoints() {
	return yPoints;
    }

    private void initPoly() {
	int[] xPoints;
	int[] yPoints;
	xPoints = getScaledxPoints();
	yPoints = getScaledyPoints();
	if (xPoints.length == yPoints.length) {
	    polygon = new Polygon(xPoints, yPoints, xPoints.length);
	} else {
	    logger.error("Not same amout of coordinates for area " + getName()
		    + ". " + xPoints.length + " X pints and " + yPoints.length
		    + " Y points");
	}
    }

    private final String areaName;

    private Polygon polygon;

    private final int[] xPoints;

    private final int[] yPoints;
}
