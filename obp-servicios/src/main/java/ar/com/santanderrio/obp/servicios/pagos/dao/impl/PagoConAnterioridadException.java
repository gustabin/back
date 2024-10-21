/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

/**
 * The Class PagoConAnterioridadException.
 */
public class PagoConAnterioridadException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4097878922494856772L;

	/**
	 * Instantiates a new service exception.
	 */
	public PagoConAnterioridadException() {
		super();
	};

	/**
	 * Instantiates a new service exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public PagoConAnterioridadException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public PagoConAnterioridadException(String msg) {
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
	public PagoConAnterioridadException(Throwable cause, String msg) {
		super(msg, cause);
	}
}
