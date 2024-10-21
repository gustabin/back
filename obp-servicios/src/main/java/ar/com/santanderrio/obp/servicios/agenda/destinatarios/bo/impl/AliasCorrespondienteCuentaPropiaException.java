/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

/**
 * The Class AliasCorrespondienteCuentaPropiaException.
 *
 * @author florencia.n.martinez
 */
public class AliasCorrespondienteCuentaPropiaException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MENSAJE. */
	private static final String MENSAJE = "El alias que desea agendar pertenece a una cuenta propia.";

	/**
	 * Instantiates a new alias correspondiente cuenta propia exception.
	 *
	 * @param cause
	 *            the cause
	 */
	public AliasCorrespondienteCuentaPropiaException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new alias correspondiente cuenta propia exception.
	 */
	public AliasCorrespondienteCuentaPropiaException() {
		super(MENSAJE);
	}
}
