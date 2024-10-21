/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.exceptions;

/**
 * The Class ValidarFechaDesdeBuscadorException.
 */
public class ValidarFechaDesdeBuscadorException extends Exception {

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
	public ValidarFechaDesdeBuscadorException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Instantiates a new tarjeta BO utils exception.
	 *
	 * @param message
	 *            the message
	 */
	public ValidarFechaDesdeBuscadorException(Exception message) {
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
