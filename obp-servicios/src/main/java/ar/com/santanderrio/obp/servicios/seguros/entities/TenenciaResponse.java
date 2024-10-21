/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;

/**
 * The Class TenenciaResponse.
 *
 * @author sergio.e.goldentair
 */
public class TenenciaResponse implements Serializable {
	/** Serial Id. */
	private static final long serialVersionUID = -7624312688528000121L;

	/** The codigo respuesta. */
	private String codigoRespuesta;

	/** The mensaje. */
	private String mensaje;

	/** The respuesta. */
	private TenenciaRespuesta respuesta;

	/**
	 * Gets the codigo respuesta.
	 *
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Sets the codigo respuesta.
	 *
	 * @param codigoRespuesta
	 *            the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
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

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public TenenciaRespuesta getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(TenenciaRespuesta respuesta) {
		this.respuesta = respuesta;
	}
}
