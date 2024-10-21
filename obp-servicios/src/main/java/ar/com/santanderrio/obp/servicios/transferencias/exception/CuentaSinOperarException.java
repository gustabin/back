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

public class CuentaSinOperarException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6690881617046081344L;

	/**
	 * Instantiates a new cuenta sin operar exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaSinOperarException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new cuenta sin operar exception.
	 */
	public CuentaSinOperarException() {

	}

}
