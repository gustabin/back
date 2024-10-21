/*
 * 
 */
package ar.com.santanderrio.obp.servicios.exception;

/**
 * Exception usada para manifestar en el front un error inesperado.
 *
 * @author emilio.watemberg
 * @since Dec 15, 2016.
 */
public class RobotException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5950081840569643219L;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message
	 */
	public RobotException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public RobotException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor .
	 *
	 * @param cause
	 *            the cause
	 */
	public RobotException(Throwable cause) {
		super(cause);
	}

}
