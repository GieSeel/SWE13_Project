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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import de.dhbw.swe.camping_site_mgt.common.Delegate;
import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_DatabaseConnector;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_Guest;
import de.dhbw.swe.camping_site_mgt.common.neu.NEU_Pitch;
import de.dhbw.swe.camping_site_mgt.gui_mgt.Gui;
import de.dhbw.swe.camping_site_mgt.gui_mgt.UniversalFormularPanel;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_NatureOfSoil;
import de.dhbw.swe.camping_site_mgt.place_mgt.Pitch_Type;

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

		final HashMap<Integer, JComponent> formMap = new HashMap<>();
		for (final Entry<Integer, ColumnInfo> column : columns.entrySet()) {
			JComponent component = null;
			if (columns.get(0).getDbName().equals("booking")) {
				if (column.getKey() == 2) {
					// Guests
					NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
							.getInstance();
					Vector<String> guestList = new Vector<>();
					for (NEU_Guest guest : NEU_dbConnector.getGuests()) {
						guestList.add(guest.getPerson_first_name() + " "
								+ guest.getPerson_name());
					}
					JComboBox<String> combo = new JComboBox<>(guestList);

					if (values.get(column.getKey()) != null
							&& values.get(column.getKey()) != "") {
						combo.setSelectedIndex((int) values.get(column.getKey()));
					}
					component = combo;
				} else if (column.getKey() == 3) {
					// Checked in
					JCheckBox check = new JCheckBox();

					if (values.get(column.getKey()) != null
							&& values.get(column.getKey()) != "") {
						check.setSelected((boolean) values.get(column.getKey()));
					}
					component = check;
				} else if (column.getKey() == 4) {
					// Pitches
					NEU_DatabaseConnector NEU_dbConnector = NEU_DatabaseConnector
							.getInstance();
					Vector<String> pitchList = new Vector<>();
					for (NEU_Pitch pitch : NEU_dbConnector.getPitches()) {
						pitchList.add(pitch.getName());
					}
					JComboBox<String> combo = new JComboBox<>(pitchList);

					if (values.get(column.getKey()) != null
							&& values.get(column.getKey()) != "") {
						combo.setSelectedIndex((int) values.get(column.getKey()));
					}
					component = combo;
				}
			} else if (columns.get(0).getDbName().equals("pitch")) {
				if (column.getKey() == 2) {
					Vector<String> typeList = new Vector<>();
					for (Pitch_Type typ : Pitch_Type.values()) {
						typeList.add(typ.toString());
					}
					JComboBox<String> combo = new JComboBox<>(typeList);

					if (values.get(column.getKey()) != null
							&& values.get(column.getKey()) != "") {
						combo.setSelectedIndex((int) values.get(column.getKey()));
					}
					component = combo;
				} else if (column.getKey() == 5) {
					Vector<String> natureOfSoilList = new Vector<>();
					for (Pitch_NatureOfSoil natureOfSoil : Pitch_NatureOfSoil
							.values()) {
						natureOfSoilList.add(natureOfSoil.toString());
					}
					JComboBox<String> combo = new JComboBox<>(natureOfSoilList);

					if (values.get(column.getKey()) != null
							&& values.get(column.getKey()) != "") {
						combo.setSelectedIndex((int) values.get(column.getKey()));
					}
					component = combo;
				}
			}
			if (component == null) {
				component = new JTextField(values.get(column.getKey())
						.toString());
				if (columns.get(0).getDbName().equals("guest")
						&& column.getKey() == 3
						|| (columns.get(0).getDbName().equals("booking") && (column
								.getKey() == 0 || column.getKey() == 1))) {
					component.setToolTipText("yyyy/mm/dd (e.g. 2013/12/24)");
				}
				if (columns.get(0).getDbName().equals("pitch")
						&& (column
								.getKey() == 7 || column.getKey() == 8)) {
					component.setToolTipText("a, b, c, d (e.g. 15, 150, 20, 30)");
				}
			}

			formMap.put(column.getKey(), component);
			formularPanel.add(column.getValue().getDisplayName(), component);
		}

		final JButton addBtn = new JButton("Save");
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				// TODO nicht new HashMap weil sonst IDs und so fehlen!
				final HashMap<Integer, Object> newValues = values;
				for (final Entry<Integer, JComponent> component : formMap
						.entrySet()) {
					if (component.getValue() instanceof JComboBox) {
						newValues.put(component.getKey(),
								((JComboBox<String>) component.getValue())
										.getSelectedIndex());
					} else if (component.getValue() instanceof JCheckBox) {
						newValues.put(component.getKey(),
								((JCheckBox) component.getValue()).isSelected());
					} else {
						newValues.put(component.getKey(),
								((JTextField) component.getValue()).getText());
					}
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
