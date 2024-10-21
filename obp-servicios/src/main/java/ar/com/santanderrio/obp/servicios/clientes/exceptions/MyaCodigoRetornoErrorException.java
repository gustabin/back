/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class MyaCodigoRetornoErrorException.
 */
public class MyaCodigoRetornoErrorException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public MyaCodigoRetornoErrorException(String mensaje) {
		super(mensaje);
	}

}
