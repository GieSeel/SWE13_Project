package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Guest extends BaseDataObject {

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
	super(id);
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
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Guest object = (Guest) dataObject;
	if (this.person.equals(object.getPerson())
		&& this.visitorsTaxClass.equals(object.getVisitorsTaxClass())) {
	    setId(object.getId());
	    return true;
	}
	return false;
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "guest";
    }

    /**
     * Returns the visitorsTaxClass.
     * 
     * @return the visitorsTaxClass
     */
    public VisitorsTaxClass getVisitorsTaxClass() {
	return visitorsTaxClass;
    }

    private final Person person;
    private final VisitorsTaxClass visitorsTaxClass;
}