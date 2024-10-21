/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ClienteSinAutValidoException.
 */
public class ClienteSinAutValidoException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ClienteSinAutValidoException(String mensaje) {
		super(mensaje);
	}

}
