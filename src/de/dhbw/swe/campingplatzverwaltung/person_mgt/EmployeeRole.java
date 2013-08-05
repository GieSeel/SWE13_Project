package de.dhbw.swe.campingplatzverwaltung.person_mgt;

public class EmployeeRole {

    public EmployeeRole(final int id, final String arrangement,
	    final String labeling) {
	super();
	this.id = id;
	this.arrangement = arrangement;
	this.labeling = labeling;
    }

    // private final EmployeeRole_Arrangement arrangement;
    // private final EmployeeRole_Labeling labeling;
    public String getArrangement() {
	return arrangement;
    }

    public int getId() {
	return id;
    }

    public String getLabeling() {
	return labeling;
    }

    private final String arrangement;
    private final int id;
    private final String labeling;
}
