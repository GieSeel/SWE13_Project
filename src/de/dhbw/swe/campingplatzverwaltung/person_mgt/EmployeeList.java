package de.dhbw.swe.campingplatzverwaltung.person_mgt;

public class EmployeeList {
    public EmployeeList(final int id, final Employee employee, final int number) {
	super();
	this.id = id;
	this.employee = employee;
	this.number = number;
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

    private final Employee employee;
    private final int id;

    private final int number;
}
