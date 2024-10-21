/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

import java.io.Serializable;
import java.util.List;

/**
 * The Class TenenciaPolizaResponse.
 *
 * @author sergio.e.goldentair
 */
public class TenenciaPolizaResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1160042353847004789L;

	/** The codigo respuesta. */
	private String codigoRespuesta;

	/** The mensaje. */
	private String mensaje;

	/** The respuesta. */
	private List<Poliza> respuesta;

	/**
	 * Gets the codigo respuesta.
	 *
	 * @return the codigo respuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Sets the codigo respuesta.
	 *
	 * @param codigoRespuesta
	 *            the new codigo respuesta
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
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public List<Poliza> getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(List<Poliza> respuesta) {
		this.respuesta = respuesta;
	}

}
