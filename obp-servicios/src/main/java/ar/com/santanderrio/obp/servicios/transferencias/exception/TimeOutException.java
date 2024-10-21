/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class TimeOutException.
 * 
 * @author pablo.d.gargaglione
 *
 */

public class TimeOutException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7753174074898419360L;

	/**
	 * Instantiates a new time out exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public TimeOutException(String mensaje) {
		super(mensaje);
	}

}
