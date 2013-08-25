package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import de.dhbw.swe.campingplatzverwaltung.common.*;
import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.*;

public class SearchPanel extends JPanel {

    /**   */
    private static final long serialVersionUID = 1L;

    public SearchPanel() {
	dbController = DatabaseController.getInstance();
	gui = Gui.getInstance();

	final List<Object[]> bodyData = new ArrayList<Object[]>();
	final List<Object[]> headData = new ArrayList<Object[]>();

	// dbController.queryInsertGuest(new Guest(0, new Person(0, new
	// Address(0,
	// "Schlehenweg", "3", new Town(0,
	// new Country(0, "DE", "Deutschland"), "Öhringen", "74613")),
	// new Date(1992, 2, 24), "Benedikt", "627685136838", "Giesel"),
	// new VisitorsTaxClass(3, null, null)));

	final String[] coulmnNames = { "Identification Number", "First Name",
		"Name", "Date of Birth", "Street", "House Number", "Postal Code",
		"Town", "Country", "Country Acronym", "Visitors Tax Class",
		"Visitors Tax Class Price" };

	final List<Guest> guests = dbController.querySelectGuest();
	for (final Guest guest : guests) {
	    bodyData.add(guest.getData().toArray());
	}

	final String[] beispielHeadData = { "6278386343D", "Benedikt", "Giesel",
		"24.02.1992", "Mainhardterstr.", "23", "74629", "Untersteinbach",
		"Deutschland", "DE", "busy season", "2,0" };
	final List<Object> emptyData = new ArrayList<Object>();
	for (int i = 0; i < coulmnNames.length; i++) {
	    // emptyData.add("");
	    emptyData.add(beispielHeadData[i]);
	}
	headData.add(emptyData.toArray());

	initRowFilterList(coulmnNames);

	final JTable headTable = new JTable(headData.toArray(new Object[0][0]),
		coulmnNames);
	headTable.setRowSelectionAllowed(false);
	headTable.getTableHeader().setReorderingAllowed(false);
	headTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	// TODO Table Eingabeformatierung (z.B. nur Zahlen zulassen...)

	final JTable bodyTable = new JTable(bodyData.toArray(new Object[0][0]),
		coulmnNames) {
	    /**   */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public boolean isCellEditable(final int row, final int column) {
		return false;
	    }
	};

	bodyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	bodyTable.getTableHeader().setReorderingAllowed(false);
	// bodytable.getTableHeader().setResizingAllowed(false);
	// bodyTable.getTableHeader().setVisible(false);
	// bodyTable.setTableHeader(null); // -- macht resize column kaputt
	bodyTable.setAutoCreateRowSorter(true);
	bodyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	// Change both column widths
	final TableColumnModel headColumnModel = headTable.getColumnModel();
	headColumnModel.addColumnModelListener(new TableColumnModelListener() {

	    @Override
	    public void columnAdded(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnMarginChanged(final ChangeEvent arg0) {
		// Check if the column width was changed
		if (headTable.getTableHeader().getResizingColumn() != null) {
		    final TableColumn headColumn = headTable.getTableHeader().getResizingColumn();
		    final TableColumn bodyColumn = bodyTable.getTableHeader().getColumnModel().getColumn(
			    headTable.getTableHeader().getColumnModel().getColumnIndex(
				    headColumn.getIdentifier()));
		    bodyColumn.setPreferredWidth(headColumn.getPreferredWidth());
		}
	    }

	    @Override
	    public void columnMoved(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnRemoved(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnSelectionChanged(final ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
	    }
	});

	final TableColumnModel bodyColumnModel = bodyTable.getColumnModel();
	bodyColumnModel.addColumnModelListener(new TableColumnModelListener() {

	    @Override
	    public void columnAdded(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnMarginChanged(final ChangeEvent arg0) {
		// Check if the column width was changed
		if (bodyTable.getTableHeader().getResizingColumn() != null) {
		    final TableColumn bodyColumn = bodyTable.getTableHeader().getResizingColumn();
		    final TableColumn headColumn = headTable.getTableHeader().getColumnModel().getColumn(
			    bodyTable.getTableHeader().getColumnModel().getColumnIndex(
				    bodyColumn.getIdentifier()));
		    headColumn.setPreferredWidth(bodyColumn.getWidth());
		}
	    }

	    @Override
	    public void columnMoved(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnRemoved(final TableColumnModelEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void columnSelectionChanged(final ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
	    }
	});

	// Get Selection
	bodyTable.getSelectionModel().addListSelectionListener(
		new ListSelectionListener() {
		    @Override
		    public void valueChanged(final ListSelectionEvent event) {
			final int viewRow = bodyTable.getSelectedRow();
			if (viewRow < 0) {
			    // Selection got filtered away.
			    gui.clearStatus();
			} else {
			    final int modelRow = bodyTable.convertRowIndexToModel(viewRow);
			    gui.setStatusBarStatus(String.format(
				    "Selected Row in view: %d. "
					    + "Selected Row in model: %d.",
				    viewRow, modelRow));
			}
		    }
		});

	// TODO onkeypressed oder so..
	headTable.getModel().addTableModelListener(new TableModelListener() {

	    @Override
	    public void tableChanged(final TableModelEvent arg0) {
		// Zelle ausfindigmachen und auslesen
		final int rowNumber = arg0.getFirstRow();
		final int columnNumber = arg0.getColumn();
		final TableModel tableModel = (TableModel) arg0.getSource();
		final String columnName = tableModel.getColumnName(columnNumber);
		final Object cellData = tableModel.getValueAt(rowNumber,
			columnNumber);
		gui.setStatusBarStatus(columnName + ": " + cellData.toString());

		// Filter
		RowFilter<Object, Object> rowFilter = RowFilter.regexFilter("(?i)^"
			+ cellData.toString() + ".*$", columnNumber);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
			bodyTable.getModel());

		rowFilterList.set(columnNumber, rowFilter);

		rowFilter = RowFilter.andFilter(rowFilterList);

		sorter.setRowFilter(rowFilter);
		bodyTable.setRowSorter(sorter);
	    }
	});

	final JButton but_clear = new JButton("Clear");
	final JButton but_save = new JButton("Save");

	but_clear.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Clear all inputs
		for (int i = 0; i < coulmnNames.length; i++) {
		    headTable.getModel().setValueAt("", 0, i);
		}
	    }
	});
	but_save.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Save data into database

		final List<String> dataList = new ArrayList<String>();
		for (int i = 0; i < coulmnNames.length; i++) {
		    final String val = (String) headTable.getModel().getValueAt(0,
			    i);
		    if (val.equals("")) {
			gui.setStatusBarStatus("Alle Felder müssen gefüllt sein!!");
			dataList.clear();
			break;
		    } else {
			dataList.add(val);
		    }
		}

		if (!dataList.isEmpty()) {

		    // "Identification Number", "First Name", "Name",
		    // "Date of Birth", "Street", "House Number", "Postal Code",
		    // "Town", "Country", "Country Acronym",
		    // "Visitors Tax Class",
		    // "Visitors Tax Class Price"

		    int id = 0;
		    if ((id = dbController.queryInsertUpdateGuest(new Guest(
			    new Person(new Address(dataList.get(4),
				    dataList.get(5), new Town(new Country(
					    dataList.get(9), dataList.get(8)),
					    dataList.get(7), dataList.get(6))),
				    dataList.get(3), dataList.get(1),
				    dataList.get(0), dataList.get(2)),
			    new VisitorsTaxClass(dataList.get(10), dataList.get(11))))) != 0) {

			// Add row
			// TODO!!!!!!!!!!!!!!!!!!

			// final DefaultTableModel model = (DefaultTableModel)
			// bodyTable.getModel();
			// model.addRow(dbController.querySelectGuest(id).getData().toArray());

			// TODO next
			// click on row -> open formular -> edit entry -> save
			// in database
		    }
		}
	    }
	});

