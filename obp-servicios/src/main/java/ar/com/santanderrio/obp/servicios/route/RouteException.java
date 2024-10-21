/*
 * 
 */
package ar.com.santanderrio.obp.servicios.route;

/**
 * Manage Route exception.
 */
public class RouteException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param message
	 *            the message
	 */
	public RouteException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new manager exception.
	 */
	public RouteException() {
		super();
	}

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param e
	 *            the e
	 */
	public RouteException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param e
	 *            the e
	 */
	public RouteException(Throwable e) {
		super(e);
	}

}
