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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.dhbw.swe.camping_site_mgt.common.BaseDataObjectMgr;
import de.dhbw.swe.camping_site_mgt.common.CountryMgr;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.DataStructure;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_Booking;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_DatabaseConnector;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_Guest;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_Pitch;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Displayable;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.AddBaseDataObjectListener;
import de.dhbw.swe.camping_site_mgt.gui_mgt.edit.EditDialog;
import de.dhbw.swe.camping_site_mgt.person_mgt.PersonMgr;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_NatureOfSoil;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_Type;

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
	public SearchPanelController(final PersonMgr thePersonMgr,
			final CountryMgr theCountryMgr) {
		// final GuestMgr theGuestMgr) {
		// public SearchPanelController(final BookingMgr theBookingMgr,
		// final EmployeeMgr theEmployeeMgr, final GuestMgr theGuestMgr,
		// final ServiceMgr theServiceMgr) {
		// bookingMgr = theBookingMgr;
		// employeeMgr = theEmployeeMgr;
		// guestMgr = theGuestMgr;
		// serviceMgr = theServiceMgr;
		personMgr = thePersonMgr;
		countryMgr = theCountryMgr;

		view = new JPanel(new BorderLayout());
		searchPanels = new HashMap<>();
		// activSubject = Search_Subjects.values()[0];

		// Save search subjects
		// final Search_Subjects[] search_Subjects = Search_Subjects.values();
		// int subjectLength = search_Subjects.length;
		// sarchSubjects = new String[subjectLength];
		// subjectLength--;
		// for (; subjectLength >= 0; subjectLength--) {
		// sarchSubjects[subjectLength] =
		// search_Subjects[subjectLength].toString();
		// }

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
			public void editRow(HashMap<Integer, ColumnInfo> columns,
					HashMap<Integer, Object> values) {
				if (searchPanelIndex == 2) {
					// Booking
					HashMap<Integer, ColumnInfo> newColumns = new HashMap<>();
					newColumns.put(0, columns.get(0));
					newColumns.put(1, columns.get(1));
					newColumns.put(2, new ColumnInfo("booking", String.class, "Guest"));
					newColumns.put(3, columns.get(4));
					newColumns.put(4, new ColumnInfo("booking", String.class, "Pitch"));
					
					columns = new HashMap<>();
					columns = newColumns;
					
					HashMap<Integer, Object> newValues = new HashMap<>();
					newValues.put(0, values.get(0));
					newValues.put(1, values.get(1));
					if(values.containsKey(8)) {
						newValues.put(2, ((NEU_Guest)values.get(8)).getId());
					} else {
						newValues.put(2, null);
					}
					newValues.put(3, values.get(4));
					if(values.containsKey(9)) {
						newValues.put(4, ((NEU_Pitch)values.get(9)).getId());
					} else {
						newValues.put(4, null);
					}
					
					values = new HashMap<>();
					values = newValues;
				}
				final EditDialog editDialog = new EditDialog(columns, values);
				editDialog.register(new AddBaseDataObjectListener() {

					@Override
					public void add(final HashMap<Integer, Object> newValues) {
						System.out.println(searchPanelIndex);

						NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
								.getInstance();

						if (searchPanelIndex == 0) {
							// Guest
							try {
								NEU_dbConnector.saveGuest(new NEU_Guest(
										(String) newValues.get(0),
										(String) newValues.get(1),
										(String) newValues.get(2),
										new SimpleDateFormat("yyyy/MM/dd")
												.parse((String) newValues
														.get(3)),
										(String) newValues.get(4),
										(String) newValues.get(5),
										(String) newValues.get(6),
										(String) newValues.get(7),
										(String) newValues.get(8),
										(String) newValues.get(9)));
								searchPanels.get(searchPanelIndex).refreshData(
										prepareGuestsForGrid(NEU_dbConnector
												.getGuests()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else if (searchPanelIndex == 1) {
							// Pitch
							NEU_dbConnector.savePitch(new NEU_Pitch(
									Integer.valueOf((String)newValues.get(0)),
									(String) newValues.get(1),
									Pitch_Type.values()[(int)newValues.get(2)],
									Integer.valueOf((String)newValues.get(3)),
											Integer.valueOf((String)newValues.get(4)),
									Pitch_NatureOfSoil.values()[(int)newValues.get(5)],
									(String) newValues.get(6),
									(String) newValues.get(7),
									(String) newValues.get(8)));
							searchPanels.get(searchPanelIndex).refreshData(
									preparePitchesForGrid(NEU_dbConnector
											.getPitches()));
						} else if (searchPanelIndex == 2) {
							// Booking
							try {
								if((Integer) newValues
														.get(2) == -1 || (Integer) newValues
														.get(4) == -1) {
									logger.info("Es wurde nicht alles ausgefüllt!");
									return;
								}
								
								HashMap<Integer, NEU_Guest> guestMap = new HashMap<>();
								int i=0;
								for (NEU_Guest guest : NEU_dbConnector.getGuests()) {
									guestMap.put(i, guest);
									i++;
								}
								HashMap<Integer, NEU_Pitch> pitchMap = new HashMap<>();
								i=0;
								for (NEU_Pitch pitch : NEU_dbConnector.getPitches()) {
									pitchMap.put(i, pitch);
									i++;
								}

								
								NEU_dbConnector.saveBooking(new NEU_Booking(
										new SimpleDateFormat("yyyy/MM/dd")
												.parse((String) newValues
														.get(0)),
										new SimpleDateFormat("yyyy/MM/dd")
												.parse((String) newValues
														.get(1)),
										guestMap.get((Integer) newValues
														.get(2)),
										(boolean) newValues.get(3),
										pitchMap.get((Integer) newValues
														.get(4))));
								searchPanels.get(searchPanelIndex).refreshData(
										prepareBookingsForGrid(NEU_dbConnector
												.getBookings()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							logger.error("Unerwarteter SearchPanelIndex "
									+ searchPanelIndex);
						}

//						refreshPanel();

						// // TODO Auto-generated method stub
						// // ((getClassName von valueobject))
						//
						//
						// BaseDataObjectMgr dataMgr = null;
						//
						// // Get type of the object
						// final Search_Subjects searchSubject = Search_Subjects
						// .values()[(int) newValues.get(-1)];
						// // if
						// (searchSubject.equals(Search_Subjects.BOOKINGS)) {
						// // dataMgr = bookingMgr;
						// // } else
						// // if
						// (searchSubject.equals(Search_Subjects.EMPLOYEES))
						// // {
						// // dataMgr = employeeMgr;
						// // } else
						// // if (searchSubject.equals(Search_Subjects.GUESTS))
						// {
						// // dataMgr = guestMgr;
						// if (searchSubject.equals(Search_Subjects.PERSONS)) {
						// dataMgr = personMgr;
						// } else if (searchSubject
						// .equals(Search_Subjects.COUNTRIES)) {
						// dataMgr = countryMgr;
						// // } else if
						// // (searchSubject.equals(Search_Subjects.SERVICES))
						// // {
						// // dataMgr = serviceMgr;
						// } else {
						// // TODO error unerwartetes search subject
						// }
						//
						// // final DataObject newObject =
						// // dataMgr.saveDisplay2Object(
						// // columns, newValues);
						// // // Insert or update new object
						// // dataMgr.updateObject(newObject);
						// //
						// // // Refresh all data
						// // final Vector<HashMap<Integer, Object>>
						// // displayDataList = new Vector<>();
						// //
						// dataMgr.saveObjects2DisplayIn(searchSubject.ordinal(),
						// // displayDataList, columns);
						// //
						// searchPanels.get(searchSubject.ordinal()).refreshData(
						// // displayDataList);
						// // ;
						// // TODO evtl. nur removeOneEntry and insertOneEntry?
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
		// makeBookingsPanel();
		// makeEmployeesPanel();
		// makeGuestsPanel();
		// makePersonsPanel();
		// makeCountriesPanel();
		// makeServicesPanel();

		NEU_makeGuestsPanel();
		NEU_makePitchesPanel();
		NEU_makeBookingsPanel();

		// selectPanel(activSubject.ordinal());
		selectPanel(0);

		addSearchTableListener();
	}

	/**
	 * Save fields that will be displayed.
	 */
	private void makeBookingsPanel() {
		final Vector<String> fields = new Vector<>();
		fields.add("person_firstName");
		fields.add("person_name");

		fields.add("booking_fellowGuests");
		fields.add("booking_from");
		fields.add("booking_until");
		fields.add("booking_equipments");
		fields.add("booking_pitchBookings");
		fields.add("booking_extraBookings");
		fields.add("booking_chipCards");

		fields.add("bill");

		// makePanel(bookingMgr, fields, Search_Subjects.BOOKINGS);
	}

	/**
	 * Save fields that will be displayed.
	 */
	private void makeCountriesPanel() {
		final Vector<String> fields = new Vector<>();
		fields.add("country_acronym");
		fields.add("country_name");

		makePanel(countryMgr, fields, Search_Subjects.COUNTRIES);
	}

	/**
	 * Save fields that will be displayed.
	 */
	private void makeEmployeesPanel() {
		// final Vector<String> fields = new Vector<>();
		// fields.add("person_identificationNumber");
		// fields.add("person_name");
		// fields.add("person_firstName");
		// fields.add("person_dateOfBirth");
		// fields.add("person_street");
		// fields.add("person_houseNumber");
		//
		// fields.add("town_postalCode");
		// fields.add("town_name");
		//
		// fields.add("country_acronym");
		// fields.add("country_name");
		//
		// fields.add("employee_userName");
		// fields.add("employee_password");
		// fields.add("employee_blocked");
		//
		// fields.add("employeeRole_labeling");
		// fields.add("employeeRole_arrangement");
		//
		// fields.add("chipCard_validFrom");
		// fields.add("chipCard_validTo");
		//
		// makePanel(employeeMgr, fields, Search_Subjects.EMPLOYEES);
	}

	/**
	 * Save fields that will be displayed.
	 */
	private void makeGuestsPanel() {
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

		fields.add("visitorstaxclass_labeling");
		fields.add("visitorstaxclass_price");

		// makePanel(guestMgr, fields, Search_Subjects.GUESTS);
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
				for (final ColumnInfo dataStructur : DataStructure
						.getStructureFor(className)) {
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
				searchSubjectKey, searchSubjects);
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
	 * Save fields that will be displayed.
	 */
	private void makeServicesPanel() {
		// final Vector<String> fields = new Vector<>();
		// fields.add("service_description");
		// fields.add("service_creationDate");
		// fields.add("service_priority");
		// fields.add("service_doneDate");
		//
		// fields.add("employeeRole_labeling");
		// fields.add("employeeRole_arrangement");
		//
		// fields.add("site_labeling");
		// fields.add("site_type");
		// fields.add("site_openingHours");
		// fields.add("site_description");
		//
		// fields.add("pitch_id");
		// fields.add("pitch_area");
		// fields.add("pitch_type");
		// fields.add("pitch_height");
		// fields.add("pitch_width");
		// fields.add("pitch_natureOfSoil");
		// fields.add("pitch_characteristics");
		//
		// makePanel(serviceMgr, fields, Search_Subjects.SERVICES);

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
		this.searchPanelIndex = searchPanelIndex;
		// activSubject = Search_Subjects.values()[searchPanelIndex];
		activeSearchPanel = searchPanels.get(searchPanelIndex);
		view.add(activeSearchPanel);
		refreshPanel();
	}

	private Integer searchPanelIndex;

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

	private void NEU_makeGuestsPanel() {
		NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
				.getInstance();

		Collection<NEU_Guest> guests = NEU_dbConnector.getGuests();
		System.out.println(guests);

		HashMap<Integer, ColumnInfo> columns = new HashMap<>();
		columns.put(0, new ColumnInfo("guest", String.class,
				"Identification Number"));
		columns.put(1, new ColumnInfo("guest", String.class, "Name"));
		columns.put(2, new ColumnInfo("guest", String.class, "First Name"));
		columns.put(3, new ColumnInfo("guest", Date.class, "Date of Birth"));
		columns.put(4, new ColumnInfo("guest", String.class, "Street"));
		columns.put(5, new ColumnInfo("guest", String.class, "House Number"));
		columns.put(6, new ColumnInfo("guest", String.class, "Town"));
		columns.put(7, new ColumnInfo("guest", String.class, "Postal Code"));
		columns.put(8, new ColumnInfo("guest", String.class, "Country"));
		columns.put(9, new ColumnInfo("guest", String.class, "Country Acronym"));

		Vector<HashMap<Integer, Object>> data = prepareGuestsForGrid(guests);

		final SearchPanel searchPanel = new SearchPanel(columns, data, 0,
				searchSubjects);
		searchPanels.put(0, searchPanel);
	}

	private Vector<HashMap<Integer, Object>> prepareGuestsForGrid(
			Collection<NEU_Guest> guests) {
		Vector<HashMap<Integer, Object>> data = new Vector<>();

		for (NEU_Guest guest : guests) {
			HashMap<Integer, Object> dat = new HashMap<>();
			dat.put(0, guest.getPerson_identification_number());
			dat.put(1, guest.getPerson_name());
			dat.put(2, guest.getPerson_first_name());
			dat.put(3, new SimpleDateFormat("yyyy/MM/dd").format(guest
					.getPerson_date_of_birth()));
			dat.put(4, guest.getAddress_street());
			dat.put(5, guest.getAddress_house_number());
			dat.put(6, guest.getTown_name());
			dat.put(7, guest.getTown_postal_code());
			dat.put(8, guest.getCountry_name());
			dat.put(9, guest.getCountry_acronym());
			dat.put(10, guest.getId());

			data.add(dat);
		}
		return data;
	}

	private void NEU_makePitchesPanel() {
		NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
				.getInstance();

		Collection<NEU_Pitch> pitches = NEU_dbConnector.getPitches();
		System.out.println(pitches);

		HashMap<Integer, ColumnInfo> columns = new HashMap<>();
		columns.put(0, new ColumnInfo("pitch", Integer.class, "Number"));
		columns.put(1, new ColumnInfo("pitch", String.class, "Area"));
		columns.put(2, new ColumnInfo("pitch", String.class, "Type"));
		columns.put(3, new ColumnInfo("pitch", Integer.class, "Height"));
		columns.put(4, new ColumnInfo("pitch", Integer.class, "Width"));
		columns.put(5, new ColumnInfo("pitch", String.class, "Nature of Soil"));
		columns.put(6, new ColumnInfo("pitch", String.class, "Characteristics"));
		columns.put(7, new ColumnInfo("pitch", String.class, "X-Coords"));
		columns.put(8, new ColumnInfo("pitch", String.class, "Y-Coords"));

		Vector<HashMap<Integer, Object>> data = preparePitchesForGrid(pitches);

		final SearchPanel searchPanel = new SearchPanel(columns, data, 1,
				searchSubjects);
		searchPanels.put(1, searchPanel);
	}

	private Vector<HashMap<Integer, Object>> preparePitchesForGrid(
			Collection<NEU_Pitch> pitches) {
		Vector<HashMap<Integer, Object>> data = new Vector<>();

		for (NEU_Pitch pitch : pitches) {
			HashMap<Integer, Object> dat = new HashMap<>();
			dat.put(0, pitch.getId());
			dat.put(1, pitch.getArea());
			dat.put(2, pitch.getType());
			dat.put(3, pitch.getHeight());
			dat.put(4, pitch.getWidth());
			dat.put(5, pitch.getNatureOfSoil());
			dat.put(6, pitch.getCharacteristics());
			dat.put(7, pitch.getX_coords());
			dat.put(8, pitch.getY_coords());

			data.add(dat);
		}
		return data;
	}

	private void NEU_makeBookingsPanel() {
		NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
				.getInstance();

		Collection<NEU_Booking> bookings = NEU_dbConnector.getBookings();
		System.out.println(bookings);

		HashMap<Integer, ColumnInfo> columns = new HashMap<>();
		columns.put(0, new ColumnInfo("booking", String.class, "From"));
		columns.put(1, new ColumnInfo("booking", String.class, "Until"));
		columns.put(2, new ColumnInfo("booking", String.class, "Guest Name"));
		columns.put(3, new ColumnInfo("booking", String.class,
				"Guest First Name"));
		columns.put(4, new ColumnInfo("booking", String.class, "Checked In"));
		columns.put(5, new ColumnInfo("booking", String.class, "Pitch Number"));
		columns.put(6, new ColumnInfo("booking", String.class, "Pitch Area"));

		Vector<HashMap<Integer, Object>> data = prepareBookingsForGrid(bookings);

		final SearchPanel searchPanel = new SearchPanel(columns, data, 2,
				searchSubjects);
		searchPanels.put(2, searchPanel);
	}

	private Vector<HashMap<Integer, Object>> prepareBookingsForGrid(
			Collection<NEU_Booking> bookings) {
		Vector<HashMap<Integer, Object>> data = new Vector<>();

		for (NEU_Booking booking : bookings) {
			HashMap<Integer, Object> dat = new HashMap<>();
			dat.put(0, new SimpleDateFormat("yyyy/MM/dd").format(booking
					.getDuration_from()));
			dat.put(1, new SimpleDateFormat("yyyy/MM/dd").format(booking
					.getDuration_until()));
			dat.put(2, booking.getGuest().getPerson_name());
			dat.put(3, booking.getGuest().getPerson_first_name());
			dat.put(4, booking.isChecked_in());
			dat.put(5, booking.getPitch().getId());
			dat.put(6, booking.getPitch().getArea());
			dat.put(7, booking.getId());
			dat.put(8, booking.getGuest());
			dat.put(9, booking.getPitch());

			data.add(dat);
		}
		return data;
	}

	private SearchPanel activeSearchPanel;
	// private Search_Subjects activSubject;

	private final CountryMgr countryMgr;
	// private final GuestMgr guestMgr;
	// private final ServiceMgr serviceMgr;
	// private final BookingMgr bookingMgr;
	// private final EmployeeMgr employeeMgr;
	private final PersonMgr personMgr;

	private final String[] searchSubjects = { "Guests", "Pitches", "Bookings" };

	private final HashMap<Integer, SearchPanel> searchPanels;
	private final JPanel view;
	/** The {@link CampingLogger}. */
	private static CampingLogger logger = CampingLogger
			.getLogger(SearchPanelController.class);
}
