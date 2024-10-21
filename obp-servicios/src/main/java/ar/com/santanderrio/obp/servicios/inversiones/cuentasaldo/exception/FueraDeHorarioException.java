/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class FueraDeHorarioException.
 */
public class FueraDeHorarioException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8486191345737458740L;

	/**
	 * Instantiates a new fuera de horario exception.
	 */
	public FueraDeHorarioException() {
	}

	/**
	 * Instantiates a new fuera de horario exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public FueraDeHorarioException(String msg) {
		super(msg);
	}

}
