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

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.dhbw.swe.camping_site_mgt.booking_mgt.BookingMgr;
import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.CountryMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.*;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.AddBaseDataObjectListener;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.EditDialog;
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
    public SearchPanelController(final CountryMgr theCountryMgr,
	    final BookingMgr theBookingMgr, final PersonMgr thePersonMgr) {
	countryMgr = theCountryMgr;
	bookingMgr = theBookingMgr;
	personMgr = thePersonMgr;
	view = new JPanel(new BorderLayout());
	searchPanels = new HashMap<>();
	activSubject = Search_Subjects.COUNTRIES;

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
	return view;
    }

    /**
     * Refreshes the panel.
     */
    public void refreshPanel() {
	view.repaint();
    }

    /**
     * Adds the {@link SearchTableListener}.
     */
    private void addSearchTableListener() {
	register(new SearchTableListener() {

	    @Override
	    public void editRow(final HashMap<Integer, ColumnInfo> columns,
		    final HashMap<Integer, Object> values) {
		final EditDialog editDialog = new EditDialog(columns, values);
		editDialog.register(new AddBaseDataObjectListener() {

		    @Override
		    public void add(final HashMap<Integer, Object> newValues) {
			// TODO Auto-generated method stub
			// ((getClassName von valueobject))

			BaseDataObjectMgr dataMgr = null;

			// Get type of the object
			final Search_Subjects searchSubject = Search_Subjects.values()[(int) newValues.get(-1)];
			if (searchSubject.equals(Search_Subjects.COUNTRIES)) {
			    dataMgr = countryMgr;
			} else if (searchSubject.equals(Search_Subjects.PERSONS)) {
			    dataMgr = personMgr;
			} else {
			    // TODO error unerwartetes search subject
			}

			final DataObject newObject = dataMgr.saveDisplay2Object(
				columns, newValues);
			// Insert or update new object
			dataMgr.updateObject(newObject);

			// Refresh all data
			final Vector<HashMap<Integer, Object>> displayDataList = new Vector<>();
			dataMgr.saveObjects2DisplayIn(searchSubject.ordinal(),
				displayDataList, columns);
			searchPanels.get(searchSubject.ordinal()).refreshData(
				displayDataList);
			;
			// TODO evtl. nur removeOneEntry and insertOneEntry?

			// makeCountriesPanel();
			// refreshPanel();
			// TODO daten aktuallisieren
		    }
		});
	    }

	    @Override
	    public void subjectChangedTo(final int index) {
		selectPanel(index);
	    }
	});
    }

    /**
     * Initialize the search panels.
     */
    private void initPanels() {
	// makeGuestsPanel();
	makePersonsPanel();
	makeCountriesPanel();
	// makeBookingsPanel();

	selectPanel(activSubject.ordinal());

	addSearchTableListener();
    }

    /**
     * Save fields that will be displayed.
     */
    private void makeBookingsPanel() {
	final Vector<String> fields = new Vector<>();
	fields.add("booking_id");
	fields.add("booking_chipCards");
	fields.add("booking_equipments");
	fields.add("booking_extraBookings");
	fields.add("booking_fellowGuests");
	fields.add("booking_from");
	fields.add("booking_pitchBookings");
	fields.add("person_firstName");
	fields.add("person_name");
	fields.add("booking_until");
	fields.add("bill");

	// makePanel(bookingMgr, fields, Search_Subjects.BOOKINGS);
    }

    /**
     * Save fields that will be displayed.
     */
    private void makeCountriesPanel() {
	final Vector<String> fields = new Vector<>();
	fields.add("country_name");
	fields.add("country_acronym");

	makePanel(countryMgr, fields, Search_Subjects.COUNTRIES);
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
	final int searchSubjectKey = search_Subject.ordinal();
	objectManger.saveObjects2DisplayIn(searchSubjectKey, data, columns);

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
	fields.add("person_dateOfBirth");
	fields.add("person_street");
	fields.add("person_houseNumber");

	fields.add("town_postalCode");
	fields.add("town_name");

	fields.add("country_acronym");
	fields.add("country_name");

	makePanel(personMgr, fields, Search_Subjects.PERSONS);
    }

    /**
     * Registers the {@link SearchTableListener} in the {@link SearchPanel} s.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    private void register(final SearchTableListener searchTableListener) {
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
    private void selectPanel(final int searchPanelIndex) {
	if (view != null) {
	    view.removeAll();
	}
	activSubject = Search_Subjects.values()[searchPanelIndex];
	activeSearchPanel = searchPanels.get(searchPanelIndex);
	view.add(activeSearchPanel);
	refreshPanel();
    }

    /**
     * Unregisters the {@link SearchTableListener} from the {@link SearchPanel}
     * s.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    private void unregister(final SearchTableListener searchTableListener) {
	for (final SearchPanel searchPanel : searchPanels.values()) {
	    searchPanel.unregister(searchTableListener);
	}
    }

    private SearchPanel activeSearchPanel;
    private Search_Subjects activSubject;
    private final BookingMgr bookingMgr;
    private final CountryMgr countryMgr;
    private final PersonMgr personMgr;
    private final String[] sarchSubjects;
    private final HashMap<Integer, SearchPanel> searchPanels;
    private final JPanel view;
}
