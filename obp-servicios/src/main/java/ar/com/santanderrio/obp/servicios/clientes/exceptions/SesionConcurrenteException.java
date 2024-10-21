package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * }
 * 
 */
public class SesionConcurrenteException extends CredencialesException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public SesionConcurrenteException(String mensaje) {
		super(mensaje);
	}
}
