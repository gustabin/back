/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class CuentaSinDebitosNiAcreditacionesException.
 */
public class CuentaSinDebitosNiAcreditacionesException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5765585003695868062L;

	/**
	 * Instantiates a new cuenta sin debitos ni acreditaciones exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaSinDebitosNiAcreditacionesException(String mensaje) {
		super(mensaje);
	}

}
