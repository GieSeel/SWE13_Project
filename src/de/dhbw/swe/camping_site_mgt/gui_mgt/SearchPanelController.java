/**
 * Comments for file SearchPanelController.java
 *
 * @author   GieSeel
 *
 * Project:    Campingplatz Verwaltung
 * Company:    GieSeel
 * $Revision:  $
 *
 * Unclassified
 *
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataStructure;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;

/**
 * Insert description for SearchPanelController
 * 
 * @author GieSeel
 * @version 1.0
 */
public class SearchPanelController implements Displayable {
    /**
     * Constructor.
     * 
     */
    public SearchPanelController() {
	view = new SearchPanel();
	init();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable#getGuiSnippet()
     */
    @Override
    public JComponent getGuiSnippet() {
	return view;
    }

    public void init() {
	// Select columns for display
	final Vector<String> fields = new Vector<>();
	fields.add("person_identificationNumber");
	fields.add("person_name");
	fields.add("person_firstName");
	// fields.add("person_dateOfBirth");
	fields.add("person_street");
	fields.add("person_houseNumber");

	fields.add("town_postalCode");
	fields.add("town_name");

	fields.add("country_acronym");
	fields.add("country_name");

	objectManger = PersonMgr.getInstance();

	// Save relevant data
	final HashMap<Integer, ColumnInfo> columns = new HashMap<>();
	String className, lastClassName = null, fieldName;

	for (final String field : fields) {
	    className = field.split("_")[0];
	    if (!className.equals(lastClassName)) {
		lastClassName = className;
		for (final ColumnInfo dataStructur : DataStructure.getStructureFor(className)) {
		    fieldName = className + "_" + dataStructur.getFieldName();
		    if (fields.contains(fieldName)) {
			dataStructur.setClassName(className);
			columns.put(fields.indexOf(fieldName), dataStructur);
		    }
		}
	    }
	}

	Vector<HashMap<Integer, Object>> data = new Vector<>();
	data = objectManger.saveDisplayDataTo(columns);

	((SearchPanel) view).makeTables(columns, data);
    }

    private BaseDataObjectMgr objectManger;
    private final JComponent view;
}
