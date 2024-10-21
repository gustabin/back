/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class DisponibleInsuficienteException.
 */
public class DisponibleInsuficienteException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -153599603499612284L;

	/**
	 * Instantiates a new disponible insuficiente exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public DisponibleInsuficienteException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new disponible insuficiente exception.
	 */
	public DisponibleInsuficienteException() {
	}

}
