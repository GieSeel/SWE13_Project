package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area;

import java.awt.*;

import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.GuiController;

public class Area implements AreaInterface {
    /** The {@link CampingLogger}. */
    private static final CampingLogger logger = CampingLogger.getLogger(Area.class);

    /**
     * Constructor.
     * 
     * @param areaName
     *            the name
     * @param xPoints
     *            the x points
     * @param yPoints
     *            the y points
     */
    public Area(final String areaName, final int[] xPoints, final int[] yPoints) {
	this.xPoints = xPoints;
	this.yPoints = yPoints;
	this.areaName = areaName;
	buildPoly();
    }

    @Override
    public Rectangle getAreaFrame() {
	return buildPoly().getBounds();
    }

    @Override
    public String getName() {
	return areaName;
    }

    @Override
    public int getPitchCount() {
	return pitchCount;
    }

    @Override
    public Polygon getPoly() {
	return buildPoly();
    }

    @Override
    public int[] getScaledxPoints() {
	final int[] scaledXPoints = new int[getxPoints().length];

	for (int n = 0; n < getxPoints().length; n++) {
	    scaledXPoints[n] = (int) (getxPoints()[n] * GuiController.getScaleFactor());
	}

	return scaledXPoints;
    }

    @Override
    public int[] getScaledyPoints() {
	final int[] scaledYPoints = new int[getyPoints().length];
	for (int n = 0; n < getyPoints().length; n++) {
	    scaledYPoints[n] = (int) (getyPoints()[n] * GuiController.getScaleFactor());
	}
	return scaledYPoints;
    }

    @Override
    public int[] getxPoints() {
	return xPoints;
    }

    @Override
    public int[] getyPoints() {
	return yPoints;
    }

    /**
     * Sets the pitch count.
     * 
     * @param pitchCount
     *            the count
     */
    public void setPitchCount(final int pitchCount) {
	this.pitchCount = pitchCount;
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
    private int pitchCount = 0;
    private final int[] xPoints;
    private final int[] yPoints;
}
