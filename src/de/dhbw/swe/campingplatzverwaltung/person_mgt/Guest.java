package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Guest {

    public Guest(final HashMap<String, Object> elements) {
	super();
	final DatabaseController db = DatabaseController.getInstance();
	this.id = (int) elements.get("id");
	this.person = db.querySelectPerson((int) elements.get("person_ID"));
	this.visitorsTaxClass = db.querySelectVisitorsTaxClass((int) elements.get("visitorsTaxClass_ID"));
    }

    public Guest(final int id, final Person person,
	    final VisitorsTaxClass visitorsTaxClass) {
	super();
	this.id = id;
	this.person = person;
	this.visitorsTaxClass = visitorsTaxClass;
    }

    public Guest(final Person person, final VisitorsTaxClass visitorsTaxClass) {
	super();
	this.id = 0;
	this.person = person;
	this.visitorsTaxClass = visitorsTaxClass;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.addAll(this.person.getData());
	data.addAll(this.visitorsTaxClass.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("person_ID", db.queryInsertUpdatePerson(this.person));
	elements.put("visitorsTaxClass_ID",
		db.queryInsertUpdateVisitorsTaxClass(this.visitorsTaxClass));
	return elements;
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
