package de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.language_mgt.*;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.GuiController;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.area.Area;
import de.dhbw.swe.camping_site_mgt.gui_mgt.map_mgt.map.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar.*;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

@SuppressWarnings("static-access")
public class Map extends JPanel implements AccessableMap {
    private class AreaKeyListener implements AWTEventListener {
	public AreaKeyListener() {
	    Toolkit.getDefaultToolkit().addAWTEventListener(this,
		    AWTEvent.KEY_EVENT_MASK);
	}

	@Override
	public void eventDispatched(final AWTEvent event) {
	    if (event.getID() == KeyEvent.KEY_RELEASED) {
		keyTyped((KeyEvent) event);
	    }
	}

	private void keyTyped(final KeyEvent e) {
	    if (!e.isAltDown()) {
		return;
	    }

	    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		if (selectedArea != null) {
		    zoomIn();
		}
		return;
	    }
	    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		if (zoomedIn == true) {
		    zoomOut();
		}
		return;
	    }

	    final char[] keys = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		    'W', 'X' };
	    for (final char key : keys) {
		if ((e.getKeyChar() + "").equalsIgnoreCase((key + ""))) {
		    if (areas.containsKey(key + "")) {
			selectedArea = areas.get(key + "");
			handleAreaSelected(selectedArea);
			statusBar.setStatus(buildAreaSelectedInfo());
			zoomIn();
			return;
		    }
		}
	    }
	}
    }

    private class MapMouseListener extends MouseAdapter {
	@Override
	public void mouseReleased(final MouseEvent e) {
	    wasDoubleClick = false;
	    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
		wasDoubleClick = true;
	    }
	    if (zoomedIn) {
		handleZoomedInClick(e);
	    } else {
		handleOverviewClick(e);
	    }
	}

	private String buildPitchSelectedInfo() {
	    final StringBuilder info = new StringBuilder();
	    info.append(selectedArea.getName());
	    info.append(selectedPitch.getId());
	    info.append(" " + LM.get(LP.PITCH));
	    info.append(" " + LM.get(LP.SELECTED));
	    return info.toString();
	}

	private void handleOverviewClick(final MouseEvent e) {
	    for (final Area area : areas.values()) {
		statusBar.cleanupStatus();

		if (area.getPoly().contains(e.getX(), e.getY())) {
		    if (selectedArea == area && !wasDoubleClick) {
			selectedArea = null;
		    } else {
			selectedArea = area;
			statusBar.setStatus(buildAreaSelectedInfo());
		    }
		    handleAreaSelected(selectedArea);
		    break;
		}

	    }
	    repaint();

	    if (wasDoubleClick) {
		zoomIn();
	    }
	}

	private void handleZoomedInClick(final MouseEvent e) {
	    for (final PitchInterface pitch : pitches.values()) {
		statusBar.cleanupStatus();

		if (pitch.getShape().contains(e.getX() + frame.x,
			e.getY() + frame.y)) {
		    if (selectedPitch == pitch && !wasDoubleClick) {
			selectedPitch = null;
		    } else {
			selectedPitch = pitch;
			statusBar.setStatus(buildPitchSelectedInfo());
		    }
		    handlePitchSelected(pitch);
		    break;
		}

	    }
	    repaint();

	    if (wasDoubleClick) {
		zoomOut();
	    }
	}

	private boolean wasDoubleClick;
    }

    private class MapMouseMotionListener extends MouseMotionAdapter {
	@Override
	public void mouseMoved(final MouseEvent e) {
	    if (zoomedIn) {
		handleMouseMotionZoom(e);
	    } else {
		handleMouseMotionOverview(e);
	    }
	}

	private String buildAreaHoverInfo() {
	    final StringBuilder hoverInfo = new StringBuilder();
	    hoverInfo.append(LM.get(LP.AREA));
	    hoverInfo.append(" " + highlightedArea.getName());

	    if (highlightedArea == selectedArea) {
		hoverInfo.append(" (" + LM.get(LP.CLICK_TO_DESELECT) + ")");
		return hoverInfo.toString();
	    }

	    return buildHoverInteractionHint(hoverInfo);
	}

	private String buildHoverInteractionHint(final StringBuilder hoverInfo) {
	    hoverInfo.append(" (" + LM.get(LP.CLICK_TO_SELECT) + " & ");
	    hoverInfo.append(LM.get(LP.ADDITIONAL_INFO) + " | ");
	    hoverInfo.append(LM.get(zoomedIn ? LP.HOW_TO_ZOOM_OUT
		    : LP.HOW_TO_ZOOM_IN) + ")");
	    return hoverInfo.toString();
	}

	private String buildPitchHoverInfo() {
	    final StringBuilder hoverInfo = new StringBuilder();
	    hoverInfo.append(LM.get(LP.PITCH));
	    hoverInfo.append(" " + highlightedPitch.getId());

	    if (highlightedPitch == selectedPitch) {
		hoverInfo.append(" (" + LM.get(LP.CLICK_TO_DESELECT) + ")");
		return hoverInfo.toString();
	    }

	    return buildHoverInteractionHint(hoverInfo);
	}

	private void handleMouseMotionOverview(final MouseEvent e) {
	    highlightedArea = null;

	    for (final Area area : areas.values()) {
		statusBar.cleanupHoverInfo();
		setCurserDefault();

		if (area.getPoly().contains(e.getX(), e.getY())) {
		    highlightedArea = area;
		    statusBar.setHoverInfo(buildAreaHoverInfo());
		    setCurserHand();
		    break;
		}

	    }
	    repaint();
	}

	private void handleMouseMotionZoom(final MouseEvent e) {
	    statusBar.setHoverInfo(LM.get(LP.HOW_TO_ZOOM_OUT));
	    highlightedPitch = null;

	    for (final PitchInterface pitch : pitches.values()) {
		if (!pitch.getArea().equalsIgnoreCase(selectedArea.getName())) {
		    return;
		}
		statusBar.setHoverInfo(LM.get(LP.HOW_TO_ZOOM_OUT));
		setCurserDefault();

		if (pitch.getShape().contains(e.getX() + frame.x,
			e.getY() + frame.y)) {
		    highlightedPitch = pitch;
		    statusBar.setHoverInfo(buildPitchHoverInfo());
		    break;
		}
	    }
	    repaint();
	}

    }

    /** The opacity factor. */
    private static float ALPHA = 0.3f;

    /** The {@link LanguageMgr}. */
    private static LanguageMgr LM = LanguageMgr.getInstance();

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Map.class);

    /** The {@link LanguageProperties}. */
    private static LanguageProperties LP;

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    public Map(final String mapImagePath, final HashMap<String, Area> theAreas,
	    final HashMap<Integer, PitchInterface> thePitches) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	img = getMapImage(mapImagePath);
	final Dimension screenSize = toolkit.getScreenSize();
	GuiController.setScaleFactor((screenSize.width * MapPanelInterface.MAP_SCREEN_COVERAGE)
		/ img.getWidth());

	imgScaledOverview = getScaledImage(img);
	imgScaled = getScaledImage(img);

	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * GuiController.getScaleFactor()),
		(int) (img.getHeight() * GuiController.getScaleFactor()));
	setPreferredSize(mapSize);

	areas = theAreas;
	pitches = thePitches;

	addMouseListener(new MapMouseListener());
	addMouseMotionListener(new MapMouseMotionListener());
	new AreaKeyListener();
    }

    @Override
    public Area getSelectedArea() {
	return selectedArea;
    }

    @Override
    public PitchInterface getSelectedPitch() {
	return selectedPitch;
    }

    @Override
    public void paint(final Graphics g) {
	super.paint(g);
	final Graphics2D g2 = (Graphics2D) g;
	if (zoomedIn) {
	    paintZoomedIn(g2);
	} else {
	    paintOverview(g2);
	}
    }

    /**
     * Register a {@link MapListener} at {@link Delegate}.
     * 
     * @param listener
     *            the {@link MapListener}
     */
    public void register(final MapListener listener) {
	delegate.register(listener);
    }

    @Override
    public void setSelectedArea(String areName) {
	areName = areName.toUpperCase();
	if (areas.containsKey(areName)) {
	    selectedArea = areas.get(areName);
	    delegate.getDelegator().areaSelected(selectedArea);
	    repaint();
	    return;
	}
	logger.info("No area with name '" + areName + "' available!");
    }

    @Override
    public void setSelectedPitch(final int pitchNumber) {
	if (pitches.containsKey(pitchNumber)) {
	    selectedArea = areas.get(pitches.get(pitchNumber).getArea());
	    selectedPitch = pitches.get(pitchNumber);
	    delegate.getDelegator().pitchSelected(selectedPitch);
	    zoomIn();
	    return;
	}
	logger.info("No pitch with number '" + pitchNumber + "' available in "
		+ selectedArea.getName() + "!");
    }

    /**
     * Unregister a {@link MapListener} from {@link Delegate}.
     * 
     * @param listener
     *            the {@link MapListener}
     */
    public void unregister(final MapListener listener) {
	delegate.unregister(listener);
    }

    private String buildAreaSelectedInfo() {
	final StringBuilder info = new StringBuilder();
	info.append(LM.get(LP.AREA));
	info.append(" " + selectedArea.getName());
	info.append(" " + LM.get(LP.SELECTED));
	return info.toString();
    }

    /**
     * Gets the map image.
     * 
     * @param mapImagePath
     *            the images path
     * @return the {@link BufferedImage}
     */
    private BufferedImage getMapImage(final String mapImagePath) {
	try {
	    return ImageIO.read(ResourceLoader.load(mapImagePath));
	} catch (final IOException e) {
	    logger.error("Can�t read file \"" + mapImagePath + "\"!");
	}
	return null;
    }

    private Image getScaledImage(final BufferedImage image) {
	final Dimension mapSize = new Dimension(
		(int) (image.getWidth() * GuiController.getScaleFactor()),
		(int) (image.getHeight() * GuiController.getScaleFactor()));

	return image.getScaledInstance(mapSize.width, mapSize.height,
		BufferedImage.SCALE_FAST);
    }

    /**
     * Get frame for zooming into an area.
     * 
     * @param area
     *            the {@link Rectangle} fitting a area
     * @return the {@link Rectangle} centering the area ... as far as possible.
     */
    private Rectangle getSubFrame(final Rectangle area) {
	final Dimension mapSize = new Dimension(
		(int) (img.getWidth() * GuiController.getScaleFactor()),
		(int) (img.getHeight() * GuiController.getScaleFactor()));
	final Point center = new Point(area.x + area.width / 2, area.y
		+ area.height / 2);
	final Point base = new Point(center.x - mapSize.width / 2, center.y
		- mapSize.height / 2);
	final int xOverlap = base.x + mapSize.width - img.getWidth();

	if (xOverlap > 0) {
	    base.x = base.x - xOverlap;
	}
	final int yOverlap = base.y + mapSize.height - img.getHeight();
	if (yOverlap > 0) {
	    base.y = base.y - yOverlap;
	}

	if (base.x < 0) {
	    base.x = 0;
	}
	if (base.y < 0) {
	    base.y = 0;
	}

	new Rectangle(base, mapSize);
	return new Rectangle(base, mapSize);
    }

    /**
     * Handles {@link Area} selected.
     * 
     * @param area
     *            the area
     */
    private void handleAreaSelected(final Area area) {
	delegate.getDelegator().areaSelected(area);
    }

    /**
     * Handles {@link Pitch} selected.
     * 
     * @param pitch
     *            the {@link PitchInterface}
     */
    private void handlePitchSelected(final PitchInterface pitch) {
	delegate.getDelegator().pitchSelected(pitch);
    }

    private void paintDisablePart(final Graphics2D g2) {
	Polygon leftPoly = new Polygon();

	Polygon rightPoly = new Polygon();

	final int[] xPoints = selectedArea.getxPoints();
	final int[] yPoints = selectedArea.getyPoints();

	final int[] values = Arrays.copyOf(xPoints, xPoints.length);
	Arrays.sort(values);
	final int turningPoint = values[values.length / 2];

	final Vector<Point> selectedAreaPoints = new Vector<>();
	for (int i = 0; i < xPoints.length; i++) {
	    selectedAreaPoints.add(new Point(xPoints[i], yPoints[i]));
	}

	int lastXLeft = 0, lastXRight = 0;
	boolean addedLeftBaseRectangle = false;
	boolean addedRightBaseRectangle = false;
	for (final Point point : selectedAreaPoints) {
	    if (point.x > turningPoint) {
		if (lastXLeft != 0 && !addedRightBaseRectangle) {
		    rightPoly.addPoint(turningPoint, frame.y + frame.height);
		    rightPoly.addPoint(frame.x + frame.width, frame.y
			    + frame.height);
		    rightPoly.addPoint(frame.x + frame.width, frame.y);
		    rightPoly.addPoint(turningPoint, frame.y);
		    leftPoly.addPoint(point.x, point.y);
		    addedRightBaseRectangle = true;
		}
		rightPoly.addPoint(point.x, point.y);
		lastXRight = point.x;
	    } else {
		if (lastXRight != 0 && !addedLeftBaseRectangle) {
		    rightPoly.addPoint(point.x, point.y);
		    leftPoly.addPoint(turningPoint, frame.y);
		    leftPoly.addPoint(frame.x, frame.y);
		    leftPoly.addPoint(frame.x, frame.y + frame.height);
		    leftPoly.addPoint(turningPoint, frame.y + frame.height);
		    addedLeftBaseRectangle = true;
		}
		leftPoly.addPoint(point.x, point.y);
		lastXLeft = point.x;
	    }
	}

	Vector<Point> movedToZero = new Vector<>();
	for (int i = 0; i < leftPoly.npoints; i++) {
	    movedToZero.add(new Point(leftPoly.xpoints[i] - frame.x,
		    leftPoly.ypoints[i] - frame.y));
	}
	leftPoly = new Polygon();
	for (final Point point : movedToZero) {
	    leftPoly.addPoint(point.x, point.y);
	}

	movedToZero = new Vector<>();
	for (int i = 0; i < rightPoly.npoints; i++) {
	    final int x = rightPoly.xpoints[i] - frame.x;
	    final int y = rightPoly.ypoints[i] - frame.y;
	    movedToZero.add(new Point(x, y));
	}
	rightPoly = new Polygon();
	for (final Point point : movedToZero) {
	    rightPoly.addPoint(point.x, point.y);
	}

	g2.setColor(Color.GRAY);
	g2.fill(leftPoly);
	g2.fill(rightPoly);

    }

    private void paintOverview(final Graphics2D g2) {
	g2.drawImage(imgScaledOverview, 0, 0, null);

	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));
	g2.setColor(Color.GRAY);

	if (highlightedArea != null) {
	    g2.fillPolygon(highlightedArea.getPoly());
	}

	g2.setColor(Color.BLACK);

	if (selectedArea != null) {
	    g2.fillPolygon(selectedArea.getPoly());
	}
    }

    private void paintZoomedIn(final Graphics2D g2) {
	g2.drawImage(imgScaled, 0, 0, null);

	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, ALPHA));

	paintDisablePart(g2);

	if (highlightedPitch != null) {
	    if (highlightedPitch.isInUse()) {
		g2.setColor(Color.RED);
	    }
	    g2.setColor(Color.GREEN);

	    g2.fillPolygon(highlightedPitch.getShape(frame.x, frame.y));
	}

	if (selectedPitch != null) {
	    g2.setColor(Color.DARK_GRAY);

	    g2.fillPolygon(selectedPitch.getShape(frame.x, frame.y));
	}
    }

    private void setCurserDefault() {
	final Cursor cursor = Cursor.getDefaultCursor();
	setCursor(cursor);
    }

    private void setCurserHand() {
	final Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	setCursor(cursor);
    }

    private void zoomIn() {
	zoomedIn = true;
	statusBar.cleanupHoverInfo();
	setCurserDefault();
	final float sf = GuiController.getScaleFactor();
	final Rectangle areaFrame = selectedArea.getAreaFrame();
	final int x = (int) (areaFrame.x / sf);
	final int y = (int) (areaFrame.y / sf);
	final int width = (int) (areaFrame.width / sf);
	final int height = (int) (areaFrame.height / sf);
	frame = getSubFrame(new Rectangle(x, y, width, height));

	imgScaled = img.getSubimage(frame.x, frame.y, frame.width, frame.height);
	repaint();
    }

    private void zoomOut() {
	statusBar.cleanupStatus();
	zoomedIn = false;
	repaint();
    }

    /** The available areas. */
    private final HashMap<String, Area> areas;

    private final Delegate<MapListener> delegate = new Delegate<>(MapListener.class);

    /** The zoom frame. */
    private Rectangle frame;

    /** The highlighted {@link Area}. */
    private Area highlightedArea = null;

    /** The highlighted {@link Pitch}. */
    private PitchInterface highlightedPitch = null;

    /** The {@link BufferedImage} of the map. */
    private final BufferedImage img;

    /** The scaled {@link Image}. */
    private Image imgScaled;

    /** The scaled overview {@link Image} */
    private final Image imgScaledOverview;

    /** The available {@link PitchInterface}. */
    private final HashMap<Integer, PitchInterface> pitches;

    /** The selected {@link Area}. */
    private Area selectedArea = null;

    /** The selected {@link Pitch}. */
    private PitchInterface selectedPitch;

    /** The access interface to the status bar. */
    private final StatusBarInterface statusBar = StatusBarController.getInstance();

    private boolean zoomedIn = false;
}
