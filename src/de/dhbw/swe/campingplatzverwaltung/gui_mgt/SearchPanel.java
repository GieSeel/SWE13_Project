package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import de.dhbw.swe.campingplatzverwaltung.common.database_mgt.DatabaseController;
import de.dhbw.swe.campingplatzverwaltung.person_mgt.Guest;

public class SearchPanel extends JPanel {

    /**   */
    private static final long serialVersionUID = 1L;

    public SearchPanel() {
	dbController = DatabaseController.getInstance();
	this.gui = Gui.getInstance();

	final List<Guest> objects = dbController.querySelectGuest();
	final List<String[]> coulmnObjects = getColumnObjects(
		objects.get(0).getClass().getSimpleName(), "");

	// ## DEL
	final HashMap<String, Object> bspData = objects.get(0).getTableData("");
	// ##

	// TODO string.equals ((ignorCase??))

	final List<HashMap<String, Object>> bodyData = new Vector<HashMap<String, Object>>();
	for (final Guest guest : objects) {
	    bodyData.add(guest.getTableData(""));
	}

	// TODO autovervollständigung (evtl. erst wenn nur noch ein eintrag
	// gefiltert ist)

	final CampingTable headTable = new CampingTable(coulmnObjects);
	final CampingTable bodyTable = new CampingTable(coulmnObjects, bodyData);

	sorter = new TableRowSorter<CampingTableModel>(bodyTable.getTableModel());
	initRowFilterList(coulmnObjects);

	headTable.setHeadTableSettings();
	bodyTable.setBodyTableSettings();
	sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
	bodyTable.setRowSorter(sorter);

	// ## DEL
	headTable.insertData(bspData);
	// ##

	// TODO
	// getColumnModel().getColumn(colIndex).setHeaderRenderer(new
	// MyButtonHeaderRenderer());
	// oder
	// selbst position setzten: ((https://forums.oracle.com/thread/1359052))
	// headTable.getTableHeader().getHeaderRect(column)

	// Change both column widths
	final CampingTable[] tableList = { headTable, bodyTable };
	for (int i = 0; i < tableList.length; i++) {
	    final int index = i;
	    tableList[index].getColumnModel().addColumnModelListener(
		    new TableColumnModelListener() {

			@Override
			public void columnAdded(final TableColumnModelEvent arg0) {
			}

			@Override
			public void columnMarginChanged(final ChangeEvent arg0) {
			    // Check if the column width was changed

			    final TableColumn resizedColumn;
			    if ((resizedColumn = tableList[index].getTableHeader().getResizingColumn()) != null) {
				tableList[tableList.length - 1 - index].getTableHeader().getColumnModel().getColumn(
					tableList[index].getTableHeader().getColumnModel().getColumnIndex(
						resizedColumn.getIdentifier())).setPreferredWidth(
					resizedColumn.getPreferredWidth());
			    }
			}

			@Override
			public void columnMoved(final TableColumnModelEvent arg0) {
			}

			@Override
			public void columnRemoved(final TableColumnModelEvent arg0) {
			}

			@Override
			public void columnSelectionChanged(
				final ListSelectionEvent arg0) {
			}
		    });
	}

	// Double-click event on a row
	bodyTable.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent e) {
		if (e.getClickCount() == 2) {
		    final HashMap<String, Object[]> data = bodyTable.getRowValues(bodyTable.getSelectedRow());
		    System.out.println(data);
		    // TODO open formular
		}
	    }
	});

	// Filters the data on key released
	headTable.addKeyListener(new KeyListener() {

	    @Override
	    public void keyPressed(final KeyEvent arg0) {
	    }

	    @Override
	    public void keyReleased(final KeyEvent arg0) {
		final int column = headTable.getEditingColumn();
		if (column != -1) {
		    final TableCellEditor editor = headTable.getCellEditor();
		    if (editor != null) {
			// Updates the cell value
			editor.stopCellEditing();
		    }

		    // TODO inputvalidation (z.B.: geb. darf nur 24.02.1992 sein
		    // und sonst nichts!)

		    // Filter
		    final RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(
			    "(?i)^" + headTable.getValueAt(0, column) + ".*$",
			    column);
		    // TODO Umlaute caseinsensitiv??
		    rowFilterList.set(column, rowFilter);
		    sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
		    bodyTable.setRowSorter(sorter);
		}
	    }

	    @Override
	    public void keyTyped(final KeyEvent arg0) {
	    }
	});

	// Add GUI elements
	final JButton but_clear = new JButton("Clear");
	final JButton but_save = new JButton("Save");

	but_clear.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Clear all inputs
		headTable.removeAllDataAndInsertAnEmptyRow();
	    }
	});

	// Save data
	but_save.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Get input data and save it into database
		final HashMap<String, Object> data = headTable.getInputValues();

		// Check if all fields are filled
		if (data.containsValue("")) {
		    gui.setStatusBarStatus("Alle Felder müssen gefüllt sein!!");
		    return;
		} else {
		    // bodyTable.insertData(data);
		    System.out.println(data);
		    // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    // ((DateFXI!!
		    if (dbController.queryInsertUpdateGuest(new Guest().setTableData(data)) != 0) {
			bodyTable.insertData(data);
		    }
		}
	    }
	});

	final JPanel mainPanel = new JPanel();
	mainPanel.setMinimumSize(new Dimension(800, 600));
	mainPanel.setLayout(new BorderLayout());

	final JScrollPane headScrollPane = new JScrollPane(headTable);
	final JScrollPane bodyScrollPane = new JScrollPane(bodyTable);
	// TODO scrollpane funzt nicht?!

	// headScrollPane.setPreferredSize(new Dimension(this.getWidth(), 40));
	headScrollPane.setPreferredSize(new Dimension(this.getWidth(), 200));
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

    /**
     * Returns the column names of the given class.
     * 
     * @param className
     *            is the name of the class
     * @return
     */
    public List<String[]> getColumnObjects(String className,
	    final String parentClass) {
	className = className.toLowerCase();
	if (className == "booking") {
	    // TODO speziell!!!
	} else {
	    final String[][] elements = dbController.getSqlObjectClass(className);
	    final List<String[]> columnObjects = new Vector<String[]>();
	    // columnNames[0] = internal column name | columnNames[1] = value
	    for (final String[] element : elements) {
		if (element[2] == null) {
		    if (!element[0].equals("id")) {
			columnObjects.addAll(getColumnObjects(
				element[0].split("_")[0], parentClass + className
					+ "_"));
		    }
		} else {
		    final String[] val = {
			    parentClass + className + "_" + element[0], element[2],
			    element[1] };// TODO
		    // mit
		    // validation,
		    // element[3]
		    // };
		    columnObjects.add(val);
		}
	    }
	    return columnObjects;
	}
	return null;
    }

    private void initRowFilterList(final List<String[]> columnObjects) {

	// Prepare the integer-comparator
	final Comparator<Integer> intComp = new Comparator<Integer>() {
	    @Override
	    public int compare(final Integer obj1, final Integer obj2) {
		if (obj1 > obj2) {
		    return 1;
		} else if (obj1 < obj2) {
		    return -1;
		}
		return 0;
	    }
	};
	// TODO evtl. weitere comperatoren bauen

	rowFilterList = new ArrayList<RowFilter<Object, Object>>();
	for (int i = 0; i < columnObjects.size(); i++) {
	    // Set empty filter for that column
	    rowFilterList.add(RowFilter.regexFilter("^.*$", i));

	    // Set column comparator
	    final String type = columnObjects.get(i)[1];
	    if (type.equals("int")) {
		sorter.setComparator(i, intComp);
	    } else if (type.equals("float")) {

	    } else if (type.equals("date")) {

	    } else if (type.equals("string")) {

	    } else {
		// TODO throw Unerwarteter Typ!
	    }
	}
    }

    private final DatabaseController dbController;
    private final Gui gui;
    private List<RowFilter<Object, Object>> rowFilterList;
    private final TableRowSorter<CampingTableModel> sorter;
}
