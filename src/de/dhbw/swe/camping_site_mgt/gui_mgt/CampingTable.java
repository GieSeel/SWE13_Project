package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;

/**
 * Insert description for CampingTable
 * 
 * @author GieSeel
 * @version 1.0
 */
public class CampingTable extends JTable {
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param columns
     */
    public CampingTable(final HashMap<String, ColumnInfo> columns) {
	this(columns, new Vector<HashMap<String, Object>>());
    }

    /**
     * Constructor.
     * 
     * @param columns
     * @param data
     */
    public CampingTable(final HashMap<String, ColumnInfo> columns,
	    final Vector<HashMap<String, Object>> data) {
	super();
	// this.columns = columns; // TODO del?
	this.tableModel = new CampingTableModel(columns, data);
	init();
    }

    /**
     * Gets the inputed values.
     * 
     * @return the object that was typed in
     */
    public HashMap<String, Object> getInputValues() {
	return tableModel.getRow(convertRowIndexToModel(0));
    }

    /**
     * Gets the values of the selected row.
     * 
     * @param row
     *            is the row
     * @return the selected row
     */
    public HashMap<String, Object> getRowValues(final int row) {
	return tableModel.getRow(convertRowIndexToModel(row));
    }

    /**
     * Gets the {@link CampingTableModel}.
     * 
     * @return the {@link CampingTableModel}
     */
    public CampingTableModel getTableModel() {
	return this.tableModel;

    }

    @Override
    public Object getValueAt(final int row, final int col) {
	// convertRowIndexToModel() is needed for the correct sorting order
	return tableModel.getValueAt(convertRowIndexToModel(row), col);
    }

    /**
     * Inserts an object.
     * 
     * @param data
     *            the object
     */
    public void insertData(final HashMap<String, Object> data) {
	tableModel.insertData(data);
    }

    /**
     * Removes all data and inserts an empty row.
     */
    public void removeAllDataAndInsertAnEmptyRow() {
	tableModel.removeAll();
	tableModel.insertEmptyRow();
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
	tableModel.setEditable(true);
	tableModel.insertEmptyRow();
    }

    /**
     * Initializes the table.
     */
    private void init() {
	setModel(tableModel);
	// Some table settings
	getTableHeader().setReorderingAllowed(false);
	setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    // private HashMap<String, ColumnInfo> columns; // TODO del?
    private final CampingTableModel tableModel;
}
