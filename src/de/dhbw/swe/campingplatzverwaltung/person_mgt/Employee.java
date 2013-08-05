package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import de.dhbw.swe.campingplatzverwaltung.common.ChipCard;

public class Employee {
    public Employee(final int id, final Person person, final boolean blocked,
	    final ChipCard chipCard, final String password,
	    final EmployeeRole role, final String userName) {
	this.id = id;
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
