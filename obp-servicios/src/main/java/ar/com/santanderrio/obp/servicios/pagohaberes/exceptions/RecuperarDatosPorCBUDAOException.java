/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.exceptions;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * Exception para DAO de Recuperar Datos Por CBU.
 */
public class RecuperarDatosPorCBUDAOException extends DAOException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Mensaje de excepcion desde la BD de mensajes. */
	private String mensaje;

	/**
	 * Instantiates a new Recuperar Datos Por CBU DAO Exception.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public RecuperarDatosPorCBUDAOException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new Recuperar Datos Por CBU DAO Exception.
	 * 
	 * @param message
	 *            the message
	 */
	public RecuperarDatosPorCBUDAOException(Exception message) {
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
