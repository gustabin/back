package ar.com.santanderrio.obp.servicios.clave.online.excepciones;
/**
 * 
 * @author Itr
 *
 */
public class CLienteSinContratoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Instancia una nueva CLienteSinContratoException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CLienteSinContratoException(String mensaje) {
		super(mensaje);
	}

}
