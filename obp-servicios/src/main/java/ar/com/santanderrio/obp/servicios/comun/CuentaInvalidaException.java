/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

/**
 * Clase CuentaInvalidaException.
 *
 * @author mariano.g.pulera
 */
public class CuentaInvalidaException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CuentaInvalidaException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaInvalidaException(String mensaje) {
		super(mensaje);
	}

}
