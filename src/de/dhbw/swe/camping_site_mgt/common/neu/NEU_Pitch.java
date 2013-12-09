package de.dhbw.swe.camping_site_mgt.common.neu;

import java.awt.Polygon;
import java.util.Arrays;

import de.dhbw.swe.camping_site_mgt.common.IntArrayParser;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.PitchInterface;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_NatureOfSoil;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_Type;

public class NEU_Pitch implements PitchInterface {

	/**
	 * @param id
	 * @param area
	 * @param type
	 * @param height
	 * @param width
	 * @param nature_of_soil
	 * @param characteristics
	 * @param x_coords
	 * @param y_coords
	 */
	public NEU_Pitch(Integer id, String area, Pitch_Type type, Integer height,
			Integer width, Pitch_NatureOfSoil nature_of_soil,
			String characteristics, String x_coords, String y_coords) {
		super();
		this.id = id;
		this.area = area;
		this.type = type;
		this.height = height;
		this.width = width;
		this.nature_of_soil = nature_of_soil;
		this.characteristics = characteristics;
		this.x_coords = x_coords;
		this.y_coords = y_coords;
		int[] xcoords = IntArrayParser.parseArray(x_coords);
		int[] ycoords = IntArrayParser.parseArray(y_coords);
		this.shape = buildShape(xcoords, ycoords);
	}

	/**
	 * Builds the {@link Pitch} shape.
	 * 
	 * @param xCoords
	 *            the x coordinates
	 * @param yCoords
	 *            the y coordinates
	 * @return the shape {@link Polygon}
	 */
	private static Polygon buildShape(final int[] xCoords, final int[] yCoords) {
		if (xCoords == null || yCoords == null) {
			return new Polygon();
		}

		final Polygon polygon = new Polygon(xCoords, yCoords, xCoords.length);
		return polygon;
	}

	@Override
	public boolean equals(Object obj) {
		NEU_Pitch pitch = (NEU_Pitch) obj;
		if (this.id == pitch.getId() && this.area.equals(pitch.getArea())
				&& this.type.equals(pitch.getType())
				&& this.height == pitch.getHeight()
				&& this.width == pitch.getWidth()
				&& this.nature_of_soil.equals(pitch.getNatureOfSoil())
				&& this.characteristics.equals(pitch.getCharacteristics())
				&& this.shape.equals(pitch.getShape())
				&& this.x_coords.equals(pitch.getX_coords())
				&& this.y_coords.equals(pitch.getY_coords())) {

			return true;
		}
		return false;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @return the type
	 */
	public Pitch_Type getType() {
		return type;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the nature_of_soil
	 */
	public Pitch_NatureOfSoil getNatureOfSoil() {
		return nature_of_soil;
	}

	/**
	 * @return the characteristics
	 */
	public String getCharacteristics() {
		return characteristics;
	}

	@Override
	public String getName() {
		final StringBuilder name = new StringBuilder();
		name.append(area);
		for (int i = 4 - (getId() + "").length(); i > 0; i--) {
			name.append("0");
		}
		name.append(getId());
		return name.toString();
	}

	@Override
	public Polygon getShape() {
		return shape;
	}

	@Override
	public Polygon getShape(int xShift, int yShift) {
		final int[] xPoints = shape.xpoints;
		final int[] yPoints = shape.ypoints;
		final int[] shiftedXPoints = new int[xPoints.length];
		final int[] shiftedYPoints = new int[yPoints.length];
		for (int i = 0; i < xPoints.length; i++) {
			shiftedXPoints[i] = xPoints[i] - xShift;
			shiftedYPoints[i] = yPoints[i] - yShift;
		}
		return new Polygon(shiftedXPoints, shiftedYPoints,
				shiftedXPoints.length);
	}

	@Override
	public String getxCoords() {
		return Arrays.toString(shape.xpoints);
	}

	@Override
	public String getyCoords() {
		return Arrays.toString(shape.ypoints);
	}

	/**
	 * @return the x_coords
	 */
	public String getX_coords() {
		return x_coords;
	}

	/**
	 * @return the y_coords
	 */
	public String getY_coords() {
		return y_coords;
	}

	@Override
	public boolean isInUse() {
		return false;
	}

	private Integer id;
	private String area;
	private Pitch_Type type;
	private Integer height;
	private Integer width;
	private Pitch_NatureOfSoil nature_of_soil;
	private String characteristics;
	private Polygon shape;
	private String x_coords;
	private String y_coords;
}
