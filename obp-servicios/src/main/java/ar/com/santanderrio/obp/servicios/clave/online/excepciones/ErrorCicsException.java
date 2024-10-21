package ar.com.santanderrio.obp.servicios.clave.online.excepciones;
/**
 * 
 * @author itr
 *
 */
public class ErrorCicsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	  public ErrorCicsException(String mensaje) {
	        super(mensaje);
	    }
}
