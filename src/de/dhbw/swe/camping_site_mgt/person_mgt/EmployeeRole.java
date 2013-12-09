package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class EmployeeRole extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public EmployeeRole() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param labeling
     */
    public EmployeeRole(final EmployeeRole_Arrangement arrangement,
	    final EmployeeRole_Labeling labeling) {
	this(0, arrangement, labeling);
    }

    /**
     * Constructor.
     * 
     * @param arrangement
     * @param id
     * @param labeling
     */
    public EmployeeRole(final int id, final EmployeeRole_Arrangement arrangement,
	    final EmployeeRole_Labeling labeling) {
	super(id);
	this.arrangement = arrangement;
	this.labeling = labeling;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final EmployeeRole object = (EmployeeRole) dataObject;
	if (this.arrangement.equals(object.getArrangement())
		&& this.labeling.equals(object.getLabeling())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the arrangement.
     * 
     * @return the arrangement
     */
    public EmployeeRole_Arrangement getArrangement() {
	return arrangement;
    }

    /**
     * Returns the labeling.
     * 
     * @return the labeling
     */
    public EmployeeRole_Labeling getLabeling() {
	return labeling;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "employee_role";
    }

    private final EmployeeRole_Arrangement arrangement;
    private final EmployeeRole_Labeling labeling;
}
