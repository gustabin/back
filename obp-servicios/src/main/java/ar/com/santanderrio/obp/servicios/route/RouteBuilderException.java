/*
 * 
 */
package ar.com.santanderrio.obp.servicios.route;

/**
 * Manage route builder exception.
 */
public class RouteBuilderException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param message
	 *            the message
	 */
	public RouteBuilderException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new manager exception.
	 */
	public RouteBuilderException() {
		super();
	}

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param e
	 *            the e
	 */
	public RouteBuilderException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new manager exception.
	 *
	 * @param e
	 *            the e
	 */
	public RouteBuilderException(Throwable e) {
		super(e);
	}

}
