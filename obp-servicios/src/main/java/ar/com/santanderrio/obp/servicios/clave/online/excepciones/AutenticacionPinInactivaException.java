package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author Pablo_Cumpe
 *
 */
public class AutenticacionPinInactivaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutenticacionPinInactivaException(String mensaje) {
		super(mensaje);
	}
}
