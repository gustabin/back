/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

/**
 * The Class TitularidadNoVerificadaException.
 *
 * @author B041299
 */
public class TitularidadNoVerificadaException extends Exception {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The error code service. */
	private int errorCode;

	/**
	 * Instantiates a new titularidad no verificada exception.
	 *
	 * @param errorCode
	 *            the error code
	 */
	public TitularidadNoVerificadaException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	/**
	 * Default constructor.
	 */
	public TitularidadNoVerificadaException() {
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
