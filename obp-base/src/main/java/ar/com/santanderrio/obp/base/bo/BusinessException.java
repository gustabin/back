package ar.com.santanderrio.obp.base.bo;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessException.
 */
public class BusinessException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6514662528241440389L;

	/**
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
		super();
	};

	/**
	 * Instantiates a new business exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param cause
	 *            the cause
	 * @param msg
	 *            the msg
	 */
	public BusinessException(Throwable cause, String msg) {
		super(msg, cause);
	}
}
