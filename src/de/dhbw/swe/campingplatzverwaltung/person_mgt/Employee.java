package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.ChipCard;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class Employee {
    public Employee(final HashMap<String, Object> elements) {
	super();
	final DatabaseController db = DatabaseController.getInstance();
	this.id = (int) elements.get("id");
	this.person = db.querySelectPerson((int) elements.get("person_ID"));
	this.blocked = ((int) elements.get("blocked") == 1 ? true : false);
	this.chipCard = db.querySelectChipCard((int) elements.get("chipCard_ID"));
	this.password = (String) elements.get("password");
	this.role = db.querySelectEmployeeRole((int) elements.get("employeeRole_ID"));
	this.userName = (String) elements.get("userName");
    }

    public Employee(final int id, final Person person, final boolean blocked,
	    final ChipCard chipCard, final String password,
	    final EmployeeRole role, final String userName) {
	super();
	this.id = id;
	this.person = person;
	this.blocked = blocked;
	this.chipCard = chipCard;
	this.password = password;
	this.role = role;
	this.userName = userName;
    }

    public Employee(final Person person, final boolean blocked,
	    final ChipCard chipCard, final String password,
	    final EmployeeRole role, final String userName) {
	super();
	this.id = 0;
	this.person = person;
	this.blocked = blocked;
	this.chipCard = chipCard;
	this.password = password;
	this.role = role;
	this.userName = userName;
    }

    public ChipCard getChipCard() {
	return chipCard;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add((this.blocked ? "yes" : "no")); // TODO SPRACHE!!
	data.add(this.password);
	data.add(this.userName);
	data.addAll(this.chipCard.getData());
	// TODO data.addAll(this.person.getData());
	data.addAll(this.role.getData());
	return data;
    }

    public HashMap<String, Object> getHashMap() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("person_ID", db.queryInsertUpdatePerson(this.person));
	elements.put("blocked", (this.blocked ? 1 : 0));
	elements.put("chipCard_ID", db.queryInsertUpdateChipCard(this.chipCard));
	elements.put("password", this.password);
	elements.put("employeeRole_ID", db.queryInsertUpdateEmployeeRole(this.role));
	elements.put("userName", this.userName);
	return elements;
    }

    public int getId() {
	return id;
    }

    public String getPassword() {
	return password;
    }

    public Person getPerson() {
	return person;
    }

    public EmployeeRole getRole() {
	return role;
    }

    public String getUserName() {
	return userName;
    }

    public boolean isBlocked() {
	return blocked;
    }

    private final boolean blocked;
    private final ChipCard chipCard;
    private final int id;
    private final String password;
    private final Person person;
    private final EmployeeRole role;
    private final String userName;
}
