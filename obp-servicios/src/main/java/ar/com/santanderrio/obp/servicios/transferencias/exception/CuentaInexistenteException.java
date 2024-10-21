/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

/**
 * The Class CuentaInexistenteException.
 */
public class CuentaInexistenteException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6651448991336770471L;

	/**
	 * Instantiates a new cuenta cerrada exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaInexistenteException(String mensaje) {
		super(mensaje);
	}
}
