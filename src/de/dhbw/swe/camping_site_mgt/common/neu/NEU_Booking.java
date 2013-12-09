package de.dhbw.swe.camping_site_mgt.common.neu;

import java.util.Date;

public class NEU_Booking {

	/**
	 * @param duration_from
	 * @param duration_until
	 * @param guest_id
	 * @param checked_in
	 * @param pitch_id
	 */
	public NEU_Booking(Date duration_from, Date duration_until,
			NEU_Guest guest, boolean checked_in, NEU_Pitch pitch) {
		this(0, duration_from, duration_until, guest, checked_in, pitch);
	}
	
	/**
	 * @param id
	 * @param duration_from
	 * @param duration_until
	 * @param guest_id
	 * @param checked_in
	 * @param pitch_id
	 */
	public NEU_Booking(Integer id, Date duration_from, Date duration_until,
			NEU_Guest guest, boolean checked_in, NEU_Pitch pitch) {
		super();
		this.id = id;
		this.duration_from = duration_from;
		this.duration_until = duration_until;
		this.guest = guest;
		this.checked_in = checked_in;
		this.pitch = pitch;
	}

	@Override
	public boolean equals(Object obj) {
		NEU_Booking booking = (NEU_Booking) obj;
		if (this.duration_from.equals(booking.getDuration_from())
				&& this.duration_until.equals(booking.getDuration_until())
				&& this.guest.equals(booking.getGuest())
				&& this.checked_in == booking.isChecked_in()
				&& this.pitch.equals(booking.getPitch())) {
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
	 * @return the duration_from
	 */
	public Date getDuration_from() {
		return duration_from;
	}

	/**
	 * @return the duration_until
	 */
	public Date getDuration_until() {
		return duration_until;
	}

	/**
	 * @return the guest
	 */
	public NEU_Guest getGuest() {
		return guest;
	}

	/**
	 * @return the checked_in
	 */
	public boolean isChecked_in() {
		return checked_in;
	}

	/**
	 * @return the pitch
	 */
	public NEU_Pitch getPitch() {
		return pitch;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private Date duration_from;
	private Date duration_until;
	private NEU_Guest guest;
	private boolean checked_in;
	private NEU_Pitch pitch;
}
