package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author itr
 *
 */
public class ErrorModuloException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public ErrorModuloException(String mensaje) {
		super(mensaje);
	}
}
