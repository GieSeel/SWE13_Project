package de.dhbw.swe.camping_site_mgt.booking_mgt;

import java.util.Date;

import de.dhbw.swe.camping_site_mgt.person_mgt.*;

public class BookingMgr {
    /**
     * Creates a booking entry.
     * 
     * @param booking
     *            one {@link Booking} entry
     * @return
     */
    public boolean create(final Booking booking) {
	return false;
    }

    /**
     * Creates a booking entry.
     * 
     * @param responsiblePerson
     *            the responsible {@link Guest}
     * @param fellowTravelers
     *            the fellow guests ({@link GuestList})
     * @param from
     *            the arrival {@link Date}
     * @param until
     *            the checkout {@link Date}
     * @param equipment
     *            the whole {@link EquipmentList}
     * @param pitchBooking
     *            the whole {@link PitchBookingList}
     * @param extraBooking
     *            some {@link ExtraBooking}
     * @param bill
     *            the {@link Bill}
     * @param chipCard
     *            all chip cards ({@link ChipCardList})
     * @return
     */
    public boolean create(final Guest responsiblePerson,
	    final GuestList fellowTravelers, final Date from, final Date until,
	    final EquipmentList equipment, final PitchBookingList pitchBooking,
	    final ExtraBookingList extraBooking, final Bill bill,
	    final ChipCardList chipCard) {
	return false;
    }

    /**
     * Deletes a booking entry.
     * 
     * @param booking
     *            one {@link Booking} entry
     * @return
     */
    public boolean delete(final Booking booking) {
	return false;
    }

    /**
     * Deletes the booking entry with this number.
     * 
     * @param number
     *            the ID of the entry
     * @return
     */
    public boolean delete(final int number) {
	return false;
    }

    /**
     * Edits the entry with the number.
     * 
     * @param number
     *            the ID of the entry
     * 
     * @param responsiblePerson
     *            the responsible {@link Guest}
     * @param fellowTravelers
     *            the fellow guests ({@link GuestList})
     * @param from
     *            the arrival {@link Date}
     * @param until
     *            the checkout {@link Date}
     * @param equipment
     *            the whole {@link EquipmentList}
     * @param pitchBooking
     *            the whole {@link PitchBookingList}
     * @param extraBooking
     *            some {@link ExtraBooking}
     * @param bill
     *            the {@link Bill}
     * @param chipCard
     *            all chip cards ({@link ChipCardList})
     * @return
     */
    public boolean edit(final int number, final Guest responsiblePerson,
	    final GuestList fellowTravelers, final Date from, final Date until,
	    final EquipmentList equipment, final PitchBookingList pitchBooking,
	    final ExtraBookingList extraBooking, final Bill bill,
	    final ChipCardList chipCard) {
	return false;
    }

    /**
     * Save the whole data into a file.
     * 
     * @return
     */
    public boolean exportAll() {
	return false;
    }

    /**
     * Imports the whole data from a file.
     * 
     * @return
     */
    public boolean importAll() {
	return false;
    }

    /**
     * Prepares the bill data to print.
     * 
     * @param bill
     *            the {@link Bill} which will be prepared for printing
     * @return
     */
    public boolean print(final Bill bill) {
	return false;
    }

    /**
     * Prepares the booking data to print. TODO description?
     * 
     * @param booking
     *            is a {@link Booking} entry
     * @return
     */
    public boolean print(final Booking booking) {
	return false;
    }

    /**
     * Search for the booking number
     * 
     * @param number
     *            is the number of the {@link Booking} entry
     * @param from
     *            the arrival {@link Date}
     * @param until
     *            the checkout {@link Date}
     * 
     * @return
     */
    public Booking search(final int number, final Date from, final Date until) {
	return null;
    }

    private BookingList bookingList;
}
