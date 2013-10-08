package de.dhbw.swe.camping_site_mgt.common;

/**
 * Insert description for Country
 * 
 * @author GieSeel
 * @version 1.0
 */
public class Country {

    /**
     * Constructor for empty object.
     * 
     */
    public Country() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param acronym
     * @param name
     */
    public Country(final int id, final String acronym, final String name) {
	this.id = id;
	this.acronym = acronym;
	this.name = name;
    }

    /**
     * Constructor.
     * 
     * @param acronym
     * @param name
     */
    public Country(final String acronym, final String name) {
	this(0, acronym, name);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Country object = (Country) obj;
	if (this.acronym.equals(object.getAcronym())
		&& this.name.equals(object.getName())) {
	    setId(object.getId());
	    return true;
	}
	return false;
    }

    /**
     * Returns the acronym.
     * 
     * @return the acronym
     */
    public String getAcronym() {
	return acronym;
    }

    /**
     * Returns the id.
     * 
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
	this.id = id;
    }

    private final String acronym;
    private int id;
    private final String name;

    // public HashMap<String, Object> getDatabaseData() {
    // final HashMap<String, Object> elements = new HashMap<String, Object>();
    // elements.put("id", this.id);
    // elements.put("acronym", this.acronym);
    // elements.put("name", this.name);
    // return elements;
    // }
    //
    // public HashMap<String, Object> getTableData(final String parentClass) {
    // final HashMap<String, Object> objects = new HashMap<String, Object>();
    // final String className = parentClass + "country_";
    // objects.put(className + "id", new Integer(this.id));
    // objects.put(className + "name", new String(this.name));
    // objects.put(className + "acronym", new String(this.acronym));
    // return objects;
    // }
    //
    // public Country setDatabaseData(final HashMap<String, Object> objects) {
    // setData(objects);
    // return this;
    // }
    //
    // public Country setTableData(final HashMap<String, Object> objects) {
    // final String className = "country_";
    // final int classNameLength = className.length();
    // final HashMap<String, Object> thisMap = new HashMap<String, Object>();
    //
    // Object val;
    // final Set<String> keys = objects.keySet();
    // for (String key : keys) {
    // val = objects.get(key);
    // key = key.substring(classNameLength);
    // thisMap.put(key, val);
    // }
    // setData(thisMap);
    // return this;
    // }
    //
    // private void setData(final HashMap<String, Object> objects) {
    // this.id = (int) objects.get("id");
    // this.acronym = (String) objects.get("acronym");
    // this.name = (String) objects.get("name");
    // }
}
