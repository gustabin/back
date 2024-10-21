/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class ExcedeLimiteBeneficiarioException.
 */
public class ExcedeLimiteDolaresBeneficiarioException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5765585003695868062L;

	/**
	 * Instantiates a new excede limite dolares beneficiario exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ExcedeLimiteDolaresBeneficiarioException(String mensaje) {
		super(mensaje);
	}

}
