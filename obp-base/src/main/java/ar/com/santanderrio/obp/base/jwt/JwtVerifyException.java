/**
 * 
 */
package ar.com.santanderrio.obp.base.jwt;

// TODO: Auto-generated Javadoc
/**
 * Encapsular los errores que se puedan dar al verificar un token evitando
 * propagar todas la excepciones cuando no se toma medidas diferentes segun el
 * origen del error.
 * 
 * @author sergio.e.goldentair
 *
 */
public class JwtVerifyException extends Exception {

	/**
	 * El serial id de la exception.
	 */
	private static final long serialVersionUID = 1387903412885613646L;

	/**
	 * Instantiates a new jwt verify exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public JwtVerifyException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new jwt verify exception.
	 *
	 * @param message
	 *            the message
	 */
	public JwtVerifyException(String message) {
		super(message);
	}

}
