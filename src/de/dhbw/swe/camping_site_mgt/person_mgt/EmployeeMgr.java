package de.dhbw.swe.camping_site_mgt.person_mgt;

import java.util.HashMap;
import java.util.Vector;

import de.dhbw.swe.camping_site_mgt.common.*;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.AccessableDatabase;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataObject;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;

/**
 * The manager class for the {@link Employee} objects.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EmployeeMgr extends BaseDataObjectMgr {
    /**
     * Constructor.
     * 
     * @param db
     *            the {@link AccessableDatabase}
     * @param theChipCardMgr
     *            the {@link ChipCardMgr}
     * @param theEmployeeRoleMgr
     *            the {@link EmployeeRoleMgr}
     * @param thePersonMgr
     *            the {@link PersonMgr}
     */
    public EmployeeMgr(final AccessableDatabase db,
	    final ChipCardMgr theChipCardMgr,
	    final EmployeeRoleMgr theEmployeeRoleMgr, final PersonMgr thePersonMgr) {
	super(db);
	chipCardMgr = theChipCardMgr;
	employeeRoleMgr = theEmployeeRoleMgr;
	personMgr = thePersonMgr;
	load();
    }

    @Override
    public String getTableName() {
	return new Employee().getTableName();
    }

    @Override
    protected boolean evenUpdateInUse() {
	return false;
    }

    @Override
    protected CampingLogger getLogger() {
	return CampingLogger.getLogger(getClass());
    }

    @Override
    protected Vector<BaseDataObjectMgr> getSubMgr() {
	final Vector<BaseDataObjectMgr> subMgr = new Vector<>();
	subMgr.add(chipCardMgr);
	subMgr.add(employeeRoleMgr);
	subMgr.add(personMgr);
	return subMgr;
    }

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

    /** The {@link ChipCardMgr}. */
    private final ChipCardMgr chipCardMgr;

    /** The {@link EmployeeRoleMgr}. */
    private final EmployeeRoleMgr employeeRoleMgr;

    /** The {@link PersonMgr}. */
    private final PersonMgr personMgr;
}
