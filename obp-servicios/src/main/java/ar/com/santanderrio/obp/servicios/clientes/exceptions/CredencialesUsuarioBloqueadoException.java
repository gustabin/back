/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesUsuarioBloqueadoException.
 */
public class CredencialesUsuarioBloqueadoException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioBloqueadoException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesUsuarioBloqueadoException(String mensaje) {
		super(mensaje);
	}

}
