package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

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
     * @param tableModel
     * 
     * @param tableModel
     */
    public CampingTable(final CampingTableModel tableModel,
	    final DefaultTableColumnModel columnModel) {
	super(tableModel, columnModel);
	this.tableModel = tableModel;
	init();
    }

    /**
     * Gets the values of the selected row.
     * 
     * @param row
     *            is the row
     * @return the selected row
     */
    public HashMap<Integer, Object> getRowValues(final int row) {
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
    public void insertData(final HashMap<Integer, Object> data) {
	tableModel.insertData(data);
    }

    /**
     * Inserts objects.
     * 
     * @param data
     *            the object list
     */
    public void insertData(final Vector<HashMap<Integer, Object>> data) {
	tableModel.insertData(data);
    }

    /**
     * Inserts an empty row.
     * 
     * @param comboIndex
     */
    public void insertEmptyRow(final int comboIndex) {
	tableModel.insertEmptyRow(comboIndex);
    }

    @Override
    public void removeAll() {
	tableModel.removeAll();
    }

    /**
     * Some table settings for body table.
     */
    public void setBodyTableSettings() {
	setAutoCreateRowSorter(true);
    }

    /**
     * Some table settings for header table.
     */
    public void setHeadTableSettings() {
	setRowSelectionAllowed(false);
	tableModel.setEditable(true);
	setTableHeader(null);
    }

    /**
     * Initializes the table.
     */
    private void init() {
	setAutoCreateColumnsFromModel(true);

	// Some table settings
	getTableHeader().setReorderingAllowed(false);
	setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    // private HashMap<String, ColumnInfo> columns; // TODO del?
    private final CampingTableModel tableModel;
}
