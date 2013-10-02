package de.dhbw.swe.camping_site_mgt.place_mgt;

import java.util.HashMap;

public interface PitchInterface {
    String getCharacteristics();

    HashMap<String, Object> getDatabaseData();

    Site getDeliveryPoint();

    String getDistrict();

    int getId();

    int getLength();

    String getNatureOfSoil();

    HashMap<String, Object> getTableData(final String parentClass);

    String getType();

    int getWidth();
}