	final JPanel mainPanel = new JPanel();
	mainPanel.setMinimumSize(new Dimension(800, 600));
	mainPanel.setLayout(new BorderLayout());

	final JScrollPane headScrollPane = new JScrollPane(headTable);
	final JScrollPane bodyScrollPane = new JScrollPane(bodyTable);

	headScrollPane.setPreferredSize(new Dimension(this.getWidth(), 40));
	headScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	headScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	headScrollPane.getVerticalScrollBar().setModel(
		bodyScrollPane.getVerticalScrollBar().getModel());
	headScrollPane.getHorizontalScrollBar().setModel(
		bodyScrollPane.getHorizontalScrollBar().getModel());

	mainPanel.add(headScrollPane, BorderLayout.NORTH);
	mainPanel.add(bodyScrollPane, BorderLayout.CENTER);

	final JPanel buttons = new JPanel();
	buttons.setLayout(new FlowLayout());
	buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	buttons.add(but_clear);
	buttons.add(but_save);
	mainPanel.add(buttons, BorderLayout.SOUTH);

	this.setLayout(new BorderLayout());
	this.add(mainPanel, BorderLayout.CENTER);
    }

    private void initRowFilterList(final String[] coulmnNames) {
	rowFilterList = new ArrayList<RowFilter<Object, Object>>(coulmnNames.length);
	for (int i = 0; i < coulmnNames.length; i++) {
	    rowFilterList.add(RowFilter.regexFilter("^.*$", i));
	}
    }

    private DatabaseController dbController;
    private Gui gui;
    private List<RowFilter<Object, Object>> rowFilterList;
}
