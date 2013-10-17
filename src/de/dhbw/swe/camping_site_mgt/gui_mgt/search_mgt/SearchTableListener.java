/**
 * Comments for file SearchTableListener.java
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
package de.dhbw.swe.camping_site_mgt.gui_mgt.search_mgt;

import java.util.HashMap;

import de.dhbw.swe.camping_site_mgt.common.database_mgt.ColumnInfo;

/**
 * The listener for the {@link SearchTable}.
 * 
 * @author GieSeel
 * @version 1.0
 */
public interface SearchTableListener {

    /**
     * Action for editing one double-clicked row.
     * 
     * @param columns
     *            the columns
     * @param values
     *            the values of the single row
     */
    void editRow(HashMap<Integer, ColumnInfo> columns,
	    HashMap<Integer, Object> values);

    /**
     * Action for changing the search table.
     * 
     * @param index
     *            the index of the search subject
     */
    void subjectChangedTo(final int index);
}
