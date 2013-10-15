/**
 * Comments for file EditDialog.java
 *
 * @author   GieSeel
 *
 * Project:    Campingplatz Verwaltung
 * Company:    GieSeel
 * $Revision:  $
 *
 * Unclassified
 *
 * Copyright © since 2013 - Pforzheim - GieSeel GmbH
 * All rights especially the right for copying and distribution as
 * well as translation reserved.
 * No part of the product shall be reproduced or stored processed
 * copied or distributed with electronic tools or by paper copy or 
 * any other process without written authorization of GieSeel.
 */
package de.dhbw.swe.camping_site_mgt.gui_mgt.edit;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.gui_mgt.BaseFormularPanel;

/**
 * A frame to edit object data.
 * 
 * @author GieSeel
 * @version 1.0
 */
public class EditDialog extends JFrame {

    /**   */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param columns
     *            the columns
     * @param values
     *            the column values
     */
    public EditDialog(final HashMap<Integer, ColumnInfo> columns,
	    final HashMap<Integer, Object> values) {
	final BaseFormularPanel baseFormularPanel = new BaseFormularPanel();
	int i = 0;
	for (final ColumnInfo column : columns.values()) {
	    baseFormularPanel.add(column.getDisplayName(), new JTextField(
		    values.get(i).toString()));
	    i++;
	}
	add(baseFormularPanel);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

}
