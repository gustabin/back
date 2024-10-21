/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.alias;

/**
 * @author leonardo.medina
 *
 */
//@WebFault
public class ConsultaExtendidaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7760205650277254909L;

	/**
	 * @param message
	 * @param transferenciasException
	 */
	public ConsultaExtendidaException(String message) {
		super(message);
	}

	/**
     * @param message
     * @param cause
     * @param transferenciasException
     */
    public ConsultaExtendidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
