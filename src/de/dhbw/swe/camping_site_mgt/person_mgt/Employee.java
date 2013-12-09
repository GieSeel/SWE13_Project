package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.common.Address;
import de.dhbw.swe.camping_site_mgt.common.Chipcard;
import de.dhbw.swe.camping_site_mgt.common.Country;
import de.dhbw.swe.camping_site_mgt.common.Town;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Employee extends Person {

    /**
     * Constructor.
     * 
     */
    public Employee() {
		this(null, null, null, null, new Address(), new Town(), new Country(), new EmployeeRole(), null, null, new Chipcard(), true);
    }

    /**
     * Constructor.
     * 
     * @param blocked
     * @param chipcard
     * @param password
     * @param person
     * @param employee_role
     * @param user_name
     */
    public Employee(final String identification_number,
    	    final String name, final String first_name, final Date date_of_birth,
    	    final Address address, final Town town,
    	    final Country country,
    	    final EmployeeRole employee_role,
    	    final String user_name,
    	    final String password,
    	    final Chipcard chipcard,
    	    final boolean blocked) {
    	this(0, identification_number, name, first_name, date_of_birth, address, town, country, employee_role, user_name, password, chipcard, blocked);
    }

    /**
     * Constructor.
     * 
     *    * @param id
     *            the id
     * @param identification_number
     *            identification number
     * @param name
     *            the name
     * @param first_name
     *            the first name
     * @param date_of_birth
     *            the birth date
     * @param adress
     * 			  the {@link Address}
     * @param town
     *            the {@link Town}
     * @param country
     *            the {@link Country}
     * @param employee_role
     * @param user_name
     * @param password
     * @param chipcard
     * @param blocked
     */
    public Employee(final int id, final String identification_number,
    	    final String name, final String first_name, final Date date_of_birth,
    	    final Address address, final Town town,
    	    final Country country,
    	    final EmployeeRole employee_role,
    	    final String user_name,
    	    final String password,
    	    final Chipcard chipcard,
    	    final boolean blocked) {
    	super(id, identification_number, user_name, first_name, date_of_birth, address, town, country);
		this.blocked = blocked;
		this.chipcard = chipcard;
		this.password = password;
		this.employee_role = employee_role;
		this.user_name = user_name;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
//	final Employee object = (Employee) dataObject;
//	if (this.blocked == object.isBlocked()
//		&& this.chipcard.equals(object.getChipcard())
//		&& this.password.equals(object.getPassword())
//		&& this.person.equals(object.getPerson())
//		&& this.employee_role.equals(object.getRole())
//		&& this.user_name.equals(object.getUserName())) {
//	    setId(object.getId());
//	    return true;
//	}
	return false;
    }

    /**
     * Returns the chipcard.
     * 
     * @return the chipcard
     */
    public Chipcard getChipcard() {
	return chipcard;
    }

    /**
     * Returns the password.
     * 
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Returns the role.
     * 
     * @return the role
     */
    public EmployeeRole getRole() {
	return employee_role;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#getTableName()
     */
    @Override
    public String getTableName() {
	return "employee";
    }

    /**
     * Returns the user_name.
     * 
     * @return the user_name
     */
    public String getUserName() {
	return user_name;
    }

    /**
     * Returns the blocked.
     * 
     * @return the blocked
     */
    public boolean isBlocked() {
	return blocked;
    }

    private final EmployeeRole employee_role;
    private final String user_name;
    private final String password;
    private final Chipcard chipcard;
    private final boolean blocked;
}
