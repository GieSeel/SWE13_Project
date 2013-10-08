package de.dhbw.swe.camping_site_mgt.place_mgt;

public class Site {

    /**
     * Constructor.
     * 
     */
    public Site() {
	this(null, null, null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param description
     * @param labeling
     * @param openingHours
     * @param type
     */
    public Site(final int id, final String description, final String labeling,
	    final String openingHours, final String type) {
	this.description = description;
	this.id = id;
	this.labeling = labeling;
	this.openingHours = openingHours;
	this.type = type;
    }

    /**
     * Constructor.
     * 
     * @param description
     * @param labeling
     * @param openingHours
     * @param type
     */
    public Site(final String description, final String labeling,
	    final String openingHours, final String type) {
	this(0, description, labeling, openingHours, type);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Site object = (Site) obj;
	if (this.description.equals(object.getDescription())
		&& this.labeling.equals(object.getLabeling())
		&& this.openingHours.equals(object.getOpeningHours())
		&& this.type.equals(object.getType())) {
	    return true;
	}
	return false;
    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Returns the labeling.
     * 
     * @return the labeling
     */
    public String getLabeling() {
	return labeling;
    }

    /**
     * Returns the openingHours.
     * 
     * @return the openingHours
     */
    public String getOpeningHours() {
	return openingHours;
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public String getType() {
	return type;
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

    private final String description;
    private int id;
    private final String labeling;
    private final String openingHours;
    private final String type;
}
