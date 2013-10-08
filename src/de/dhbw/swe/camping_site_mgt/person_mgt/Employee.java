package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Set;

import de.dhbw.swe.camping_site_mgt.common.ChipCard;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;

public class Employee {
    public Employee() {
	super();
	this.id = 0;
	this.person = null;
	this.blocked = true;
	this.chipCard = null;
	this.password = null;
	this.role = null;
	this.userName = null;
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

    public HashMap<String, Object> getDatabaseData() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("person_ID", db.queryInsertUpdatePerson(this.person));
	objects.put("blocked", (this.blocked ? 1 : 0));
	objects.put("chipCard_ID", db.queryInsertUpdateChipCard(this.chipCard));
	objects.put("password", this.password);
	objects.put("employeeRole_ID", db.queryInsertUpdateEmployeeRole(this.role));
	objects.put("userName", this.userName);
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "employee_";
	objects.put(className + "id", new Integer(this.id));
	objects.put("blocked", new String(this.blocked ? "yes" : "no")); // TODO
									 // SPRACHE!!
	objects.put("password", new String(this.password));
	objects.put("userName", new String(this.userName));

	// objects.putAll(this.person.getTableData(className));
	objects.putAll(this.role.getTableData(className));
	objects.putAll(this.chipCard.getTableData(className));

	return objects;
    }

    public String getUserName() {
	return userName;
    }

    public boolean isBlocked() {
	return blocked;
    }

    public Employee setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.person = db.querySelectPerson((int) objects.get("person_ID"));
	this.role = db.querySelectEmployeeRole((int) objects.get("employeeRole_ID"));
	this.chipCard = db.querySelectChipCard((int) objects.get("chipCard_ID"));
	setData(objects);
	return this;
    }

    public Employee setTableData(final HashMap<String, Object> objects) {
	final String className = "employee_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), employeeroleMap = new HashMap<String, Object>(), personMap = new HashMap<String, Object>(), chipcardMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("employeerole_")) {
		employeeroleMap.put(key, val);
	    } else if (key.startsWith("person_")) {
		personMap.put(key, val);
	    } else if (key.startsWith("chipcard_")) {
		chipcardMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.role = new EmployeeRole().setTableData(employeeroleMap);
	// this.person = new Person().setTableData(personMap);
	this.chipCard = new ChipCard().setTableData(personMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.blocked = ((int) objects.get("blocked") == 1 ? true : false);
	this.password = (String) objects.get("password");
	this.userName = (String) objects.get("userName");

    }

    private boolean blocked;
    private ChipCard chipCard;
    private int id;
    private String password;
    private Person person;
    private EmployeeRole role;
    private String userName;
}
