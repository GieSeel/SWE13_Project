package de.dhbw.swe.camping_site_mgt.service_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class Service extends BaseDataObject {

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
	super(id);
	this.creationDate = creationDate;
	this.description = description;
	this.doneDate = doneDate;
	this.employeeRole = employeeRole;
	this.pitch = pitch;
	this.priority = priority;
	this.serviceNumber = serviceNumber;
	this.site = site;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Service object = (Service) dataObject;
	if (this.creationDate.equals(object.getCreationDate())
		&& this.description.equals(object.getDescription())
		&& this.doneDate.equals(object.getDoneDate())
		&& this.employeeRole.equals(object.getEmployeeRole())
		&& this.pitch.equals(object.getPitch())
		&& this.priority == object.getPriority()
		&& this.serviceNumber == object.getServiceNumber()
		&& this.site.equals(object.getSite())) {
	    return true;
	}
	return false;
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
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "service";
    }

    private final Date creationDate;
    private final String description;
    private final Date doneDate;
    private final EmployeeRole employeeRole;
    private final Pitch pitch;
    private final int priority;
    private final int serviceNumber;
    private final Site site;
}
