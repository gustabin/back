package ar.com.santanderrio.obp.servicios.inversiones.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

public class PrecancelacionSolicitadaException extends DAOException {
	
	private static final long serialVersionUID = 1L;

	public PrecancelacionSolicitadaException() {
	}

	public PrecancelacionSolicitadaException(String message) {
		super(message);
	}

}
