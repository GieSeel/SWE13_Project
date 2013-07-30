package de.dhbw.swe.campingplatzverwaltung.place_mgt;

public class Pitch {
    public enum NatureOfSoil {
	GRASS, GRAVEL, SAND, SOIL
    };

    public enum PitchType {
	CAMPERPITCH, CARAVANPITCH, PARKINGPLACE, TENTPITCH
    };

    private String characteristics;
    private Site deliveryPoint;
    private String district;
    private int length;
    private int pitchNumber;
    private int width;
}
