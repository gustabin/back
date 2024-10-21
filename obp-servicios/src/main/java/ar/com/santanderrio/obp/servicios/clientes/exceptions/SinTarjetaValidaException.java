package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * 
 * @author itr
 *
 */
public class SinTarjetaValidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva MyaWarningException.
	 *
	 * @param mensaje the mensaje
	 */
	public SinTarjetaValidaException(String mensaje) {
		super(mensaje);
	}

}
