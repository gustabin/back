/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.merlin.exception;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class MerlinWarningException.
 */
public class MerlinWarningException extends BusinessException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The tipo error. */
    private TipoError tipoError;

    /**
	 * Instantiates a new merlin warning exception.
	 *
	 * @param tipoError
	 *            the tipo error
	 */
    public MerlinWarningException(TipoError tipoError) {
        this.tipoError = tipoError;
    }

    /**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
    public TipoError getTipoError() {
        return tipoError;
    }

    /**
	 * Sets the tipo error.
	 *
	 * @param tipoError
	 *            the new tipo error
	 */
    public void setTipoError(TipoError tipoError) {
        this.tipoError = tipoError;
    }

}
