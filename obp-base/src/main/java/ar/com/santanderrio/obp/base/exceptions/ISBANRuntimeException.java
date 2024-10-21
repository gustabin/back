package ar.com.santanderrio.obp.base.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ISBANRuntimeException.
 */
public class ISBANRuntimeException extends RuntimeException {

	/** The Constant serialVersionUID. */

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ISBAN runtime exception.
	 *
	 * @param message
	 *            the message
	 */
	public ISBANRuntimeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new ISBAN runtime exception.
	 */
	public ISBANRuntimeException() {
		super();
	}

	/**
	 * Instantiates a new ISBAN runtime exception.
	 *
	 * @param e
	 *            the e
	 */
	public ISBANRuntimeException(Exception e) {
		super(e);
	}

}
