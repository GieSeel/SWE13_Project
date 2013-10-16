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

public class SearchPanel extends JPanel {

    private static CampingLogger logger = CampingLogger.getLogger(SearchPanel.class);

    /**   */
    private static final long serialVersionUID = 1L;

    public SearchPanel() {
	rowFilterList = new Vector<>();
	sorter = new TableRowSorter<>();
	columns = new HashMap<>();

	// TODO autovervollständigung (evtl. erst wenn nur noch ein eintrag
	// gefiltert ist)
    }

    /**
     * Makes the tables and fills them with data.
     * 
     * @param columns
     *            the columns that will show
     * @param data
     *            the data for the table
     */
    public void makeTables(final HashMap<Integer, ColumnInfo> columns,
	    final Vector<HashMap<Integer, Object>> data) {

	this.columns = columns;

	final DefaultTableColumnModel dtColumnModel = new DefaultTableColumnModel();
	final CampingTable headTable = new CampingTable(new CampingTableModel(
		columns), dtColumnModel);
	final CampingTable bodyTable = new CampingTable(new CampingTableModel(
		columns), dtColumnModel);
	headTable.setHeadTableSettings();
	bodyTable.setBodyTableSettings();

	headTable.insertEmptyRow();
	bodyTable.insertData(data);

	sorter = new TableRowSorter<CampingTableModel>(bodyTable.getTableModel());

	initRowFilterList();
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
	final JButton but_clear = new JButton("Clear");
	final JButton but_save = new JButton("Save");

	but_clear.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		// Clear all inputs
		final TableCellEditor editor = headTable.getCellEditor();
		if (editor != null) {
		    editor.stopCellEditing();
		}
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

		final HashMap<Integer, Object> values = headTable.getRowValues(0);
		delegate.getDelegator().editRow(columns, values);
	    }
	});
	final JPanel buttons = new JPanel(new BorderLayout());
	buttons.add(but_clear, BorderLayout.WEST);
	buttons.add(but_save, BorderLayout.EAST);

	final JScrollPane headScrollPane = new JScrollPane(headTable);
	final JScrollPane bodyScrollPane = new JScrollPane(bodyTable);
	headScrollPane.setMinimumSize(new Dimension(getWidth(), 18));

	headScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	headScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	headScrollPane.getHorizontalScrollBar().setModel(
		bodyScrollPane.getHorizontalScrollBar().getModel());

	final GridBagLayout gbl = new GridBagLayout();
	final GridBagConstraints gbc = new GridBagConstraints();
	setLayout(gbl);

	gbc.fill = GridBagConstraints.BOTH;
	gbc.weightx = 0;
	gbc.weighty = 0;
	add(headScrollPane, gbc);

	gbc.weightx = 1;
	gbc.weighty = 1;
	gbc.gridx++;
	add(bodyScrollPane, gbc);

	// TODO buttons entweder oben oder unten!!
	gbc.weightx = 0;
	gbc.weighty = 0;
	gbc.gridx++;
	gbc.fill = GridBagConstraints.CENTER;
	add(buttons, gbc);
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

	int index;
	for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
	    index = column.getKey();

	    // Set empty filter for that column
	    rowFilterList.add(RowFilter.regexFilter("^.*$", index));

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

    private HashMap<Integer, ColumnInfo> columns;
    private final Delegate<SearchTableListener> delegate = new Delegate<>(
	    SearchTableListener.class);
    private Vector<RowFilter<Object, Object>> rowFilterList;
    private TableRowSorter<CampingTableModel> sorter;
}
