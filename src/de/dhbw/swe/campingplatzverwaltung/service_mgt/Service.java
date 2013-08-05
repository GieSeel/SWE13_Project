package de.dhbw.swe.campingplatzverwaltung.service_mgt;

import java.util.Date;

import de.dhbw.swe.campingplatzverwaltung.person_mgt.EmployeeRole;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.*;

public class Service {
    public Service(final int id, final Date creationDate, final String description,
	    final Date doneDate, final EmployeeRole employeeRole,
	    final Pitch pitch, final int priority, final int serviceNumber,
	    final Site site) {
	super();
	this.id = id;
	this.creationDate = creationDate;
	this.description = description;
	this.doneDate = doneDate;
	this.employeeRole = employeeRole;
	this.pitch = pitch;
	this.priority = priority;
	this.serviceNumber = serviceNumber;
	this.site = site;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public String getDescription() {
	return description;
    }

    public Date getDoneDate() {
	return doneDate;
    }

    public EmployeeRole getEmployeeRole() {
	return employeeRole;
    }

    public int getId() {
	return id;
    }

    public Pitch getPitch() {
	return pitch;
    }

    public int getPriority() {
	return priority;
    }

    public int getServiceNumber() {
	return serviceNumber;
    }

    public Site getSite() {
	return site;
    }

    private final Date creationDate;
    private final String description;
    private final Date doneDate;
    private final EmployeeRole employeeRole;
    private final int id;
    private final Pitch pitch;
    private final int priority;
    private final int serviceNumber;
    private final Site site;
}
