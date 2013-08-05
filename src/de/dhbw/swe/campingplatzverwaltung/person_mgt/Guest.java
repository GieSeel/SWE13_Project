package de.dhbw.swe.campingplatzverwaltung.person_mgt;

public class Guest {

    public Guest(final int id, final Person person,
	    final VisitorsTaxClass visitorsTaxClass) {
	this.id = id;
	this.person = person;
	this.visitorsTaxClass = visitorsTaxClass;
    }

    public int getId() {
	return id;
    }

    public Person getPerson() {
	return person;
    }

    public VisitorsTaxClass getVisitorsTaxClass() {
	return visitorsTaxClass;
    }

    private final int id;
    private final Person person;
    private final VisitorsTaxClass visitorsTaxClass;
}
