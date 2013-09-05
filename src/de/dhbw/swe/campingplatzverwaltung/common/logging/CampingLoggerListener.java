package de.dhbw.swe.campingplatzverwaltung.common.logging;

/*** an interface for entities that need to be informed about logging messages. */
public interface CampingLoggerListener {
    /***
     * a msg was logged.* @param level* level of the message* @param msg* the
     * message itself* @param exception* a potentially raised exception (class
     * name)
     */
    void log(String level, String msg, String exception);
}
