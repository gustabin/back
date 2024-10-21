/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesClaveIndividuoVencidaException.
 */
public class CredencialesClaveIndividuoVencidaException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesClavesExpiradas.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesClaveIndividuoVencidaException(String mensaje) {
		super(mensaje);
	}

}
