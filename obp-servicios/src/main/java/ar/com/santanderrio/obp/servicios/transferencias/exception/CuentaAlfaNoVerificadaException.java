package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

public class CuentaAlfaNoVerificadaException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5765585003695868062L;

	/**
	 * Instantiates a CuentaAlfaNoVerificadaException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaAlfaNoVerificadaException(String mensaje) {
		super(mensaje);
	}

}
