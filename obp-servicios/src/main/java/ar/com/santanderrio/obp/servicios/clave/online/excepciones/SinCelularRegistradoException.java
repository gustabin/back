package ar.com.santanderrio.obp.servicios.clave.online.excepciones;
/**
 * 
 * @author itr
 *
 */
public class SinCelularRegistradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public SinCelularRegistradoException(String mensaje) {
		super(mensaje);
	}
}
