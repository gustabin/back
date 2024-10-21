/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class DniInvalidoException.
 */
public class DniInvalidoException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public DniInvalidoException(String mensaje) {
		super(mensaje);
	}

}
