/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class ProblemasAlEfectuarTransferenciaException.
 */
public class ProblemasAlEfectuarTransferenciaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9056285141378274305L;

	/**
	 * Instantiates a new problemas al efectuar transferencia exception.
	 */
	public ProblemasAlEfectuarTransferenciaException() {
	}

	/**
	 * Instantiates a new problemas al efectuar transferencia exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public ProblemasAlEfectuarTransferenciaException(String msg) {
		super(msg);
	}

}
