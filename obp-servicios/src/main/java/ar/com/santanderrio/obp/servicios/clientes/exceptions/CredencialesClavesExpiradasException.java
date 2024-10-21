/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesClavesExpiradasException.
 */
public class CredencialesClavesExpiradasException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesClavesExpiradas.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesClavesExpiradasException(String mensaje) {
		super(mensaje);
	}

}
