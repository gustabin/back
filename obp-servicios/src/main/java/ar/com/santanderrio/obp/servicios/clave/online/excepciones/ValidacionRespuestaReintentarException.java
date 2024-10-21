/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ValidacionRespuestaReintentarException.
 */
public class ValidacionRespuestaReintentarException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva ValidacionRespuestaReintentarException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ValidacionRespuestaReintentarException(String mensaje) {
		super(mensaje);
	}

}
