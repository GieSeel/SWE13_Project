package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.util.Vector;

public class MapAreas {
    public Vector<AreaCoordinate> getAreaCoordinates() {
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
}
