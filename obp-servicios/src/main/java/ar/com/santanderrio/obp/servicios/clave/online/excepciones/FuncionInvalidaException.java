package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author itr
 *
 */
public class FuncionInvalidaException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public FuncionInvalidaException(String mensaje) {
		super(mensaje);
	}

}
