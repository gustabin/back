/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Casuistica de error de soft token.
 * 
 * @author Manuel.Vargas
 *
 */
public class SyncException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -78041895030308660L;

	/**
	 * Instantiates a new sync exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public SyncException(String mensaje) {
		super(mensaje);
	}
}
