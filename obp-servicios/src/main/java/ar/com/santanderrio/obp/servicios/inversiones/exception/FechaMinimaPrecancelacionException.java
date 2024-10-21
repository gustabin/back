package ar.com.santanderrio.obp.servicios.inversiones.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

public class FechaMinimaPrecancelacionException extends DAOException {
	
	private static final long serialVersionUID = 1L;

	public FechaMinimaPrecancelacionException() {
	}

	public FechaMinimaPrecancelacionException(String message) {
		super(message);
	}
	
}
