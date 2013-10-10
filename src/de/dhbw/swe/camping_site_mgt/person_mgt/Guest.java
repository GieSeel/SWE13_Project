package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.Usage;

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
	this.usage = new Usage();
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
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void addUsage(final String parentTableName, final int parentID) {
	usage.addUsage(parentTableName, parentID);
    }

    /**
     * Deletes entry from usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void delUsage(final String parentTableName, final int parentID) {
	usage.delUsage(parentTableName, parentID);
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
     * Returns the usage.
     * 
     * @return the usage
     */
    public Usage getUsage() {
	return usage;
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
     * Checks if the object is still in use.
     * 
     * @return true if it's still in use
     */
    public boolean isInUse() {
	return usage.isInUse();
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

    /**
     * Sets the usage.
     * 
     * @param usage
     *            the usage to set
     */
    public void setUsage(final Usage usage) {
	this.usage = usage;
    }

    private int id;
    private final Person person;
    private Usage usage;
    private final VisitorsTaxClass visitorsTaxClass;
}