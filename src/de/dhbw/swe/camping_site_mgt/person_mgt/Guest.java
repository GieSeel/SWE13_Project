package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Guest {

    public Guest() {
	super();
	this.id = 0;
	this.person = null;
	this.visitorsTaxClass = null;
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

    public HashMap<String, Object> getDatabaseData() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);

	elements.put("person_ID", db.queryInsertUpdatePerson(this.person));
	elements.put("visitorsTaxClass_ID",
		db.queryInsertUpdateVisitorsTaxClass(this.visitorsTaxClass));
	return elements;

	// anstelle von queryinsert -- einfach gethashmap... dann gibts eine
	// groﬂe hashmap und die kann dann ausgelsen werden..

	// for(
	//
    }

    public int getId() {
	return id;
    }

    public Person getPerson() {
	return person;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "guest_";
	objects.put(className + "id", new Integer(this.id));
	objects.putAll(this.person.getTableData(className));
	objects.putAll(this.visitorsTaxClass.getTableData(className));
	return objects;
    }

    public VisitorsTaxClass getVisitorsTaxClass() {
	return visitorsTaxClass;
    }

    public Guest setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.person = db.querySelectPerson((int) objects.get("person_ID"));
	this.visitorsTaxClass = db.querySelectVisitorsTaxClass((int) objects.get("visitorsTaxClass_ID"));
	setData(objects);
	return this;
    }

    public Guest setTableData(final HashMap<String, Object> objects) {
	final String className = "guest_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), personMap = new HashMap<String, Object>(), visitorstaxclassMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("person_")) {
		personMap.put(key, val);
	    } else if (key.startsWith("visitorstaxclass_")) {
		visitorstaxclassMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.person = new Person().setTableData(personMap);
	this.visitorsTaxClass = new VisitorsTaxClass().setTableData(visitorstaxclassMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
    }

    private int id;
    private Person person;
    private VisitorsTaxClass visitorsTaxClass;
}