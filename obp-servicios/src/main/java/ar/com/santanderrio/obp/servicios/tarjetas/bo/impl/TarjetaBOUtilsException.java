/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;

/**
 * The Class TarjetaBOUtilsException.
 *
 * @author sabrina.cis
 */

public class TarjetaBOUtilsException extends BusinessException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Error en Util de tarjetas.";

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 */
	public TarjetaBOUtilsException() {
		super(MESSAGE);
	}

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 *
	 * @param message
	 *            the message
	 */
	public TarjetaBOUtilsException(Exception message) {
		super(message);
	}

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 *
	 * @param message2
	 *            the message 2
	 */
	public TarjetaBOUtilsException(String message2) {
		super(message2);
	}

}
