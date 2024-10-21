/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

/**
 * The Class RespuestaAutenticacion.
 */
public class RespuestaAutenticacion {

	/** The orden. */
	private Integer orden;

	/** The respuesta. */
	private String respuesta;

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
