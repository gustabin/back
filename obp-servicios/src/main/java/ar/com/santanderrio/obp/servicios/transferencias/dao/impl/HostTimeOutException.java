/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Exception si hay time out en TRFCCI130.
 *
 * @author B041299
 */
public class HostTimeOutException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -157675768131192556L;

	/**
	 * Instantiates a new host time out exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public HostTimeOutException(String mensaje) {
		super(mensaje);
	}
	
	/**
	 * Instantiates a new host time out exception.
	 */
	public HostTimeOutException() {
		super("");
	}
}
