/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager.impl;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import ar.com.santanderrio.obp.base.service.ServiceException;

/**
 * The Class RequestContextAwareCallable.
 *
 * @author B039636
 * @param <V>
 *            the value type
 */
public abstract class RequestContextAwareCallable<V> implements Callable<V> {

	/** The request attributes. */
	private final RequestAttributes requestAttributes;

	/** The thread. */
	private Thread thread;

	/**
	 * Instantiates a new request context aware callable.
	 */
	public RequestContextAwareCallable() {
		this.requestAttributes = RequestContextHolder.getRequestAttributes();
		this.thread = Thread.currentThread();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public V call() throws ServiceException {
		try {
			RequestContextHolder.setRequestAttributes(requestAttributes);
			return onCall();
		} finally {
			if (Thread.currentThread() != thread) {
				RequestContextHolder.resetRequestAttributes();
			}
			thread = null;
		}
	}

	/**
	 * On call.
	 *
	 * @return the v
	 * @throws ServiceException
	 *             the service exception
	 */
	public abstract V onCall() throws ServiceException;
}