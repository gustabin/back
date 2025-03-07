/*
 * 
 */
package ar.com.santanderrio.base.http.web.util;

/**
 * Clase HostAdressException.
 *
 * @author mariano.g.pulera
 */
public class HostAdressException extends RuntimeException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva HostAdressException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public HostAdressException(String mensaje) {
		super(mensaje);
	}
}