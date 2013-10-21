package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map;

import java.util.HashMap;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.Area;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view.Map;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchMgr;

public class MapController implements Displayable {

    public MapController(final String mapPath,
	    final HashMap<String, Area> theAreas, final PitchMgr thePitchMgr) {
	pitchMgr = thePitchMgr;

	final HashMap<Integer, PitchInterface> pitches = new HashMap<>();
	for (final DataObject object : thePitchMgr.getAllObjects().values()) {
	    pitches.put(object.getId(), (PitchInterface) object);
	}

	calculateAreaPitchCounts(theAreas, pitches);
	view = new Map(mapPath, theAreas, pitches);
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

    private void calculateAreaPitchCounts(final HashMap<String, Area> areas,
	    final HashMap<Integer, PitchInterface> pitches) {
	for (final PitchInterface pitch : pitches.values()) {
	    final Area area = areas.get(pitch.getArea());
	    int pitchCount = area.getPitchCount();
	    pitchCount++;
	    area.setPitchCount(pitchCount);
	}
    }

    /** The {@link PitchMgr}. */
    private final PitchMgr pitchMgr;

    /** The view. */
    private final Map view;
}
