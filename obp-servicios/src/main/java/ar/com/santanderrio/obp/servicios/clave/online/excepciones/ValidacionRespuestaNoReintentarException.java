/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.excepciones;

/**
 * The Class ValidacionRespuestaNoReintentarException.
 */
public class ValidacionRespuestaNoReintentarException extends Exception {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia una nueva ValidacionRespuestaNoReintentarException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ValidacionRespuestaNoReintentarException(String mensaje) {
		super(mensaje);
	}

}
