/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.exception;

/**
 * The Class TarjetaInexistenteException.
 */
public class TarjetaInexistenteException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.  ZOA0226 - TARJETA INEXISTENTE.";

	/**
	 * Instantiates a new sin acceso A la informacion exception.
	 */
	public TarjetaInexistenteException() {
		super(MESSAGE);
	}

}
