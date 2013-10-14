package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;

public class CampingTableModel extends AbstractTableModel {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param columnNames
     * @param data
     */
    public CampingTableModel(final HashMap<String, ColumnInfo> columns,
	    final Vector<HashMap<String, Object>> data) {
	super();
	this.columns = new HashMap<>();
	this.columnKeys = (String[]) columns.keySet().toArray();
	// this.objects = new Vector<HashMap<String, Object>>(); // evtl. wenn
	// object nicht dirket geändert werden soll (auch bei unteren methoden)
	this.dataList = data;
	this.editable = false;
    }

    @Override
    public Class<? extends Object> getColumnClass(final int column) {
	return columns.get(columnKeys[column]).getDbType();
    }

    @Override
    public int getColumnCount() {
	return columns.size();
    }

    @Override
    public String getColumnName(final int column) {
	return columns.get(getColumnKey(column)).getDisplayName();
    }

    /**
     * Gets a row.
     * 
     * @param row
     *            the row number
     * @return a data row
     */
    public HashMap<String, Object> getRow(final int row) {
	return this.dataList.get(row);
    }

    @Override
    public int getRowCount() {
	return dataList.size();
    }

    @Override
    public Object getValueAt(final int row, final int column) {
	return dataList.get(row).get(getColumnKey(column));
    }

    /**
     * Inserts an object.
     * 
     * @param data
     *            the object
     */
    public void insertData(final HashMap<String, Object> data) {
	dataList.add(data);
	fireTableDataChanged();
    }

    /**
     * Inserts an empty row.
     */
    public void insertEmptyRow() {
	final HashMap<String, Object> newData = new HashMap<>();
	for (final String columnKey : columnKeys) {
	    newData.put(columnKey, "");
	}
	insertData(newData);
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
	return editable;
    }

    /**
     * Removes all data.
     */
    public void removeAll() {
	dataList = new Vector<>();
	fireTableDataChanged();
    }

    /**
     * Removes one row.
     * 
     * @param row
     *            the row number
     */
    public void removeRow(final int row) {
	this.dataList.remove(row);
	fireTableDataChanged();
    }

    /**
     * Sets all data.
     * 
     * @param data
     *            are all data objects
     */
    public void setData(final Vector<HashMap<String, Object>> data) {
	dataList = data;
    }

    /**
     * Sets if the cells are editable or not
     * 
     * @param val
     *            true if cells should be editable
     */
    public void setEditable(final boolean val) {
	editable = val;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int column) {
	getRow(row).put(getColumnKey(column), value);
    }

    /**
     * Gets key of the column.
     * 
     * @param column
     *            the column number
     * @return the key of the wanted column
     */
    private String getColumnKey(final int column) {
	return columnKeys[column];
    }

    private final String[] columnKeys;
    private final HashMap<String, ColumnInfo> columns;
    private Vector<HashMap<String, Object>> dataList;
    private boolean editable;
}