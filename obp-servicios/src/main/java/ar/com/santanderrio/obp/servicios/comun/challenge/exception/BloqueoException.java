/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Casuistica de error de soft token.
 * 
 * @author Manuel.Vargas
 */
public class BloqueoException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -78041895030308660L;

	/**
	 * Instantiates a new bloqueo exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public BloqueoException(String mensaje) {
		super(mensaje);
	}
}
