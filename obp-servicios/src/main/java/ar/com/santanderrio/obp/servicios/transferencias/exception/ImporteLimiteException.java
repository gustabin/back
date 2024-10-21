/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class TransferenciaGenericaException.
 * 
 * @author Matías Manzano
 */

public class ImporteLimiteException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -78041895030308660L;

	/**
	 * Instantiates a new importe limite exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ImporteLimiteException(String mensaje) {
		super(mensaje);
	}

}
