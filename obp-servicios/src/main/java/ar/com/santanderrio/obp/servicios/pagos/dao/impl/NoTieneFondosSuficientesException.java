/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

/**
 * The Class NoTieneFondosSuficientesException.
 */
public class NoTieneFondosSuficientesException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new no tiene fondos suficientes exception.
	 */
	public NoTieneFondosSuficientesException() {
		super();
	}

	/**
	 * Instantiates a new no tiene fondos suficientes exception.
	 *
	 * @param message
	 *            the message
	 */
	public NoTieneFondosSuficientesException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new no tiene fondos suficientes exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public NoTieneFondosSuficientesException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new no tiene fondos suficientes exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public NoTieneFondosSuficientesException(String message, Throwable cause) {
		super(message, cause);
	}

}
