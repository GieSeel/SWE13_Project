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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;
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

	final HashMap<Integer, JTextField> formMap = new HashMap<>();

	for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
	    final JTextField component = new JTextField(
		    values.get(column.getKey()).toString());
	    formMap.put(column.getKey(), component);
	    formularPanel.add(column.getValue().getDisplayName(), component);
	}

	final JButton addBtn = new JButton("Add");
	addBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(final ActionEvent e) {
		// TODO nicht new HashMap weil sonst IDs und so fehlen!
		final HashMap<Integer, Object> newValues = values;
		for (final Entry<Integer, JTextField> component : formMap.entrySet()) {
		    newValues.put(component.getKey(),
			    component.getValue().getText());
		}
		delegate.getDelegator().add(newValues);
		dispose();
	    }
	});
	formularPanel.add(addBtn);
	final JButton cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
	    }
	});
	formularPanel.add(cancelBtn);

	add(formularPanel);
	setMinimumSize(new Dimension((int) (Gui.screenSize.width * 0.25), 0));
	pack();
	setLocationRelativeTo(null);
	setAlwaysOnTop(true);
	setVisible(true);
    }

    /**
     * Register a {@link AddBaseDataObjectListener} at {@link Delegate}.
     * 
     * @param listener
     *            the {@link AddBaseDataObjectListener}
     */
    public void register(final AddBaseDataObjectListener listener) {
	delegate.register(listener);
    }

    /**
     * Unregister a {@link AddBaseDataObjectListener} from {@link Delegate}.
     * 
     * @param listener
     *            the {@link AddBaseDataObjectListener}
     */
    public void unregister(final AddBaseDataObjectListener listener) {
	delegate.unregister(listener);
    }

    private final Delegate<AddBaseDataObjectListener> delegate = new Delegate<>(
	    AddBaseDataObjectListener.class);
}
