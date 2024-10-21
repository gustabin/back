package ar.com.santanderrio.obp.base.service;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceException.
 */
public class ServiceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4097878922494856772L;

	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
		super();
	};

	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause
	 *            the cause
	 * @param msg
	 *            the msg
	 */
	public ServiceException(Throwable cause, String msg) {
		super(msg, cause);
	}
}
