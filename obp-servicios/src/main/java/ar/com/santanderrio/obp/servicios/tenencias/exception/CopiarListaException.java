/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.exception;

/**
 * The Class CopiarListaException.
 */
public class CopiarListaException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new copiar lista exception.
	 */
	public CopiarListaException() {
		super();
	}

	/**
	 * Instantiates a new copiar lista exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public CopiarListaException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new copiar lista exception.
	 *
	 * @param msg
	 *            the msg
	 * @param e
	 *            the e
	 */
	public CopiarListaException(String msg, Exception e) {
		super(msg, e);
	}

}
