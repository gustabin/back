/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/** The Constant serialVersionUID. */
public class SaldoCuotasParteInsuficienteException extends DAOException {

	/**
	 * Instantiates a new saldo cuotas parte insuficiente exception.
	 */
	public SaldoCuotasParteInsuficienteException() {
	}

	/**
	 * Instantiates a new saldo cuotas parte insuficiente exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public SaldoCuotasParteInsuficienteException(String msg) {
		super(msg);
	}

}
