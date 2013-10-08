package de.dhbw.swe.camping_site_mgt.person_mgt;

public class EmployeeRole {

    /**
     * Constructor.
     * 
     */
    public EmployeeRole() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param id
     * @param labeling
     */
    public EmployeeRole(final int id, final String arrangement,
	    final String labeling) {
	this.arrangement = arrangement;
	this.id = id;
	this.labeling = labeling;
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param labeling
     */
    public EmployeeRole(final String arrangement, final String labeling) {
	this(0, arrangement, labeling);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final EmployeeRole object = (EmployeeRole) obj;
	if (this.arrangement.equals(object.getArrangement())
		&& this.labeling.equals(object.getLabeling())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the arrangement.
     * 
     * @return the arrangement
     */
    public String getArrangement() {
	return arrangement;
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
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    private final String arrangement;
    private int id;
    private final String labeling;
}
