package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class Site {
    private enum SiteType {
	ACTIVITY, ELECTRICALPOWER, ENTRANCE, FIREPLACE, OTHER, RECEPTION, SALE, SANITARYFACILLITIES, SERVICE, UTILLITYROOM
    };

    private String description;
    private String labeling;
    private int number;
    private String openingHours;
}
