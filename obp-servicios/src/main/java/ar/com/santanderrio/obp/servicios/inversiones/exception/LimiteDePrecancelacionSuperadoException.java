package ar.com.santanderrio.obp.servicios.inversiones.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

public class LimiteDePrecancelacionSuperadoException extends DAOException {

	private static final long serialVersionUID = 1L;

	public LimiteDePrecancelacionSuperadoException() {
	}

	public LimiteDePrecancelacionSuperadoException(String message) {
		super(message);
	}
	
}
