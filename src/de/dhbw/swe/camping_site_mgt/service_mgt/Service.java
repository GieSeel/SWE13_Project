package de.dhbw.swe.camping_site_mgt.service_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.Duration;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.person_mgt.EmployeeRole;
import de.dhbw.swe.camping_site_mgt.place_mgt.Deliverypoint;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch;
import de.dhbw.swe.camping_site_mgt.place_mgt.Site;

public class Service extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Service() {
    	this(0, null, null, null, null, null, null, 0);
    }
    
    /**
     * Constructor.
     * 
     * @param pitch
     * @param employee_role
     * @param duration
     * @param description
     * @param priority
     */
    public Service(Pitch pitch,
    		EmployeeRole employee_role, Duration duration, String description,
    		int priority) {
    	this(0,pitch, null, null, employee_role, duration, description, priority);
    }
    
    /**
     * Constructor.
     * 
     * @param site
     * @param employee_role
     * @param duration
     * @param description
     * @param priority
     */
    public Service(Site site, 
    		EmployeeRole employee_role, Duration duration, String description,
    		int priority) {
    	this(0,null, site, null, employee_role, duration, description, priority);
    }

    /**
     * Constructor.
     * 
	 * @param deliverypoint
	 * @param employee_role
	 * @param duration
	 * @param description
	 * @param priority
     */
    public Service(Deliverypoint deliverypoint,
			EmployeeRole employee_role, Duration duration, String description,
			int priority) {
    	this(0,null, null, deliverypoint, employee_role, duration, description, priority);
    }

//    /**
//     * Constructor.
//     * 
//     * @param id
//     * @param creationDate
//     * @param description
//     * @param doneDate
//     * @param employee_role
//     * @param pitch
//     * @param priority
//     * @param serviceNumber
//     * @param site
//     */
//    public Service(final int id, final Date creationDate, final String description,
//	    final Date doneDate, final EmployeeRole employee_role,
//	    final Pitch pitch, final int priority, final int serviceNumber,
//	    final Site site) {
//	super(id);
////	this.creationDate = creationDate;
//	this.description = description;
////	this.doneDate = doneDate;
//	this.employee_role = employee_role;
//	this.pitch = pitch;
//	this.priority = priority;
////	this.serviceNumber = serviceNumber;
//	this.site = site;
//    }
//    
    

    /**
	 * @param id
	 * @param pitch
	 * @param site
	 * @param deliverypoint
	 * @param employee_role
	 * @param duration
	 * @param description
	 * @param priority
	 */
	public Service(int id, Pitch pitch, Site site, Deliverypoint deliverypoint,
			EmployeeRole employee_role, Duration duration, String description,
			int priority) {
		super(id);
		this.pitch = pitch;
		this.site = site;
		this.deliverypoint = deliverypoint;
		this.employee_role = employee_role;
		this.duration = duration;
		this.description = description;
		this.priority = priority;
	}

	/**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
//	final Service object = (Service) dataObject;
//	if (this.creationDate.equals(object.getCreationDate())
//		&& this.description.equals(object.getDescription())
//		&& this.doneDate.equals(object.getDoneDate())
//		&& this.employee_role.equals(object.getEmployeeRole())
//		&& this.pitch.equals(object.getPitch())
//		&& this.priority == object.getPriority()
//		&& this.serviceNumber == object.getServiceNumber()
//		&& this.site.equals(object.getSite())) {
//	    return true;
//	}
	return false;
    }

//    /**
//     * Returns the creationDate.
//     * 
//     * @return the creationDate
//     */
//    public Date getCreationDate() {
//	return creationDate;
//    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

//    /**
//     * Returns the doneDate.
//     * 
//     * @return the doneDate
//     */
//    public Date getDoneDate() {
//	return doneDate;
//    }

    
    
    /**
     * Returns the employee_role.
     * 
     * @return the employee_role
     */
    public EmployeeRole getEmployeeRole() {
	return employee_role;
    }

    /**
	 * @return the deliverypoint
	 */
	public Deliverypoint getDeliverypoint() {
		return deliverypoint;
	}

	/**
	 * @return the employee_role
	 */
	public EmployeeRole getEmployee_role() {
		return employee_role;
	}

	/**
	 * @return the duration
	 */
	public Duration getDuration() {
		return duration;
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

//    /**
//     * Returns the serviceNumber.
//     * 
//     * @return the serviceNumber
//     */
//    public int getServiceNumber() {
//	return serviceNumber;
//    }

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

    private final Pitch pitch;
    private final Site site;
    private final Deliverypoint deliverypoint;
//    private final int serviceNumber;
    private final EmployeeRole employee_role;
    private final Duration duration;
    private final String description;
    private final int priority;
}
