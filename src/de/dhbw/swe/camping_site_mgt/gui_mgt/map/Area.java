package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.awt.*;

import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.GuiController;

public class Area {
    /** The {@link CampingLogger}. */
    private static final CampingLogger logger = CampingLogger.getLogger(Area.class);

    public Area(final String areaName, final int[] xPoints, final int[] yPoints) {
	this.xPoints = xPoints;
	this.yPoints = yPoints;
	this.areaName = areaName;
	buildPoly();
    }

    public Rectangle getAreaFrame() {
	return buildPoly().getBounds();
    }

    public String getName() {
	return areaName;
    }

    public Polygon getPoly() {
	return buildPoly();
    }

    public int[] getScaledxPoints() {
	final int[] scaledXPoints = new int[getxPoints().length];

	for (int n = 0; n < getxPoints().length; n++) {
	    scaledXPoints[n] = (int) (getxPoints()[n] * GuiController.getScaleFactor());
	}

	return scaledXPoints;
    }

    public int[] getScaledyPoints() {
	final int[] scaledYPoints = new int[getyPoints().length];
	for (int n = 0; n < getyPoints().length; n++) {
	    scaledYPoints[n] = (int) (getyPoints()[n] * GuiController.getScaleFactor());
	}
	return scaledYPoints;
    }

    public int[] getxPoints() {
	return xPoints;
    }

    public int[] getyPoints() {
	return yPoints;
    }

    private Polygon buildPoly() {
	int[] xPoints;
	int[] yPoints;
	xPoints = getScaledxPoints();
	yPoints = getScaledyPoints();
	Polygon polygon = null;
	if (xPoints.length == yPoints.length) {
	    polygon = new Polygon(xPoints, yPoints, xPoints.length);
	} else {
	    logger.error("Not same amout of coordinates for area " + getName()
		    + ". " + xPoints.length + " X pints and " + yPoints.length
		    + " Y points");
	}
	return polygon;
    }

    private final String areaName;

    private final int[] xPoints;

    private final int[] yPoints;
}
