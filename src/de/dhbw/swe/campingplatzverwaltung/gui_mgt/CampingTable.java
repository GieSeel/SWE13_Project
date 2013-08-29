package de.dhbw.swe.campingplatzverwaltung.gui_mgt;

import java.text.*;
import java.util.*;

import javax.swing.JTable;

public class CampingTable extends JTable {
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param columnNames
     */
    public CampingTable(final List<String[]> columnObjects) {
	super();
	this.columnObjects = columnObjects;
	this.tableModel = new CampingTableModel(getColumnNames());
	init();
    }

    /**
     * Constructor.
     * 
     * @param columnNames
     * @param objects
     */
    public CampingTable(final List<String[]> columnObjects,
	    final List<HashMap<String, Object>> objects) {
	super();
	this.columnObjects = columnObjects;
	this.tableModel = new CampingTableModel(getColumnNames(), objects);
	init();
    }

    /**
     * Returns the input values.
     * 
     * @return
     */
    public HashMap<String, Object> getInputValues() {
	return this.tableModel.getRow(convertRowIndexToModel(0));
    }

    /**
     * Returns the values of the selected row.
     * 
     * @param row
     *            is the row
     * @return
     */
    public HashMap<String, Object[]> getRowValues(final int row) {
	final HashMap<String, Object[]> tmpMap = new HashMap<String, Object[]>();
	final HashMap<String, Object> tmpRow = this.tableModel.getRow(convertRowIndexToModel(row));

	for (final String[] columnObject : columnObjects) {
	    final Object[] tmpElement = new Object[2];
	    final String type = columnObject[2];

	    if (type.equals("int")) {
		tmpElement[0] = new Integer(tmpRow.get(columnObject[0]).toString());
	    } else if (type.equals("float")) {
		tmpElement[0] = new Float(tmpRow.get(columnObject[0]).toString());
	    } else if (type.equals("datum")) {
		try {
		    tmpElement[0] = new SimpleDateFormat("dd.MM.yyyy").parse(tmpRow.get(
			    columnObject[0]).toString());
		} catch (final ParseException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    } else if (type.equals("string")) {
		tmpElement[0] = new String(tmpRow.get(columnObject[0]).toString());
	    } else {
		// TODO throw Unerwarteter Typ!
	    }
	    tmpElement[1] = type;
	    tmpMap.put(columnObject[0], tmpElement);
	}
	return tmpMap;
    }

    /**
     * Returns the {@link CampingTableModel}
     * 
     * @return
     */
    public CampingTableModel getTableModel() {
	return this.tableModel;

    }

    @Override
    public Object getValueAt(final int row, final int col) {
	// convertRowIndexToModel() is needed for the correct sorting order
	return this.tableModel.getValueAt(convertRowIndexToModel(row), col);
    }

    /**
     * Inserts an object.
     * 
     * @param object
     *            is the object
     */
    public void insertData(final HashMap<String, Object> object) {
	this.tableModel.insertData(object);
    }

    /**
     * Inserts an object.
     * 
     * @param object
     *            is the object
     */
    public void insertDataObject(final HashMap<String, Object[]> object) {
	final HashMap<String, Object> tmpMap = new HashMap<String, Object>();
	final Set<String> keys = object.keySet();
	for (final String key : keys) {
	    tmpMap.put(key, object.get(key)[0]);
	}
	insertData(tmpMap);
    }

    /**
     * Removes all data and inserts an empty row.
     */
    public void removeAllDataAndInsertAnEmptyRow() {
	this.tableModel.removeAll();
	this.tableModel.insertEmptyRow();
    }

    /**
     * Some table settings for body table.
     */
    public void setBodyTableSettings() {
	setAutoCreateRowSorter(true);
	// getTableHeader().setVisible(false);
	// setTableHeader(null); // -- macht resize column kaputt
    }

    /**
     * Some table settings for header table.
     */
    public void setHeadTableSettings() {
	setRowSelectionAllowed(false);
	// this.tableModel.insertEmptyRow();
    }

    /**
     * Filters the column names from the column objects
     * 
     * @return
     */
    private List<String[]> getColumnNames() {
	final List<String[]> columnNames = new Vector<String[]>();
	for (final String[] columnObject : columnObjects) {
	    final String[] tmpColumnName = { columnObject[0], columnObject[1] };
	    columnNames.add(tmpColumnName);
	}
	return columnNames;
    }

    /**
     * Initializes the table.
     */
    private void init() {
	setModel(this.tableModel);
	// Some table settings
	getTableHeader().setReorderingAllowed(false);
	setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    List<String[]> columnObjects; // 0: key | 1: name | 2: type | 3: validation
    private final CampingTableModel tableModel;
}
