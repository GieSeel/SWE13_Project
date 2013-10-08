package de.dhbw.swe.camping_site_mgt.place_mgt;

public class Pitch implements PitchInterface {

    /**
     * Constructor.
     * 
     */
    public Pitch() {
	this(null, new Site(), null, 0, null, null, 0, null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param characteristics
     * @param deliveryPoint
     * @param area
     * @param length
     * @param natureOfSoil
     * @param type
     * @param width
     * @param xCoords
     * @param yCoords
     */
    public Pitch(final int id, final String characteristics,
	    final Site deliveryPoint, final String area, final int length,
	    final String natureOfSoil, final String type, final int width,
	    final String xCoords, final String yCoords) {
	this.id = id;
	this.characteristics = characteristics;
	this.deliveryPoint = deliveryPoint;
	this.area = area;
	this.length = length;
	this.natureOfSoil = natureOfSoil;
	this.type = type;
	this.width = width;
	this.xCoords = xCoords;
	this.yCoords = yCoords;
    }

    /**
     * Constructor.
     * 
     * @param characteristics
     * @param deliveryPoint
     * @param area
     * @param length
     * @param natureOfSoil
     * @param type
     * @param width
     * @param xCoords
     * @param yCoords
     */
    public Pitch(final String characteristics, final Site deliveryPoint,
	    final String area, final int length, final String natureOfSoil,
	    final String type, final int width, final String xCoords,
	    final String yCoords) {
	this(width, characteristics, deliveryPoint, area, length, natureOfSoil,
		type, width, xCoords, yCoords);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Pitch object = (Pitch) obj;
	if (this.area.equals(object.getArea())
		&& this.characteristics.equals(object.getCharacteristics())
		&& this.deliveryPoint.equals(object.getDeliveryPoint())
		&& this.length == object.getLength()
		&& this.natureOfSoil.equals(object.getNatureOfSoil())
		&& this.type.equals(object.getType())
		&& this.width == object.getWidth()
		&& this.xCoords.equals(object.getxCoords())
		&& this.yCoords.equals(object.getyCoords())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the area.
     * 
     * @return the area
     */
    @Override
    public String getArea() {
	return area;
    }

    /**
     * Returns the characteristics.
     * 
     * @return the characteristics
     */
    @Override
    public String getCharacteristics() {
	return characteristics;
    }

    /**
     * Returns the deliveryPoint.
     * 
     * @return the deliveryPoint
     */
    @Override
    public Site getDeliveryPoint() {
	return deliveryPoint;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    @Override
    public int getId() {
	return id;
    }

    /**
     * Returns the length.
     * 
     * @return the length
     */
    @Override
    public int getLength() {
	return length;
    }

    /**
     * Returns the natureOfSoil.
     * 
     * @return the natureOfSoil
     */
    @Override
    public String getNatureOfSoil() {
	return natureOfSoil;
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    @Override
    public String getType() {
	return type;
    }

    /**
     * Returns the width.
     * 
     * @return the width
     */
    @Override
    public int getWidth() {
	return width;
    }

    /**
     * Returns the xCoords.
     * 
     * @return the xCoords
     */
    @Override
    public String getxCoords() {
	return xCoords;
    }

    /**
     * Returns the yCoords.
     * 
     * @return the yCoords
     */
    @Override
    public String getyCoords() {
	return yCoords;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    private final String area;
    private final String characteristics;
    private final Site deliveryPoint;
    private int id;
    private final int length;
    private final String natureOfSoil;
    private final String type;
    private final int width;
    private final String xCoords;
    private final String yCoords;
}
