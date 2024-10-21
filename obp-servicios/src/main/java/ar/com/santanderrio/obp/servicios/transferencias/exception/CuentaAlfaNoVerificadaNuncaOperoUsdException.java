package ar.com.santanderrio.obp.servicios.transferencias.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

public class CuentaAlfaNoVerificadaNuncaOperoUsdException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5765585003695868062L;

	/**
	 * Instantiates a CuentaAlfaNoVerificadaNuncaOperoUsdException.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public CuentaAlfaNoVerificadaNuncaOperoUsdException(String mensaje) {
		super(mensaje);
	}

}
