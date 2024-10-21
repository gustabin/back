/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class MyaServiceException.
 */
public class MyaServiceException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public MyaServiceException(String mensaje) {
		super(mensaje);
	}

}
