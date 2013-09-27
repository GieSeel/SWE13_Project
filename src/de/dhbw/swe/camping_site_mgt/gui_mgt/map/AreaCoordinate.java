package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;

public class AreaCoordinate {
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

    String areaName;

    int[] xPoints;
    int[] yPoints;

}
