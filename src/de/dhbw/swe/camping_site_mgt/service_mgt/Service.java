package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.text.*;
import java.util.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.DatabaseController;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.place_mgt.*;

public class Service {
    public Service() {
	super();
	this.id = 0;
	this.creationDate = null;
	this.description = null;
	this.doneDate = null;
	this.employeeRole = null;
	this.pitch = null;
	this.priority = 0;
	this.serviceNumber = 0;
	this.site = null;
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

    public HashMap<String, Object> getDatabaseData() {
	final DatabaseController db = DatabaseController.getInstance();
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	objects.put("id", this.id);
	objects.put("creationDate", this.creationDate);
	objects.put("description", this.description);
	objects.put("doneDate", this.doneDate);
	objects.put("employeeRole_ID",
		db.queryInsertUpdateEmployeeRole(this.employeeRole));
	objects.put("pitch_ID", db.queryInsertUpdatePitch(this.pitch));
	objects.put("priority", this.priority);
	objects.put("serviceNumber", this.serviceNumber);
	objects.put("site_ID", db.queryInsertUpdateSite(this.site));
	return objects;
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

    public HashMap<String, Object> getTableData(final String parentClass) {
	final HashMap<String, Object> objects = new HashMap<String, Object>();
	final String className = parentClass + "service_";

	objects.put(className + "id", new Integer(this.id));
	objects.put(className + "creationDate", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(creationDate)));
	objects.put(className + "doneDate", new String(new SimpleDateFormat(
		"dd.MM.yyyy").format(doneDate)));
	objects.put(className + "priority", new Integer(this.priority));
	objects.put(className + "serviceNumber", new Integer(this.serviceNumber));

	objects.putAll(this.employeeRole.getTableData(className));
	objects.putAll(this.pitch.getTableData(className));
	objects.putAll(this.site.getTableData(className));

	return objects;
    }

    public Service setDatabaseData(final HashMap<String, Object> objects) {
	final DatabaseController db = DatabaseController.getInstance();
	this.employeeRole = db.querySelectEmployeeRole((int) objects.get("employeeRole_ID"));
	this.pitch = db.querySelectPitch((int) objects.get("pitch_ID"));
	this.site = db.querySelectSite((int) objects.get("site_ID"));
	setData(objects);
	return this;
    }

    public Service setTableData(final HashMap<String, Object> objects) {
	final String className = "service_";
	final int classNameLength = className.length();
	final HashMap<String, Object> thisMap = new HashMap<String, Object>(), employeeroleMap = new HashMap<String, Object>(), pitchMap = new HashMap<String, Object>(), siteMap = new HashMap<String, Object>();

	Object val;
	final Set<String> keys = objects.keySet();
	for (String key : keys) {
	    val = objects.get(key);
	    key = key.substring(classNameLength);
	    if (key.startsWith("employeerole_")) {
		employeeroleMap.put(key, val);
	    } else if (key.startsWith("pitch_")) {
		pitchMap.put(key, val);
	    } else if (key.startsWith("site_")) {
		siteMap.put(key, val);
	    } else {
		thisMap.put(key, val);
	    }
	}
	this.employeeRole = new EmployeeRole().setTableData(employeeroleMap);
	this.pitch = new Pitch().setTableData(pitchMap);
	this.site = new Site().setTableData(siteMap);
	setData(thisMap);
	return this;
    }

    private void setData(final HashMap<String, Object> objects) {
	this.id = (int) objects.get("id");
	this.creationDate = (Date) objects.get("creationDate");
	this.description = (String) objects.get("description");
	this.doneDate = (Date) objects.get("doneDate");
	this.priority = (int) objects.get("priority");
	this.serviceNumber = (int) objects.get("serviceNumber");
    }

    private Date creationDate;
    private String description;
    private Date doneDate;
    private EmployeeRole employeeRole;
    private int id;
    private Pitch pitch;
    private int priority;
    private int serviceNumber;
    private Site site;
}
