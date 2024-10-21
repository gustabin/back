package ar.com.santanderrio.obp.base.dao;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOException.
 */
public class DAOException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Codigo asociado al error, por si luego se tiene que tomar decisiones.
	 */
	private String errorCode;

	/**
	 * Instantiates a new DAO exception.
	 */
	public DAOException() {
		super();
	};

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public DAOException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param cause
	 *            the cause
	 * @param msg
	 *            the msg
	 */
	public DAOException(Throwable cause, String msg) {
		super(msg, cause);
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param msg
	 *            the msg
	 * @param errorCode
	 *            the errorCode
	 */
	public DAOException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param cause
	 *            the cause
	 * @param msg
	 *            the msg
	 * @param errorCode
	 *            the error code
	 */
	public DAOException(Throwable cause, String msg, String errorCode) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
