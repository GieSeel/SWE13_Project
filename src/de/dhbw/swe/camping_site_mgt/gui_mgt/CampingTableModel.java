package de.dhbw.swe.camping_site_mgt.gui_mgt;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class CampingTableModel extends AbstractTableModel {

    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     */
    public CampingTableModel(final List<String[]> columnNames) {
	super();
	this.columnNames = columnNames;
	this.objects = new Vector<HashMap<String, Object>>();
	this.editable = true;
    }

    public CampingTableModel(final List<String[]> columnNames,
	    final List<HashMap<String, Object>> objects) {
	super();
	this.columnNames = columnNames;
	this.objects = new Vector<HashMap<String, Object>>();
	this.objects = objects;
	this.editable = false;
    }

    @Override
    public Class<? extends Object> getColumnClass(final int col) {
	return getValueAt(0, col).getClass();
    }

    @Override
    public int getColumnCount() {
	return this.columnNames.size();
    }

    @Override
    public String getColumnName(final int col) {
	return this.columnNames.get(col)[1];
    }

    /**
     * Returns the wanted row.
     * 
     * @param row
     *            is the row
     * @return
     */
    public HashMap<String, Object> getRow(final int row) {
	return this.objects.get(row);
    }

    @Override
    public int getRowCount() {
	return objects.size();
    }

    @Override
    public Object getValueAt(final int row, final int col) {
	return getValueAt(row, this.columnNames.get(col)[0]);
    }

    /**
     * Get cell by row and column-name.
     * 
     * @param row
     *            is the row
     * @param col
     *            is the column name
     * @return
     */
    public Object getValueAt(final int row, final String col) {
	return this.objects.get(row).get(col);
    }

    /**
     * Inserts an object.
     * 
     * @param object
     *            is the object
     */
    public void insertData(final HashMap<String, Object> object) {
	this.objects.add(new HashMap<String, Object>(object));
	fireTableDataChanged();
    }

    public void insertEmptyRow() {
	final HashMap<String, Object> tmpMap = new HashMap<String, Object>();
	for (final String[] column : this.columnNames) {
	    tmpMap.put(column[0], "");
	}
	this.objects.add(tmpMap);
    }

    @Override
    public boolean isCellEditable(final int row, final int col) {
	return this.editable;
    }

    /**
     * Removes all data.
     */
    public void removeAll() {
	this.objects = new Vector<HashMap<String, Object>>();
	fireTableDataChanged();
    }

    /**
     * Removes one row.
     * 
     * @param row
     *            is the row number
     */
    public void removeRow(final int row) {
	this.objects.remove(row);
	fireTableDataChanged();
    }

    /**
     * Sets all data.
     * 
     * @param objects
     *            are all data objects
     */
    public void setData(final List<HashMap<String, Object>> objects) {
	this.objects = objects;
    }

    /**
     * Sets if the cells are editable or not
     * 
     * @param val
     */
    public void setEditable(final boolean val) {
	this.editable = val;
    }

    @Override
    public void setValueAt(final Object value, final int row, final int col) {
	setValueAt(value, row, col, this.columnNames.get(col)[0]);
    }

    /**
     * Set cell value by row and column-name.
     * 
     * @param value
     *            is the value
     * @param row
     *            is the row
     * @param col
     *            is the column number (is needed for the fire-event)
     * @param colStr
     *            is the column name
     */
    public void setValueAt(final Object value, final int row, final int col,
	    final String colStr) {
	this.objects.get(row).put(colStr, value);
	fireTableCellUpdated(row, col);
    }

    private final List<String[]> columnNames; // 0: column key | 1: column name
    private boolean editable;
    private List<HashMap<String, Object>> objects;
}