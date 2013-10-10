package de.dhbw.swe.camping_site_mgt.person_mgt;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObject;
import de.dhbw.swe.camping_site_mgt.common.ChipCard;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;

public class Employee extends BaseDataObject {

    /**
     * Constructor.
     * 
     */
    public Employee() {
	this(false, new ChipCard(), null, new Person(), new EmployeeRole(), null);
    }

    /**
     * Constructor.
     * 
     * @param blocked
     * @param chipCard
     * @param password
     * @param person
     * @param role
     * @param userName
     */
    public Employee(final boolean blocked, final ChipCard chipCard,
	    final String password, final Person person, final EmployeeRole role,
	    final String userName) {
	this(0, blocked, chipCard, password, person, role, userName);
    }

    /**
     * Constructor.
     * 
     * @param blocked
     * @param chipCard
     * @param id
     * @param password
     * @param person
     * @param role
     * @param userName
     */
    public Employee(final int id, final boolean blocked, final ChipCard chipCard,
	    final String password, final Person person, final EmployeeRole role,
	    final String userName) {
	super(id);
	this.blocked = blocked;
	this.chipCard = chipCard;
	this.password = password;
	this.person = person;
	this.role = role;
	this.userName = userName;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObject#equals(de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject)
     */
    @Override
    public boolean equals(final DataObject dataObject) {
	final Employee object = (Employee) dataObject;
	if (this.blocked == object.isBlocked()
		&& this.chipCard.equals(object.getChipCard())
		&& this.password.equals(object.getPassword())
		&& this.person.equals(object.getPerson())
		&& this.role.equals(object.getRole())
		&& this.userName.equals(object.getUserName())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the chipCard.
     * 
     * @return the chipCard
     */
    public ChipCard getChipCard() {
	return chipCard;
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
     * Returns the person.
     * 
     * @return the person
     */
    public Person getPerson() {
	return person;
    }

    /**
     * Returns the role.
     * 
     * @return the role
     */
    public EmployeeRole getRole() {
	return role;
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
     * Returns the userName.
     * 
     * @return the userName
     */
    public String getUserName() {
	return userName;
    }

    /**
     * Returns the blocked.
     * 
     * @return the blocked
     */
    public boolean isBlocked() {
	return blocked;
    }

    private final boolean blocked;
    private final ChipCard chipCard;
    private final String password;
    private final Person person;
    private final EmployeeRole role;
    private final String userName;
}
