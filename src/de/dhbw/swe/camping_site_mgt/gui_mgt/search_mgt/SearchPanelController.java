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
package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.CountryMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataStructure;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
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
	mainPanel = null;
	searchPanels = new HashMap<>();

	// Save search subjects
	final Search_Subjects[] search_Subjects = Search_Subjects.values();
	int subjectLength = search_Subjects.length;
	sarchSubjects = new String[subjectLength];
	subjectLength--;
	for (; subjectLength >= 0; subjectLength--) {
	    sarchSubjects[subjectLength] = search_Subjects[subjectLength].toString();
	}

	initPanels();
    }

    /**
     * {@inheritDoc}.
     * 
     * @see de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable#getGuiSnippet()
     */
    @Override
    public JComponent getGuiSnippet() {
	return mainPanel;
    }

    /**
     * Registers the {@link SearchTableListener} in the {@link SearchPanel} s.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    public void register(final SearchTableListener searchTableListener) {
	for (final SearchPanel searchPanel : searchPanels.values()) {
	    searchPanel.register(searchTableListener);
	}
    }

    /**
     * Selects a panel.
     * 
     * @param searchPanelIndex
     *            the index of the panel
     */
    public void selectPanel(final int searchPanelIndex) {
	mainPanel = searchPanels.get(searchPanelIndex);
    }

    /**
     * Unregisters the {@link SearchTableListener} from the {@link SearchPanel}
     * s.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    public void unregister(final SearchTableListener searchTableListener) {
	for (final SearchPanel searchPanel : searchPanels.values()) {
	    searchPanel.unregister(searchTableListener);
	}
    }

    /**
     * Initialize the search panels.
     */
    private void initPanels() {
	// makeGuestsPanel();
	makePersonsPanel();
	makeCountriesPanel();
	// makeBookingsPanel();

	selectPanel(0);
    }

    /**
     * Save fields that will be displayed.
     */
    private void makeBookingsPanel() {
	final Vector<String> fields = new Vector<>();
	// fields.add("country_name");

	// makePanel(BookingMgr.getInstance(), fields,
	// Search_Subjects.BOOKINGS);
    }

    /**
     * Save fields that will be displayed.
     */
    private void makeCountriesPanel() {
	final Vector<String> fields = new Vector<>();
	fields.add("country_name");
	fields.add("country_acronym");

	makePanel(CountryMgr.getInstance(), fields, Search_Subjects.COUNTRIES);
    }

    /**
     * Save fields that will be displayed.
     */
    private void makeGuestsPanel() {
	final Vector<String> fields = new Vector<>();
	// fields.add("country_name");

	// makePanel(GuestMgr.getInstance(), fields, Search_Subjects.GUESTS);
    }

    /**
     * Prepares data for making a panel, makes it and saves it.
     * 
     * @param fields
     *            the fields that will be displayed
     * @param objectManger
     *            the {@link BaseDataObjectMgr} to get data
     */
    private void makePanel(final BaseDataObjectMgr objectManger,
	    final Vector<String> fields, final Search_Subjects search_Subject) {
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

	final Vector<HashMap<Integer, Object>> data = new Vector<>();
	objectManger.saveObjects2DisplayIn(data, columns);

	final int searchSubjectKey = search_Subject.ordinal();
	final SearchPanel searchPanel = new SearchPanel(columns, data,
		searchSubjectKey, sarchSubjects);
	searchPanels.put(searchSubjectKey, searchPanel);
    }

    /**
     * Save fields that will be displayed.
     */
    private void makePersonsPanel() {
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

	makePanel(PersonMgr.getInstance(), fields, Search_Subjects.PERSONS);
    }

    private SearchPanel mainPanel;
    private final String[] sarchSubjects;
    private final HashMap<Integer, SearchPanel> searchPanels;
}
