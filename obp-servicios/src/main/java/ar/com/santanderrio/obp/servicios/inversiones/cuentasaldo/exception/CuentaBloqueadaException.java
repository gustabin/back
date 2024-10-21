/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class CuentaBloqueadaException.
 */
public class CuentaBloqueadaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3693732211499025704L;

	/**
	 * Instantiates a new cuenta bloqueada exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaBloqueadaException(String mensaje){
		super(mensaje);
	}
	
	/**
	 * Instantiates a new cuenta bloqueada exception.
	 */
	public CuentaBloqueadaException(){
		
	}
}
