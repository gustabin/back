package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author itr
 *
 */
public class ErrorPinOtpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public ErrorPinOtpException(String mensaje) {
		super(mensaje);
	}
}
