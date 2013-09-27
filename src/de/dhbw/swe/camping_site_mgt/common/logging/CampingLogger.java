package de.dhbw.swe.camping_site_mgt.common.logging;

import java.io.File;
import java.util.LinkedHashSet;

import org.apache.log4j.*;
import org.apache.log4j.helpers.FileWatchdog;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Wrapper for log4j.
 */
public final class CampingLogger {

    /** all my listeners. */
    private static final LinkedHashSet<CampingLoggerListener> LOG_LISTENERS = new LinkedHashSet<CampingLoggerListener>();

    /** Default log file name. */
    private static final String LOGFILE_NAME = "log4j.xml";

    static {
	final long delay = FileWatchdog.DEFAULT_DELAY;

	if (new File(LOGFILE_NAME).exists()) {
	    DOMConfigurator.configureAndWatch(LOGFILE_NAME, delay);
	} else {
	    final PatternLayout pl = new PatternLayout(
		    "%d %-5p ==> %m  \t\t\t\t [class: %c{1}]%n");
	    final ConsoleAppender appender = new ConsoleAppender(pl);
	    appender.setThreshold(Level.INFO);
	    BasicConfigurator.configure(appender);
	}
    }

    /**
     * Gets a logger object with the specified cathegory.
     * 
     * @param cathegory
     *            the cathegory
     * @return the logger object.
     */
    public static CampingLogger getLogger(final Class<?> cathegory) {
	return getLogger(cathegory, FileWatchdog.DEFAULT_DELAY);
    }

    /**
     * Gets a logger object with the specified cathegory and updateDelay.
     * 
     * @param cathegory
     *            the cathegory.
     * @param configUpdateDelay
     *            the update delay.
     * @return the logger object.
     */
    public static CampingLogger getLogger(final Class<?> cathegory,
	    final long configUpdateDelay) {
	return getLogger(cathegory.getName(), configUpdateDelay);
    }

    /**
     * Gets a logger object with the specified cathegory.
     * 
     * @param cathegory
     *            the cathegory
     * @return the logger object.
     */
    public static CampingLogger getLogger(final String cathegory) {
	return getLogger(cathegory, FileWatchdog.DEFAULT_DELAY);
    }

    /**
     * Gets a logger object with the specified cathegory and updateDelay.
     * 
     * @param cathegory
     *            the cathegory.
     * @param configUpdateDelay
     *            the update delay.
     * @return the logger object.
     */
    public static CampingLogger getLogger(final String cathegory,
	    final long configUpdateDelay) {
	return new CampingLogger(cathegory, configUpdateDelay);
    }

    /**
     * register a {@link CampingLoggerListener}.
     * 
     * @param listener
     *            listener to register
     */
    public static void registerListener(final CampingLoggerListener listener) {
	LOG_LISTENERS.add(listener);
    }

    /**
     * unregister a {@link CampingLoggerListener}.
     * 
     * @param listener
     *            listener to unregister
     */
    public static void unregisterListener(final CampingLoggerListener listener) {
	LOG_LISTENERS.remove(listener);
    }

