/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class SesionExpiradaException.
 */
public class SesionExpiradaException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva SesionExpiradaException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public SesionExpiradaException(String mensaje) {
		super(mensaje);
	}

}
