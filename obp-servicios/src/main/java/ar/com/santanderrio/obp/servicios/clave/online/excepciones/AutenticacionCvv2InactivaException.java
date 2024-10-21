package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author Itr
 *
 */
public class AutenticacionCvv2InactivaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public AutenticacionCvv2InactivaException(String mensaje) {
		super(mensaje);
	}

}
