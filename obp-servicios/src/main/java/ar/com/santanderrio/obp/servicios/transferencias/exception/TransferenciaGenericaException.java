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

public class TransferenciaGenericaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6770613692544281045L;

	/**
	 * Instantiates a new transferencia generica exception.
	 *
	 * @param str
	 *            the str
	 */
	public TransferenciaGenericaException(String str) {
		super(str);
	}
}
