package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author itr
 *
 */
public class AutenticarTarjetaDebitoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param mensaje
	 */
	public AutenticarTarjetaDebitoException(String mensaje) {
		super(mensaje);
	}
}
