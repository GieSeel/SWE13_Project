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
	final HashMap<String, Vector<String>> structur = new HashMap<>();
	Vector<String> fields = new Vector<>();
	fields.add("name");
	fields.add("firstName");
	structur.put("person", fields);
	fields = new Vector<>();
	fields.add("name");
	structur.put("country", fields);

	objectManger = PersonMgr.getInstance();

	// Save relevant data
	final HashMap<String, ColumnInfo> columns = new HashMap<>();
	String fieldName;
	fields = new Vector<>();
	for (final String table : structur.keySet()) {
	    fields = structur.get(table);
	    for (final ColumnInfo dataStructur : DataStructure.getStructureFor(table)) {
		fieldName = dataStructur.getFieldName();
		if (fields.contains(fieldName)) {
		    // key: "person_name" | value: ColumnInfo
		    columns.put(table + "_" + fieldName, dataStructur);
		}
	    }
	}
	Vector<HashMap<String, Object>> data = new Vector<>();
	data = objectManger.saveDisplayDataTo(columns);

	((SearchPanel) view).makeTables(columns, data);
    }

    private BaseDataObjectMgr objectManger;
    private final JComponent view;
}
