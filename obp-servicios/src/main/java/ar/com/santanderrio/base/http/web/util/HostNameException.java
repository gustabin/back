/*
 * 
 */
package ar.com.santanderrio.base.http.web.util;

/**
 * Clase HostNameException.
 *
 * @author mariano.g.pulera
 */
public class HostNameException extends RuntimeException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva HostNameException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public HostNameException(String mensaje) {
		super(mensaje);
	}
}