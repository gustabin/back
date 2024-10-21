/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class ExcedeLimiteTransferenciaException.
 */
public class ExcedeLimiteTransferenciaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 801693348887676927L;

	/**
	 * Instantiates a new excede limite transferencia exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ExcedeLimiteTransferenciaException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new excede limite transferencia exception.
	 */
	public ExcedeLimiteTransferenciaException() {

	}

}
