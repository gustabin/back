/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class SinDeudasException.
 */
public class SinDeudasException extends DAOException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8485547126008771787L;
    
    /**
	 * Instantiates a new sin deudas exception.
	 */
    public SinDeudasException() {
        super();
    }

    /**
	 * Instantiates a new sin deudas exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
    public SinDeudasException(String mensaje) {
        super(mensaje);
    }

}
