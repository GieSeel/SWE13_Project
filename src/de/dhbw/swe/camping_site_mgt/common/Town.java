package de.dhbw.swe.camping_site_mgt.common;

public class Town {

    /**
     * Constructor for empty object.
     * 
     */
    public Town() {
	this(null, null);
    }

    /**
     * Constructor.
     * 
     * @param id
     * @param name
     * @param postalCode
     */
    public Town(final int id, final String name, final String postalCode) {
	this.id = id;
	this.name = name;
	this.postalCode = postalCode;
    }

    /**
     * Constructor.
     * 
     * @param name
     * @param postalCode
     */
    public Town(final String name, final String postalCode) {
	this(0, name, postalCode);
    }

    /**
     * {@inheritDoc}.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
	final Town object = (Town) obj;
	if (this.name.equals(object.getName())
		&& this.postalCode.equals(object.getPostalCode())) {
	    setId(object.getId());
	    return true;
	}
	return false;
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
     * Returns the postalCode.
     * 
     * @return the postalCode
     */
    public String getPostalCode() {
	return postalCode;
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

    private int id;
    private final String name;
    private final String postalCode;

    // ############################################################
    // public HashMap<String, Object> getDatabaseData() {
    // final HashMap<String, Object> elements = new HashMap<String, Object>();
    // elements.put("id", this.id);
    // elements.put(
    // "country_ID",
    // DatabaseController.getInstance().queryInsertUpdateCountry(
    // this.country));
    // elements.put("name", this.name);
    // elements.put("postalCode", this.postalCode);
    // return elements;
    // }
    //
    // public HashMap<String, Object> getTableData(final String parentClass) {
    // final HashMap<String, Object> objects = new HashMap<String, Object>();
    // final String className = parentClass + "town_";
    // objects.put(className + "id", new Integer(this.id));
    // objects.put(className + "postalCode", new String(this.postalCode));
    // objects.put(className + "name", new String(this.name));
    // objects.putAll(this.country.getTableData(className));
    // return objects;
    // }
    //
    // public Town setDatabaseData(final HashMap<String, Object> objects) {
    // final DatabaseController db = DatabaseController.getInstance();
    // this.country = db.querySelectCountry((int) objects.get("country_ID"));
    // setData(objects);
    // return this;
    // }
    //
    // public Town setTableData(final HashMap<String, Object> objects) {
    // final String className = "town_";
    // final int classNameLength = className.length();
    // final HashMap<String, Object> thisMap = new HashMap<String, Object>(),
    // countryMap = new HashMap<String, Object>();
    //
    // Object val;
    // final Set<String> keys = objects.keySet();
    // for (String key : keys) {
    // val = objects.get(key);
    // key = key.substring(classNameLength);
    // if (key.startsWith("country_")) {
    // countryMap.put(key, val);
    // } else {
    // thisMap.put(key, val);
    // }
    // }
    // this.country = new Country().setTableData(countryMap);
    // setData(thisMap);
    // return this;
    // }
    //
    // private void setData(final HashMap<String, Object> objects) {
    // this.id = (int) objects.get("id");
    // this.name = (String) objects.get("name");
    // this.postalCode = (String) objects.get("postalCode");
    //
    // }
    // ############################################################
}
