package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.Usage;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class Service {

    /**
     * Constructor.
     * 
     */
    public Service() {
	this(null, null, null, new EmployeeRole(), new Pitch(), 0, 0, new Site());
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param creationDate
     * @param description
     * @param doneDate
     * @param employeeRole
     * @param pitch
     * @param priority
     * @param serviceNumber
     * @param site
     */
    public Service(final Date creationDate, final String description,
	    final Date doneDate, final EmployeeRole employeeRole,
	    final Pitch pitch, final int priority, final int serviceNumber,
	    final Site site) {
	this(0, creationDate, description, doneDate, employeeRole, pitch, priority,
		serviceNumber, site);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param creationDate
     * @param description
     * @param doneDate
     * @param employeeRole
     * @param pitch
     * @param priority
     * @param serviceNumber
     * @param site
     */
    public Service(final int id, final Date creationDate, final String description,
	    final Date doneDate, final EmployeeRole employeeRole,
	    final Pitch pitch, final int priority, final int serviceNumber,
	    final Site site) {
	this.id = id;
	this.creationDate = creationDate;
	this.description = description;
	this.doneDate = doneDate;
	this.employeeRole = employeeRole;
	this.pitch = pitch;
	this.priority = priority;
	this.serviceNumber = serviceNumber;
	this.site = site;
	this.usage = new Usage();
    }

    /**
     * Adds entry to usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void addUsage(final String parentTableName, final int parentID) {
	usage.addUsage(parentTableName, parentID);
    }

    /**
     * Deletes entry from usage list.
     * 
     * @param parentTableName
     *            the parents table name
     * @param parentID
     *            the id of the parent
     */
    public void delUsage(final String parentTableName, final int parentID) {
	usage.delUsage(parentTableName, parentID);
    }

    /**
     * Returns the creationDate.
     * 
     * @return the creationDate
     */
    public Date getCreationDate() {
	return creationDate;
    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Returns the doneDate.
     * 
     * @return the doneDate
     */
    public Date getDoneDate() {
	return doneDate;
    }

    /**
     * Returns the employeeRole.
     * 
     * @return the employeeRole
     */
    public EmployeeRole getEmployeeRole() {
	return employeeRole;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Returns the pitch.
     * 
     * @return the pitch
     */
    public Pitch getPitch() {
	return pitch;
    }

    /**
     * Returns the priority.
     * 
     * @return the priority
     */
    public int getPriority() {
	return priority;
    }

    /**
     * Returns the serviceNumber.
     * 
     * @return the serviceNumber
     */
    public int getServiceNumber() {
	return serviceNumber;
    }

    /**
     * Returns the site.
     * 
     * @return the site
     */
    public Site getSite() {
	return site;
    }

    /**
     * Returns the usage.
     * 
     * @return the usage
     */
    public Usage getUsage() {
	return usage;
    }

    /**
     * Checks if the object is still in use.
     * 
     * @return true if it's still in use
     */
    public boolean isInUse() {
	return usage.isInUse();
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    /**
     * Sets the usage.
     * 
     * @param usage
     *            the usage to set
     */
    public void setUsage(final Usage usage) {
	this.usage = usage;
    }

    private final Date creationDate;
    private final String description;
    private final Date doneDate;
    private final EmployeeRole employeeRole;
    private int id;
    private final Pitch pitch;
    private final int priority;
    private final int serviceNumber;
    private final Site site;
    private Usage usage;
}
