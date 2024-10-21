/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

/**
 * The Class NoSePuedeRealizarLaOperacionException.
 *
 * @author sabrina.cis
 */
public class NoSePuedeRealizarLaOperacionException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "En este momento no se puede realizar la operacion.";

	/**
	 * Instantiates a new no se puede realizar la operacion exception.
	 */
	public NoSePuedeRealizarLaOperacionException() {
		super(MESSAGE);
	}

}