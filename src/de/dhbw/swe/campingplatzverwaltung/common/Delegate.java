package de.dhbw.swe.campingplatzverwaltung.common;

import java.lang.reflect.*;
import java.util.*;

import de.dhbw.swe.campingplatzverwaltung.common.logging.CampingLogger;

/**
 * Implementation of a C# delegate. A delegate bundles objects of the specified
 * interface, is of the type of the specified interface and can be used like a
 * single object of the specified interface. A delegate delegates all calls to
 * all registered objects.
 * 
 * @param <D>
 *            the interface that the listeners implements.
 */
public class Delegate<D> {

    /**
     * The dynamic proxy used for delegation.
     */
    private class Delegator implements InvocationHandler {

	/**
	 * {@inheritDoc}.
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 *      java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(final Object proxy, final Method m, final Object[] args)
		throws Throwable {
	    if (targetMethods.contains(m)) {
		Object result = null;
		m.setAccessible(true);

		for (final D listener : new ArrayList<D>(listeners)) {
		    result = m.invoke(listener, args);
		}
		return result;
	    } else {
		return m.invoke(this, args);
	    }
	}
    }

    /** The {@link CampingLogger}. */
    private static CampingLogger logger = CampingLogger.getLogger(Delegate.class);

    /**
     * Constructor.
     * 
     * @param delegationInterface
     *            the interfaces used for delegation..
     */
    @SuppressWarnings("unchecked")
    public Delegate(final Class<D> delegationInterface) {
	if (!delegationInterface.isInterface()) {
	    throw new IllegalArgumentException(
		    "delegationInterface must be an interface");
	}
	delegateIf = delegationInterface;
	targetMethods = Arrays.asList(delegateIf.getMethods());

	try {
	    final ClassLoader cl = delegateIf.getClassLoader();
	    final Class<?>[] ifs = new Class[] { delegateIf };
	    dynProxy = (D) Proxy.newProxyInstance(cl, ifs, new Delegator());
	} catch (final Exception e) {
	    throw new IllegalArgumentException("Can not access delegationInterface");
	}
    }

    /**
     * Check if the {@link Delegate} contains the listener already.
     * 
     * @param listener
     *            the listener
     * @return <code>true</code> if contains, otherwise <code>false</code>.
     */
    public boolean contains(final D listener) {
	return listeners.contains(listener);
    }

    /**
     * @return the object that can be used for delegation.
     */
    public D getDelegator() {
	return dynProxy;
    }

    /**
     * Registers a listener.
     * 
     * @param listener
     *            the listener.
     */
    public void register(final D listener) {
	if (listener == null) {
	    throw new IllegalArgumentException(
		    "A listener cannot be null! (Delegate for "
			    + delegateIf.getSimpleName() + ").");
	}

	listeners.add(listener);
    }

    /**
     * Registers all listeners of the delegate.
     * 
     * @param delegate
     *            the delegate with all listeners.
     */
    public void register(final Delegate<D> delegate) {
	if (delegate == null) {
	    throw new IllegalArgumentException(
		    "A delegate to register as listener cannot be null! (Delegate for "
			    + delegateIf.getSimpleName() + ").");
	}

	listeners.add(delegate.getDelegator());
    }

    /**
     * Unregisters a listener.
     * 
     * @param listener
     *            the listener.
     */
    public void unregister(final D listener) {
	listeners.remove(listener);
    }

    /**
     * Unregisters all listeners.
     */
    public void unregisterAll() {
	listeners.clear();
    }

    /** The interface used for delegation. */
    private final Class<D> delegateIf;

    /** The dynamic proxy object for receiving state messages. */
    private D dynProxy;

    /** All registered state message listeners. */
    private final List<D> listeners = new ArrayList<D>();

    /** All methods of the delegation interface. */
    private final List<Method> targetMethods;
}
