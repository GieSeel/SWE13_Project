package de.dhbw.swe.campingplatzverwaltung.person_mgt;

import de.dhbw.swe.campingplatzverwaltung.common.ChipCard;

public class Employee extends Person {
    private boolean blocked;
    private ChipCard chipCard;
    private String password;
    private EmployeeRole role;
    private String userName;
}
