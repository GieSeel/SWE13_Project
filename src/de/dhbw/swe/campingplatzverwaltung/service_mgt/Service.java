package de.dhbw.swe.campingplatzverwaltung.service_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.EmployeeRole;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.*;

public class Service {
    public Service(final HashMap<String, Object> elements) {
	super();
	this.id = (int) elements.get("id");
	final DatabaseController db = DatabaseController.getInstance();
	this.creationDate = (Date) elements.get("creationDate");
	this.description = (String) elements.get("description");
	this.doneDate = (Date) elements.get("doneDate");
	this.employeeRole = db.querySelectEmployeeRole((int) elements.get("employeeRole_ID"));
	this.pitch = db.querySelectPitch((int) elements.get("pitch_ID"));
	this.priority = (int) elements.get("priority");
	this.serviceNumber = (int) elements.get("serviceNumber");
	this.site = db.querySelectSite((int) elements.get("site_ID"));
    }

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

    public Service(final String creationDate, final String description,
	    final String doneDate, final EmployeeRole employeeRole,
	    final Pitch pitch, final int priority, final int serviceNumber,
	    final Site site) {
	super();
	this.id = 0;
	this.creationDate = new Date(0);
	this.doneDate = new Date(0);
	try {
	    this.creationDate = new SimpleDateFormat("dd.MM.yyyy").parse(creationDate);
	    this.doneDate = new SimpleDateFormat("dd.MM.yyyy").parse(doneDate);
	} catch (final ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.description = description;
	this.employeeRole = employeeRole;
	this.pitch = pitch;
	this.priority = priority;
	this.serviceNumber = serviceNumber;
	this.site = site;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public List<Object> getData() {
	final List<Object> data = new ArrayList<Object>();
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(creationDate));
	data.add(this.description);
	data.add(new SimpleDateFormat("dd.MM.yyyy").format(doneDate));
	data.add(this.priority);
	data.add(this.serviceNumber);
	data.addAll(this.employeeRole.getData());
	data.addAll(this.pitch.getData());
	data.addAll(this.site.getData());
	return data;
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

    public HashMap<String, Object> getHashMap() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> elements = new HashMap<String, Object>();
	elements.put("id", this.id);
	elements.put("creationDate", this.creationDate);
	elements.put("description", this.description);
	elements.put("doneDate", this.doneDate);
	elements.put("employeeRole_ID",
		db.queryInsertUpdateEmployeeRole(this.employeeRole));
	elements.put("pitch_ID", db.queryInsertUpdatePitch(this.pitch));
	elements.put("priority", this.priority);
	elements.put("serviceNumber", this.serviceNumber);
	elements.put("site_ID", db.queryInsertUpdateSite(this.site));
	return elements;
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

    private Date creationDate;
    private final String description;
    private Date doneDate;
    private final EmployeeRole employeeRole;
    private final int id;
    private final Pitch pitch;
    private final int priority;
    private final int serviceNumber;
    private final Site site;
}
