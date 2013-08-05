package de.dhbw.swe.campingplatzverwaltung.booking_mgt;

public class Equipment {

    public Equipment(final int id, final String identification, final String size,
	    final String type) {
	super();
	this.id = id;
	this.identification = identification;
	this.size = size;
	this.type = type;
    }

    public int getId() {
	return id;
    }

    public String getIdentification() {
	return identification;
    }

    public String getSize() {
	return size;
    }

    public String getType() {
	return type;
    }

    private final int id;
    private final String identification;
    private final String size;
    private final String type;
    // private final Equipment_Type type;
}
