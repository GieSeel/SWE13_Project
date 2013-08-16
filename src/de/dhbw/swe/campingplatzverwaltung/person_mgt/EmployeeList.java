package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;

public class EmployeeList {
    public EmployeeList(final Employee employee, final int number) {
	super();
	this.id = 0;
	this.employee = employee;
	this.number = number;
    }

    public EmployeeList(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	this.employee = DatabaseController.getInstance().querySelectEmployee(
		(int) elements.get("employee_ID"));
	this.number = (int) elements.get("number");
    }

    public EmployeeList(final int id, final Employee employee, final int number) {
	super();
	this.id = id;
	this.employee = employee;
	this.number = number;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(this.number);
	data.addAll(this.employee.getData());
	return data;
    }

    public Employee getEmployee() {
	return employee;
    }

    public HashMap<String, Object> getHashMap() {
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put(
		"employee_ID",
		DatabaseController.getInstance().queryInsertUpdateEmployee(
			this.employee));
	elements.put("number", this.number);
	return elements;
    }

    public int getId() {
	return id;
    }

    public int getNumber() {
	return number;
    }

    private final Employee employee;
    private final int id;
    private final int number;
}
