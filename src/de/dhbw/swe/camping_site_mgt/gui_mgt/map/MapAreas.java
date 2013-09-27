package de.dhbw.swe.camping_site_mgt.gui_mgt.map;

import java.util.Vector;

/**
 * The class for the areas available on map.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class MapAreas {

    /**
     * Constructor.
     */
    public MapAreas() {
	areas = new Vector<>();
	initAreas();
    }

    /**
     * @return the maps Areas.
     */
    public Vector<Area> getAreas() {
	return areas;
    }

    /**
     * Initializes areas of the map.
     */
    private void initAreas() {
	areas.add(new Area("G", new int[] { 716, 815, 813, 808, 767, 756, 697, 619,
		632, 652, 684, 661, 567, 396, 383, 405, 397, 438, 436, 474, 461,
		489, 615, 649, 699 }, new int[] { 7, 28, 257, 308, 447, 448, 415,
		487, 507, 497, 535, 546, 569, 569, 535, 532, 509, 502, 493, 487,
		459, 439, 325, 277, 104 }));
	areas.add(new Area("H", new int[] { 824, 1026, 1009, 771, 764, 806, 819 },
		new int[] { 266, 297, 537, 510, 485, 364, 327 }));
	areas.add(new Area("F", new int[] { 555, 886, 841, 795, 756, 677, 564, 544,
		536, 538 }, new int[] { 718, 677, 583, 531, 515, 554, 587, 605,
		633, 658 }));
	areas.add(new Area("I", new int[] { 1041, 1129, 1145, 1154, 1160, 1175,
		1179, 1178, 1018, 1030, 1073, 1072, 1035 }, new int[] { 191, 202,
		482, 485, 510, 511, 533, 552, 544, 363, 361, 292, 291 }));
    }

    /** The area list. */
    private final Vector<Area> areas;
}
