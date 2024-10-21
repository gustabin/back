/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.exception;

import javax.xml.ws.soap.SOAPFaultException;

/**
 * Exception del ws de consulta datos titularidad Extendido.
 *
 * @author Manuel.Vargas B041299
 * @version 1
 * @see javax.xml.ws.soap.SOAPFaultException
 * 
 */
public class ConsultaTitularidadTimeoutException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -475200784381644206L;

	/**
	 * Instantiates a new consulta titularidad timeout exception.
	 *
	 * @param e
	 *            the e
	 */
	public ConsultaTitularidadTimeoutException(SOAPFaultException e) {
	}

}
