/*
 * 
 */
package ar.com.santanderrio.obp.servicios.route;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * Route builder.
 *
 * @author emilio.watemberg
 * @param <E>
 *            the element type
 * @see {@link Route}
 * @since Aug 23, 2016.
 */
public abstract class RouteBuilder<E> {

	/** The logger. */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/** The Constant THREAD_POOL_SIZE. */
	private static final int THREAD_POOL_SIZE = 10;

	/** The executor. */
	private static ExecutorService executor;

	/**
	 * Instantiates a new route builder.
	 */
	public RouteBuilder() {
	}

	/**
	 * Configure.
	 *
	 * @throws RouteBuilderException
	 *             the route builder exception
	 */
	public void configure() throws RouteBuilderException {
		// Generar un profile que permita utilizar el workerexecutor de IBM
		if (executor == null) {
			RouteBuilder.executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
		}
		logger.info("init executor with thread pool size: " + THREAD_POOL_SIZE);
	}

	/**
	 * Process.
	 *
	 * @throws RouteBuilderException
	 *             the route builder exception
	 */
	public abstract void process() throws RouteBuilderException;

	/**
	 * Manage responses.
	 *
	 * @return the respuesta
	 * @throws RouteBuilderException
	 *             the route builder exception
	 */
	public abstract Respuesta<E> manageResponses() throws RouteBuilderException;

	/**
	 * Gets the executor.
	 *
	 * @return the executor
	 */
	public ExecutorService getExecutor() {
		return executor;
	}

	/**
	 * Sets the executor.
	 *
	 * @param executor
	 *            the new executor
	 */
	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}
}
