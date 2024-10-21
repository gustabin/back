package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * 
 * @author itr
 *
 */
public class ClaveOnlineNoEsPersonaFisicaException extends Exception {

	/**
	 * The Serial Version UID.
	 */
	private static final long serialVersionUID = -8879410501888513555L;

	/**
	 * Instantiates a new clave online clave exception.
	 *
	 * @param mensajea the mensaje
	 */
	public ClaveOnlineNoEsPersonaFisicaException(String mensaje) {
		super(mensaje);
	}
}
