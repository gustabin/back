/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;

/**
 * The Class ParseadorConsumosPendientesException.
 *
 * @author florencia.n.martinez
 */
public class ParseadorConsumosPendientesException extends TarjetaBOUtilsException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5122917544804643379L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Error al pasear datos del WS de VISA: Consumos Pendientes.";

	/**
	 * Instantiates a new parseador consumos pendientes exception.
	 */
	public ParseadorConsumosPendientesException() {
		super(MESSAGE);
	}
}
