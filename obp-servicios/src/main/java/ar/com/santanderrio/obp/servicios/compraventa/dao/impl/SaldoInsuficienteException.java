/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

/**
 * The Class SaldoInsuficienteException.
 *
 * @author sabrina.cis
 */
public class SaldoInsuficienteException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "El saldo es insuficiente para realizar la operacion.";

	/**
	 * Instantiates a new saldo insuficiente exception.
	 */
	public SaldoInsuficienteException() {
		super(MESSAGE);
	}

}
