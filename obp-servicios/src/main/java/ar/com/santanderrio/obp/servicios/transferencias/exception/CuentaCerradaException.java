/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class CuentaCerradaException.
 */
public class CuentaCerradaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3926772293327474987L;

	/**
	 * Instantiates a new cuenta cerrada exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaCerradaException(String mensaje) {
		super(mensaje);
	}

}
