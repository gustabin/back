/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.exceptions;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Exception para DAO de ejecucion de pago multiple. DTF: 10221
 * 
 * @author Manuel.Vargas B041299
 */
public class EjecucionPagoMultipleDAOException extends DAOException {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Mensaje de excepcion desde la BD de mensajes. */
	private String mensaje;

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public EjecucionPagoMultipleDAOException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 *
	 * @param message
	 *            the message
	 */
	public EjecucionPagoMultipleDAOException(Exception message) {
		super(message);
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
