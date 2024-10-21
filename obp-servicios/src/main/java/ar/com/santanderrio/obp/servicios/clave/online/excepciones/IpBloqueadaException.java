package ar.com.santanderrio.obp.servicios.clave.online.excepciones;
/**
 * 
 * @author it resources
 *
 */
public class IpBloqueadaException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva CredencialesUsuarioNoDefinido.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public IpBloqueadaException(String mensaje) {
		super(mensaje);
	}
}
