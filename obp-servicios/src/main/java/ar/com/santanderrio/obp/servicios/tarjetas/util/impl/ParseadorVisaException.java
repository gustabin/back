/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;

/**
 * The Class ParseadorVisaException.
 *
 * @author sabrina.cis
 */
public class ParseadorVisaException extends TarjetaBOUtilsException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Error al parsear datos del ws de Visa.";

	/**
	 * Instantiates a new parseador visa exception.
	 */
	public ParseadorVisaException() {
		super(MESSAGE);
	}

}
