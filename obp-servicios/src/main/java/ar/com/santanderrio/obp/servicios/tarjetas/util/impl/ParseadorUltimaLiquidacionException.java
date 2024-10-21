/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

/**
 * The Class ParseadorUltimaLiquidacionException.
 *
 * @author florencia.n.martinez
 */
public class ParseadorUltimaLiquidacionException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 699655680496347425L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Error al pasear datos del WS de VISA: Ultima Liquidacion.";

	/**
	 * Instantiates a new parseador visa exception.
	 */
	public ParseadorUltimaLiquidacionException() {
		super(MESSAGE);
	}
}
