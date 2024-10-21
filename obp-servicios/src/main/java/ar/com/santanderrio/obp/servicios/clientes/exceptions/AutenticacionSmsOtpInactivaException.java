package ar.com.santanderrio.obp.servicios.clientes.exceptions;

/**
 * }
 * 
 * @author Pablo_Cumpe
 *
 */
public class AutenticacionSmsOtpInactivaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public AutenticacionSmsOtpInactivaException(String mensaje) {
		super(mensaje);
	}
}
