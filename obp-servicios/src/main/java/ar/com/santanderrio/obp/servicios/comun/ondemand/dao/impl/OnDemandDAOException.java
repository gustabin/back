/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl;

/**
 * Ondemand dao exception.
 */
public class OnDemandDAOException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Servicio OnDemand retornó código de error 1 ";

	/**
	 * Instantiates a new OnDemandDAOException.
	 */
	public OnDemandDAOException() {
		super(MESSAGE);
	}

}
