package de.dhbw.swe.campingplatzverwaltung.service_mgt;

import java.util.Date;

import de.dhbw.swe.campingplatzverwaltung.person_mgt.EmployeeRole;
import de.dhbw.swe.campingplatzverwaltung.place_mgt.*;

public class ServiceMgr {
    /**
     * Adds an entry to the {@link ServiceList}.
     * 
     * @param pitch
     *            is the {@link Pitch}
     * @param site
     *            is the {@link Site}
     * @param employeeRole
     *            is the {@link EmployeeRole}
     * @param description
     *            is the description of the entry
     * @param creationDate
     *            is the {@link Date} when the entry was created
     * @param priority
     *            is the priority of the entry
     * @return
     */
    public boolean create(final Pitch pitch, final Site site,
	    final EmployeeRole employeeRole, final String description,
	    final Date creationDate, final int priority) {
	return false;

    }

    /**
     * Adds an entry to the {@link ServiceList}.
     * 
     * @param service
     *            is an entry
     * @return
     */
    public boolean create(final Service service) {
	return false;
    }

    /**
     * Deletes the {@link Service} with the given ID.
     * 
     * @param serviceNumber
     *            is the ID of the entry
     * @return
     */
    public boolean delete(final int serviceNumber) {
	return false;
    }

    /**
     * Deletes the {@link Service} from the {@link ServiceList}.
     * 
     * @param service
     *            is the {@link Service} entry
     * @return
     */
    public boolean delete(final Service service) {
	return false;
    }

    /**
     * When the service was done this entry has to be set.
     * 
     * 
     * @return
     */
    public boolean done() {
	return false;
    }

    /**
     * When the service was done this entry has to be set.
     * 
     * @param date
     *            is the {@link Date} when the service was done
     * @return
     */
    public boolean done(final Date date) {
	return false;
    }

    /**
     * Edits the data of one entry
     * 
     * @param number
     *            is the ID of the entry
     * @param pitch
     *            is the {@link Pitch}
     * @param site
     *            is the {@link Site}
     * @param employeeRole
     *            is the {@link EmployeeRole}
     * @param description
     *            describes the entry
     * @param creationDate
     *            is the {@link Date} when the entry was created
     * @param priority
     *            is the priority of the entry
     * @return
     */
    public boolean edit(final int number, final Pitch pitch, final Site site,
	    final EmployeeRole employeeRole, final String description,
	    final Date creationDate, final int priority) {
	return false;
    }

    /**
     * Exports all entries.
     * 
     * @return
     */
    public boolean exportAll() {
	return false;
    }

    /**
     * Imports all entries.
     * 
     * @return
     */
    public boolean importAll() {
	return false;
    }

    /**
     * Search for an entry with the ID.
     * 
     * @param number
     *            is the ID of the entry
     * @return
     */
    public Service search(final int number) {
	return null;
    }

    /**
     * Search for an entry.
     * 
     * @param pitch
     *            is the {@link Pitch}
     * @param site
     *            is the {@link Site}
     * @param employeeRole
     *            is the {@link EmployeeRole}
     * @param description
     *            is the description of the entry
     * @param creationDate
     *            is the {@link Date} when the entry was created
     * @param priority
     *            is the priority of the entry
     * @return
     */
    public ServiceList search(final Pitch pitch, final Site site,
	    final EmployeeRole employeeRole, final String description,
	    final Date creationDate, final int priority) {
	return null;
    }

    private ServiceList serviceList;
}
