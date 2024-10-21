/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.exception;

/**
 * The Class SinAccesoALaInformacionException.
 *
 * @author sabrina.cis
 */
public class ErrorAltaSolicitudException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "Sin acceso a la informacion.";

	/**
	 * Instantiates a new sin acceso A la informacion exception.
	 */
	public ErrorAltaSolicitudException() {
		super(MESSAGE);
	}

}
