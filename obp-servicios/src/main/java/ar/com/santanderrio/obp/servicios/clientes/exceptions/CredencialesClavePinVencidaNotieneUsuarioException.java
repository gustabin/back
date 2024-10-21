/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * The Class CredencialesClavePinVencidaNotieneUsuarioException.
 */
public class CredencialesClavePinVencidaNotieneUsuarioException extends CredencialesException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesClavesExpiradas.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CredencialesClavePinVencidaNotieneUsuarioException(String mensaje) {
		super(mensaje);
	}

}
