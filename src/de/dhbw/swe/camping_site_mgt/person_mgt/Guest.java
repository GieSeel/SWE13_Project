package de.dhbw.swe.camping_site_mgt.person_mgt;

public class Guest {

    /**
     * Constructor.
     * 
     */
    public Guest() {
	this(new Person(), new VisitorsTaxClass());
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param person
     * @param visitorsTaxClass
     */
    public Guest(final int id, final Person person,
	    final VisitorsTaxClass visitorsTaxClass) {
	this.id = id;
	this.person = person;
	this.visitorsTaxClass = visitorsTaxClass;
    }

    /**
     * Constructor.
     * 
     * @param person
     * @param visitorsTaxClass
     */
    public Guest(final Person person, final VisitorsTaxClass visitorsTaxClass) {
	this(0, person, visitorsTaxClass);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Guest object = (Guest) obj;
	if (this.person.equals(object.getPerson())
		&& this.visitorsTaxClass.equals(object.getVisitorsTaxClass())) {
	    setId(object.getId());
	    return true;
	}
	return false;
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
     * Returns the person.
     * 
     * @return the person
     */
    public Person getPerson() {
	return person;
    }

    /**
     * Returns the visitorsTaxClass.
     * 
     * @return the visitorsTaxClass
     */
    public VisitorsTaxClass getVisitorsTaxClass() {
	return visitorsTaxClass;
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

    private int id;
    private final Person person;
    private final VisitorsTaxClass visitorsTaxClass;
}