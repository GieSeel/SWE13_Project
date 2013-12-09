package de.dhbw.swe.camping_site_mgt.common.neu;

import java.util.Date;

public class NEU_Guest {

	/**
	 * @param person_identification_number
	 * @param person_name
	 * @param person_first_name
	 * @param person_date_of_birth
	 * @param address_street
	 * @param address_house_number
	 * @param town_name
	 * @param town_postal_code
	 * @param country_name
	 * @param country_acronym
	 */
	public NEU_Guest(String person_identification_number, String person_name,
			String person_first_name, Date person_date_of_birth,
			String address_street, String address_house_number,
			String town_name, String town_postal_code, String country_name,
			String country_acronym) {
		this(0, person_identification_number, person_name, person_first_name,
				person_date_of_birth, address_street, address_house_number,
				town_name, town_postal_code, country_name, country_acronym);
	}

	/**
	 * @param id
	 * @param person_identification_number
	 * @param person_name
	 * @param person_first_name
	 * @param person_date_of_birth
	 * @param address_street
	 * @param address_house_number
	 * @param town_name
	 * @param town_postal_code
	 * @param country_name
	 * @param country_acronym
	 */
	public NEU_Guest(Integer id, String person_identification_number,
			String person_name, String person_first_name,
			Date person_date_of_birth, String address_street,
			String address_house_number, String town_name,
			String town_postal_code, String country_name, String country_acronym) {
		super();
		this.id = id;
		this.person_identification_number = person_identification_number;
		this.person_name = person_name;
		this.person_first_name = person_first_name;
		this.person_date_of_birth = person_date_of_birth;
		this.address_street = address_street;
		this.address_house_number = address_house_number;
		this.town_name = town_name;
		this.town_postal_code = town_postal_code;
		this.country_name = country_name;
		this.country_acronym = country_acronym;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		NEU_Guest guest = (NEU_Guest) obj;
		if (this.person_identification_number.equals(guest
						.getPerson_identification_number())
				&& this.person_name.equals(guest.person_name)
				&& this.person_first_name.equals(guest.person_first_name)
				&& this.person_date_of_birth.equals(guest.person_date_of_birth)
				&& this.address_street.equals(guest.address_street)
				&& this.address_house_number.equals(guest.address_house_number)
				&& this.town_name.equals(guest.town_name)
				&& this.town_postal_code.equals(guest.town_postal_code)
				&& this.country_name.equals(guest.country_name)
				&& this.country_acronym.equals(guest.country_acronym)) {
			return true;
		}
		return false;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the person_identification_number
	 */
	public String getPerson_identification_number() {
		return person_identification_number;
	}

	/**
	 * @return the person_name
	 */
	public String getPerson_name() {
		return person_name;
	}

	/**
	 * @return the person_first_name
	 */
	public String getPerson_first_name() {
		return person_first_name;
	}

	/**
	 * @return the person_date_of_birth
	 */
	public Date getPerson_date_of_birth() {
		return person_date_of_birth;
	}

	/**
	 * @return the address_street
	 */
	public String getAddress_street() {
		return address_street;
	}

	/**
	 * @return the address_house_number
	 */
	public String getAddress_house_number() {
		return address_house_number;
	}

	/**
	 * @return the town_name
	 */
	public String getTown_name() {
		return town_name;
	}

	/**
	 * @return the town_postal_code
	 */
	public String getTown_postal_code() {
		return town_postal_code;
	}

	/**
	 * @return the country_name
	 */
	public String getCountry_name() {
		return country_name;
	}

	/**
	 * @return the country_acronym
	 */
	public String getCountry_acronym() {
		return country_acronym;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private final String person_identification_number;
	private final String person_name;
	private final String person_first_name;
	private final Date person_date_of_birth;
	private final String address_street;
	private final String address_house_number;
	private final String town_name;
	private final String town_postal_code;
	private final String country_name;
	private final String country_acronym;
}
