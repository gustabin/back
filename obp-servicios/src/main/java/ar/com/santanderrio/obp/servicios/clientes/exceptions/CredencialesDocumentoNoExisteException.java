/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesDocumentoNoExisteException.
 */
public class CredencialesDocumentoNoExisteException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesDocumentoNoExisteException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesDocumentoNoExisteException(String mensaje) {
		super(mensaje);
	}

}
