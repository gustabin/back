/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

/**
 * The Class CompraVentaDolaresException.
 *
 * @author sabrina.cis
 */
public class CompraVentaDolaresException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MENSAJE. */
	private static final String MENSAJE = "Ocurrió un error    en compra/venta de dólares.";

	/**
	 * Instantiates a new compra venta dolares exception.
	 *
	 * 
	 * @param cause
	 *            the cause
	 */
	public CompraVentaDolaresException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new compra venta dolares exception.
	 */
	public CompraVentaDolaresException() {
		super(MENSAJE);
	}

}