    /**
     * Gets a logger object with the specified cathegory and updateDelay.
     * 
     * @param cathegory
     *            the cathegory.
     * @param configUpdateDelay
     *            the update delay.
     */
    private CampingLogger(final String cathegory, final long configUpdateDelay) {
	logger = Logger.getLogger(cathegory);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message.
     */
    public void debug(final String message) {
	commonLog(Priority.DEBUG_INT, message, null, null);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void debug(final String message, final Object arg) {
	commonLog(Priority.DEBUG_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void debug(final String message, final Object arg, final Throwable t) {
	commonLog(Priority.DEBUG_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void debug(final String message, final Object[] args) {
	commonLog(Priority.DEBUG_INT, message, args, null);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void debug(final String message, final Object[] args, final Throwable t) {
	commonLog(Priority.DEBUG_INT, message, args, t);
    }

    /**
     * Writes a debug message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void debug(final String message, final Throwable t) {
	commonLog(Priority.DEBUG_INT, message, null, t);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     */
    public void error(final String message) {
	commonLog(Priority.ERROR_INT, message, null, null);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void error(final String message, final Object arg) {
	commonLog(Priority.ERROR_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void error(final String message, final Object arg, final Throwable t) {
	commonLog(Priority.ERROR_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void error(final String message, final Object[] args) {
	commonLog(Priority.ERROR_INT, message, args, null);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void error(final String message, final Object[] args, final Throwable t) {
	commonLog(Priority.ERROR_INT, message, args, t);
    }

    /**
     * Writes an error message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void error(final String message, final Throwable t) {
	commonLog(Priority.ERROR_INT, message, null, t);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     */
    public void fatal(final String message) {
	commonLog(Priority.FATAL_INT, message, null, null);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void fatal(final String message, final Object arg) {
	commonLog(Priority.FATAL_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void fatal(final String message, final Object arg, final Throwable t) {
	commonLog(Priority.FATAL_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void fatal(final String message, final Object[] args) {
	commonLog(Priority.FATAL_INT, message, args, null);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void fatal(final String message, final Object[] args, final Throwable t) {
	commonLog(Priority.FATAL_INT, message, args, t);
    }

    /**
     * Writes a fatal message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void fatal(final String message, final Throwable t) {
	commonLog(Priority.FATAL_INT, message, null, t);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     */
    public void info(final String message) {
	commonLog(Priority.INFO_INT, message, null, null);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void info(final String message, final Object arg) {
	commonLog(Priority.INFO_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void info(final String message, final Object arg, final Throwable t) {
	commonLog(Priority.INFO_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void info(final String message, final Object[] args) {
	commonLog(Priority.INFO_INT, message, args, null);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void info(final String message, final Object[] args, final Throwable t) {
	commonLog(Priority.INFO_INT, message, args, t);
    }

    /**
     * Writes an info message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void info(final String message, final Throwable t) {
	commonLog(Priority.INFO_INT, message, null, t);
    }

    /**
     * Checks, if logging is enabled for the debug priority.
     * 
     * @return true, if enabled
     */
    public boolean isDebugEnabled() {
	return logger.isDebugEnabled();
    }

    /**
     * Checks, if logging is enabled for the specified priority.
     * 
     * @param p
     *            priority
     * @return true, if enabled
     */
    public boolean isEnabledFor(final Priority p) {
	return logger.isEnabledFor(p);
    }

    /**
     * Checks, if logging is enabled for the info priority.
     * 
     * @return true, if enabled
     */
    public boolean isInfoEnabled() {
	return logger.isInfoEnabled();
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     */
    public void trace(final String message) {
	commonLog(Level.TRACE_INT, message, null, null);
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void trace(final String message, final Object arg) {
	commonLog(Level.TRACE_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void trace(final String message, final Object arg, final Throwable t) {
	commonLog(Level.TRACE_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void trace(final String message, final Object[] args) {
	commonLog(Level.TRACE_INT, message, args, null);
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void trace(final String message, final Object[] args, final Throwable t) {
	commonLog(Level.TRACE_INT, message, args, t);
    }

    /**
     * Writes a trace message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void trace(final String message, final Throwable t) {
	commonLog(Level.TRACE_INT, message, null, null);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     */
    public void warn(final String message) {
	commonLog(Priority.WARN_INT, message, null, null);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     */
    public void warn(final String message, final Object arg) {
	commonLog(Priority.WARN_INT, message, new Object[] { arg }, null);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     * @param arg
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void warn(final String message, final Object arg, final Throwable t) {
	commonLog(Priority.WARN_INT, message, new Object[] { arg }, t);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     */
    public void warn(final String message, final Object[] args) {
	commonLog(Priority.WARN_INT, message, args, null);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    public void warn(final String message, final Object[] args, final Throwable t) {
	commonLog(Priority.WARN_INT, message, args, t);
    }

    /**
     * Writes a warning message.
     * 
     * @param message
     *            the message
     * @param t
     *            the raised exception
     */
    public void warn(final String message, final Throwable t) {
	commonLog(Priority.WARN_INT, message, null, t);
    }

    /**
     * Writes a log message with the specified level and input.
     * 
     * @param level
     *            the log level
     * @param message
     *            the message
     * @param args
     *            the arguments
     * @param t
     *            the raised exception
     */
    private void commonLog(final int level, final String message,
	    final Object[] args, final Throwable t) {
	final Level l = Level.toLevel(level);

	for (final CampingLoggerListener listener : LOG_LISTENERS) {
	    final String levelName = l.toString();
	    final String throwableName = (t != null) ? t.getClass().getName() : "";
	    listener.log(levelName, message, throwableName);
	}

	if (logger.isEnabledFor(l)) {
	    if ((args == null) || (args.length == 0)) {
		logger.log(l, message, t);
	    } else {
		String logMessage = message;
		for (int i = 0; i < args.length; i++) {
		    final String placeHolder = "{" + i + '}';
		    logMessage = logMessage.replace(placeHolder, (args[i] != null)
			    ? args[i].toString() : "NULL");
		}
		logger.log(l, logMessage, t);
	    }
	}
    }

    /** The log4j logger. */
    private final Logger logger;
}
