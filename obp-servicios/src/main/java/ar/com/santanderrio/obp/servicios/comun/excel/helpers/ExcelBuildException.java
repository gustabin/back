/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

/**
 * The Class ExcelBuildException.
 *
 * @author B039636
 */
public class ExcelBuildException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5745173843639426977L;

	/**
	 * Instantiates a new excel build exception.
	 */
	public ExcelBuildException() {
		super();
	}

	/**
	 * Instantiates a new excel build exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ExcelBuildException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new excel build exception.
	 *
	 * @param message
	 *            the message
	 */
	public ExcelBuildException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new excel build exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public ExcelBuildException(Throwable cause) {
		super(cause);
	}

}
