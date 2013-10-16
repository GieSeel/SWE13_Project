package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Employee} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeMgr extends BaseDataObjectMgr {
    /** The {@link ChipCardMgr}. */
    private static ChipCardMgr chipCardMgr = ChipCardMgr.getInstance();

    /** The {@link EmployeeRoleMgr}. */
    private static EmployeeRoleMgr employeeRoleMgr = EmployeeRoleMgr.getInstance();

    /** The singleton instance. */
    private static EmployeeMgr instance;

    /** The {@link PersonMgr}. */
    private static PersonMgr personMgr = PersonMgr.getInstance();

    /**
     * Returns the instance.
     * 
     * @return the singleton instance.
     */
    public static synchronized EmployeeMgr getInstance() {
	if (instance == null) {
	    instance = new EmployeeMgr();
	}
	return instance;
    }

    /**
     * Private constructor. Singleton.
     */
    private EmployeeMgr() {
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getTableName()
     */
    @Override
    public String getTableName() {
	return new Employee().getTableName();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#evenUpdateInUse()
     */
    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getLogger()
     */
    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#getSubMgr()
     */
    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(chipCardMgr);
	subMgr.add(employeeRoleMgr);
	subMgr.add(personMgr);
	return subMgr;
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr#map2DataObject(java.util.HashMap)
     */
    @Override
    protected DataObject map2DataObject(final HashMap<String, Object> map) {
	int id = 0;
	if (map.containsKey("id")) {
	    id = (int) map.get("id");
	}
	final boolean blocked = (boolean) map.get("blocked");
	final String password = (String) map.get("password");
	final String userName = (String) map.get("userName");

	final ChipCard chipCard = (ChipCard) map.get("chipCard");
	final EmployeeRole role = (EmployeeRole) map.get("role");
	final Person person = (Person) map.get("person");

	return new Employee(id, blocked, chipCard, password, person, role, userName);
    }
}
