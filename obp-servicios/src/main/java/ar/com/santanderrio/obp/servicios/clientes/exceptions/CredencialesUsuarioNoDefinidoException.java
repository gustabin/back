/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesUsuarioNoDefinidoException.
 */
public class CredencialesUsuarioNoDefinidoException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesUsuarioNoDefinidoException(String mensaje) {
		super(mensaje);
	}

}
