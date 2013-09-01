package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class EmployeeList {
    public EmployeeList() {
	super();
	this.id = 0;
	this.employee = null;
	this.number = 0;
    }

    public EmployeeList(final Employee employee, final int number) {
	super();
	this.id = 0;
	this.employee = employee;
	this.number = number;
    }

    public EmployeeList(final int id, final Employee employee, final int number) {
	super();
	this.id = id;
	this.employee = employee;
	this.number = number;
    }

    public HashMap<String, Object> getDatabaseData() {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put(
		"employee_ID",
		DatabaseController.getInstance().queryInsertUpdateEmployee(
			this.employee));
	objects.put("number", this.number);
	return objects;
    }

    public Employee getEmployee() {
	return employee;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "employeelist_";
	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "number", new Integer(this.number));
	objects.putAll(this.employee.getTableData(className));
	return objects;
    }

    public EmployeeList setDatabaseData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.employee = DatabaseController.getInstance().querySelectEmployee(
		(int) objects.get("employee_ID"));
	setData(objects);
	return this;
    }

    public EmployeeList setTableData(final HashMap<String, Object> objects) {
	final String className = "employeelist_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), employeeMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("employee_")) {
		employeeMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.employee = new Employee().setTableData(employeeMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.number = (int) objects.get("number");
    }

    private Employee employee;
    private int id;
    private int number;
}
