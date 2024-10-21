/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

/**
 * The Class RespuestaAutenticacionView.
 */
public class RespuestaAutenticacionView extends MetodoAutenticacionView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id respuesta. */
	private Integer idRespuesta;

	/** The respuesta. */
	private String respuesta;

	/**
	 * Gets the id respuesta.
	 *
	 * @return the idRespuesta
	 */
	public Integer getIdRespuesta() {
		return idRespuesta;
	}

	/**
	 * Sets the id respuesta.
	 *
	 * @param idRespuesta
	 *            the idRespuesta to set
	 */
	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

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

}
