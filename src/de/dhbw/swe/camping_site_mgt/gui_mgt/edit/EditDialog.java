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

import java.awt.event.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.gui_mgt.UniversalFormularPanel;

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
	final UniversalFormularPanel formularPanel = new UniversalFormularPanel();

	for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
	    formularPanel.add(column.getValue().getDisplayName(), new JTextField(
		    values.get(column.getKey()).toString()));
	}

	formularPanel.add(new JButton("Add"));
	final JButton cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
	    }
	});
	formularPanel.add(cancelBtn);

	add(formularPanel);
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }
}
