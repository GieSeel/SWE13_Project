package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.statusbar.StatusBarController;

public class SearchPanel extends JPanel {

    private static CampingLogger logger = CampingLogger.getLogger(SearchPanel.class);

    /**   */
    private static final long serialVersionUID = 1L;

    public SearchPanel() {
	rowFilterList = new Vector<>();
	sorter = new TableRowSorter<>();
	columns = new HashMap<>();

	// TODO string.equals ((ignorCase??))
	// TODO autovervollständigung (evtl. erst wenn nur noch ein eintrag
	// gefiltert ist)
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
	    // final String[][] elements =
	    // dbController.getSqlObjectClass(className);
	    // final List<String[]> columnObjects = new Vector<String[]>();
	    // // columnNames[0] = internal column name | columnNames[1] = value
	    // for (final String[] element : elements) {
	    // if (element[2] == null) {
	    // if (!element[0].equals("id")) {
	    // columnObjects.addAll(getColumnObjects(
	    // element[0].split("_")[0], parentClass + className
	    // + "_"));
	    // }
	    // } else {
	    // final String[] val = {
	    // parentClass + className + "_" + element[0], element[2],
	    // element[1] };// TODO
	    // // mit
	    // // validation,
	    // // element[3]
	    // // };
	    // columnObjects.add(val);
	    // }
	    // }
	    // return columnObjects;
	    return null;
	}
	return null;
    }

    public void makeTables(final HashMap<Integer, ColumnInfo> columns,
	    final Vector<HashMap<Integer, Object>> data) {

	this.columns = columns;

	final CampingTable headTable = new CampingTable(columns);
	final CampingTable bodyTable = new CampingTable(columns, data);

	sorter = new TableRowSorter<CampingTableModel>(bodyTable.getTableModel());
	initRowFilterList();

	headTable.setHeadTableSettings();
	bodyTable.setBodyTableSettings();

	sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
	bodyTable.setRowSorter(sorter);

	bodyTable.insertEmptyRow();

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
				// TODO ColumnModel -> use other identifier
				// (e.g. columnKey)
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
		    final HashMap<Integer, Object> values = bodyTable.getRowValues(bodyTable.getSelectedRow());
		    delegate.getDelegator().editRow(columns, values);
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

		// Reset Filter
		initRowFilterList();
		sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
	    }
	});

	// Save data
	but_save.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Get input data and save it into database
		final HashMap<Integer, Object> data = headTable.getInputValues();

		// Check if all fields are filled
		if (data.containsValue("")) {
		    StatusBarController.getInstance().setStatus(
			    "You have to fill all fields first!");
		    return;
		} else {
		    // TODO
		    // ============================================================================================
		    // PersonMgr.getInstance().objectFromDisplay(columns, data);
		    // ============================================================================================

		    // bodyTable.insertData(data);
		    System.out.println(data);
		    // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		    // ((DateFXI!!
		    // if (dbController.queryInsertUpdateGuest(new
		    // Guest().setTableData(data)) != 0) {
		    // bodyTable.insertData(data);
		    // }
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
     * Registers a {@link SearchTableListener}.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    public void register(final SearchTableListener searchTableListener) {
	delegate.register(searchTableListener);
    }

    /**
     * Unregisters a {@link SearchTableListener}.
     * 
     * @param searchTableListener
     *            the {@link SearchTableListener}
     */
    public void unregister(final SearchTableListener searchTableListener) {
	delegate.unregister(searchTableListener);
    }

    /**
     * Initialize the row filters and sorters
     */
    private void initRowFilterList() {

	// Prepares the integer-comparator
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

	rowFilterList = new Vector<RowFilter<Object, Object>>();

	int i = 0;
	for (final ColumnInfo columnValues : columns.values()) {
	    // Set empty filter for that column
	    rowFilterList.add(RowFilter.regexFilter("^.*$", i));

	    // Set column comparator
	    final Class<? extends Object> type = columnValues.getDbType();
	    if (type.equals(Integer.class)) {
		// Integer
		sorter.setComparator(i, intComp);
	    } else if (type.equals(Float.class)) {
		// Float
	    } else if (type.equals(Euro.class)) {
		// Euro
	    } else if (type.equals(String.class)) {
		// String
	    } else if (type.equals(Array.class)) {
		// Array
	    } else if (type.equals(Date.class)) {
		// Date
	    } else if (type.equals(Enum.class)) {
		// Enum
	    } else {
		logger.error("Unexpected typ while setting comparators!");
	    }
	    i++;
	}
    }

    private HashMap<Integer, ColumnInfo> columns;
    private final Delegate<SearchTableListener> delegate = new Delegate<>(
	    SearchTableListener.class);
    private Vector<RowFilter<Object, Object>> rowFilterList;
    private TableRowSorter<CampingTableModel> sorter;
}
