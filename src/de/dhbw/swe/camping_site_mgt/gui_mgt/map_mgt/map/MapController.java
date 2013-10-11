package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map;

import java.awt.Polygon;
import java.util.HashMap;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

public class MapController implements Displayable {
    class MapListnener {

    }

    public MapController(final String mapPath) {
	final HashMap<String, Area> areas = new MapAreas().getAreas();
	final HashMap<Integer, PitchInterface> pitches = new HashMap<>();
	final Site dev278 = new Site(278, "Electircity and Water", "Deliverypoint",
		"0-24", "Deliverypoint");
	pitches.put(1, new Pitch(1, "In the west!\nJust one direkt neighbour!",
		dev278, "A", 100, Pitch_NatureOfSoil.GRASS, Pitch_Type.CAMPERPITCH,
		100, "[7, 21, 39, 24]", "[1354, 1336, 1351, 1369]"));
	pitches.put(2, new Pitch(2, "In the west!\nJust two direkt neighbour!",
		dev278, "A", 100, Pitch_NatureOfSoil.GRASS, Pitch_Type.CAMPERPITCH,
		100, new Polygon(new int[] { 23, 36, 53, 40 }, new int[] { 1335,
			1319, 1333, 1349 }, 4)));
	view = new Map(mapPath, areas, pitches);
    }

    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    /**
     * Register a {@link MapListener} at {@link Delegate}.
     * 
     * @param listener
     *            the {@link MapListener}
     */
    public void register(final MapListener listener) {
	view.register(listener);
    }

    /**
     * Unregister a {@link MapListener} from {@link Delegate}.
     * 
     * @param listener
     *            the {@link MapListener}
     */
    public void unregister(final MapListener listener) {
	view.unregister(listener);
    }

    /** The view. */
    private final Map view;
}
