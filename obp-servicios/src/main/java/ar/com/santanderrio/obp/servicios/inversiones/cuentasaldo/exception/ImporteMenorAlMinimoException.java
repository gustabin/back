/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Class ImporteMenorAlMinimoException.
 */
public class ImporteMenorAlMinimoException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2483246798063597448L;

	/**
	 * Instantiates a new importe menor al minimo exception.
	 */
	public ImporteMenorAlMinimoException() {
	}

	/**
	 * Instantiates a new importe menor al minimo exception.
	 *
	 * @param msg
	 *            the msg
	 */
	public ImporteMenorAlMinimoException(String msg) {
		super(msg);
	}

}
