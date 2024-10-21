/*
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.exception;

/**
 * The Class AliasCorrespondienteCuentaPropiaUnicaException.
 */
public class AliasCorrespondienteCuentaPropiaUnicaException extends Exception {

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
	public AliasCorrespondienteCuentaPropiaUnicaException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new alias correspondiente cuenta propia exception.
	 */
	public AliasCorrespondienteCuentaPropiaUnicaException() {
		super(MENSAJE);
	}
}
