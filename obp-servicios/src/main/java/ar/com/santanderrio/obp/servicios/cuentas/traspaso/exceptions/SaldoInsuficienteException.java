/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.exceptions;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class SaldoInsuficienteException.
 * 
 */

public class SaldoInsuficienteException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new saldo insuficiente exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public SaldoInsuficienteException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new saldo insuficiente exception.
	 */
	public SaldoInsuficienteException() {

	}

}
