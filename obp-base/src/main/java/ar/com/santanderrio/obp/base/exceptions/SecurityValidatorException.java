package ar.com.santanderrio.obp.base.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityValidatorException.
 */
public class SecurityValidatorException extends Exception {

	/** The Constant serialVersionUID. */

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new security validator exception.
	 *
	 * @param message
	 *            the message
	 */
	public SecurityValidatorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new security validator exception.
	 */
	public SecurityValidatorException() {
		super();
	}

	/**
	 * Instantiates a new security validator exception.
	 *
	 * @param e
	 *            the e
	 */
	public SecurityValidatorException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new security validator exception.
	 *
	 * @param e
	 *            the e
	 */
	public SecurityValidatorException(Throwable e) {
		super(e);
	}

}
