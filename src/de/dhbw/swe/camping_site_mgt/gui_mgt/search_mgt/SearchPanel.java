package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.*;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.Euro;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.logging.CampingLogger;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;

public class SearchPanel extends JPanel {

    private static CampingLogger logger = CampingLogger.getLogger(SearchPanel.class);

    /**   */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param columns
     * @param data
     * @param comboIndex
     * @param sarchSubjects
     */
    public SearchPanel(final HashMap<Integer, ColumnInfo> columns,
	    final Vector<HashMap<Integer, Object>> data, final int comboIndex,
	    final String[] sarchSubjects) {
	makeTables(columns, data, comboIndex, sarchSubjects);

	// TODO autovervollständigung (evtl. erst wenn nur noch ein eintrag
	// gefiltert ist)
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
     * Clears the {@link RowFilter} list.
     * 
     * @param columns
     *            the columns that need the {@link RowFilter}s
     */
    private void clearRowFilterList(final HashMap<Integer, ColumnInfo> columns) {
	rowFilterList = new Vector<>();
	for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
	    // Set empty filter for that column
	    rowFilterList.add(RowFilter.regexFilter("^.*$", column.getKey()));
	}
    }

    /**
     * Initializes the row sorters.
     * 
     * @param sorter
     *            the {@link TableRowSorter}
     * @param columns
     *            the columns that need the sorters
     */
    private void initSorter(final TableRowSorter<CampingTableModel> sorter,
	    final HashMap<Integer, ColumnInfo> columns) {
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

	int index;
	for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
	    index = column.getKey();
	    // Set column comparator
	    final Class<? extends Object> type = column.getValue().getDbType();
	    if (type.equals(Integer.class)) {
		// Integer
		sorter.setComparator(index, intComp);
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
	}
    }

    /**
     * Makes the tables and fills them with data.
     * 
     * @param columns
     *            the columns that will show
     * @param data
     *            the data for the table
     * @param sarchSubjects
     */
    private void makeTables(final HashMap<Integer, ColumnInfo> columns,
	    final Vector<HashMap<Integer, Object>> data, final int comboIndex,
	    final String[] sarchSubjects) {

	final int defaultHeight = new JButton(" ").getPreferredSize().height;
	final int screenWidth = Gui.screenSize.width;

	final DefaultTableColumnModel dtColumnModel = new DefaultTableColumnModel();
	final CampingTable headTable = new CampingTable(new CampingTableModel(
		columns), dtColumnModel);
	final CampingTable bodyTable = new CampingTable(new CampingTableModel(
		columns), dtColumnModel);
	headTable.setHeadTableSettings();
	headTable.setRowHeight(defaultHeight);
	bodyTable.setBodyTableSettings();

	headTable.insertEmptyRow();
	bodyTable.insertData(data);

	final TableRowSorter<CampingTableModel> sorter = new TableRowSorter<CampingTableModel>(
		bodyTable.getTableModel());
	initSorter(sorter, columns);
	clearRowFilterList(columns);

	sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
	bodyTable.setRowSorter(sorter);

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

			// TODO inputvalidation
			// (z.B.: geb. darf nur 24.02.1992 sein und sonst
			// nichts!)

			// Filter
			String val = headTable.getValueAt(0, column).toString();
			val = val.replaceAll("[Ää]", "[Ää]");
			val = val.replaceAll("[Öö]", "[Öö]");
			val = val.replaceAll("[Üü]", "[Üü]");
			final RowFilter<Object, Object> rowFilter = RowFilter.regexFilter(
				"(?i)^" + val + ".*$", column);
			rowFilterList.set(column, rowFilter);
			sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
			bodyTable.setRowSorter(sorter);

			headTable.editCellAt(0, column);
			// TODO set correct tab order!
		    }
		}
	    }

	    @Override
	    public void keyTyped(final KeyEvent arg0) {
	    }
	});

	// Add GUI elements
	final JButton butClear = new JButton("Clear");
	final JButton butSave = new JButton("Save");

	butClear.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Clear all inputs
		final TableCellEditor editor = headTable.getCellEditor();
		if (editor != null) {
		    editor.stopCellEditing();
		}
		headTable.removeAllDataAndInsertAnEmptyRow();

		// Reset Filter
		clearRowFilterList(columns);
		sorter.setRowFilter(RowFilter.andFilter(rowFilterList));
	    }
	});

	// Save data
	butSave.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		final HashMap<Integer, Object> values = headTable.getRowValues(0);
		delegate.getDelegator().editRow(columns, values);
	    }
	});

	// Configure scroll panes
	final JScrollPane headScrollPane = new JScrollPane(headTable);
	final JScrollPane bodyScrollPane = new JScrollPane(bodyTable);
	headScrollPane.setPreferredSize(new Dimension(getWidth(), defaultHeight));

	headScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	headScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	headScrollPane.getHorizontalScrollBar().setModel(
		bodyScrollPane.getHorizontalScrollBar().getModel());

	// Add components to GUI
	final JPanel panLeftFoot = new JPanel(new FlowLayout(FlowLayout.LEADING));
	panLeftFoot.add(new JLabel("Select:"));
	final JComboBox<String> combo = new JComboBox<>(sarchSubjects);
	combo.setSelectedIndex(comboIndex);
	combo.setPreferredSize(new Dimension(screenWidth / 10, defaultHeight));
	combo.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		delegate.getDelegator().subjectChangedTo(combo.getSelectedIndex());
		combo.setSelectedIndex(comboIndex);
	    }
	});
	panLeftFoot.add(combo);
	panLeftFoot.add(new JLabel("Filter:"));
	final JTextField textFilter = new JTextField();

	textFilter.setPreferredSize(new Dimension(screenWidth / 8, defaultHeight));
	panLeftFoot.add(textFilter);

	final JPanel panBut = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	panBut.add(butClear);
	panBut.add(butSave);

	final JPanel panFoot = new JPanel(new BorderLayout());
	panFoot.add(panLeftFoot, BorderLayout.WEST);
	panFoot.add(panBut, BorderLayout.EAST);

	setLayout(new BorderLayout());
	add(headScrollPane, BorderLayout.NORTH);
	add(bodyScrollPane);
	add(panFoot, BorderLayout.SOUTH);
    }

    Vector<RowFilter<Object, Object>> rowFilterList = null;
    private final Delegate<SearchTableListener> delegate = new Delegate<>(
	    SearchTableListener.class);
}
