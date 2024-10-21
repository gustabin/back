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

public class SaldoInsuficienteException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5348656757759891025L;

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
