/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dto;

/**
 * The Class SolicitarPrecancelarOutDTO.
 */
public class SolicitarPrecancelarOutDTO {

	/** The porcentaje penalizacion. */
	private String porcentajePenalizacion;

	/** The mensaje. */
	private String mensaje;

	/** The plazo minimo precancelacion. */
	private String plazoMinimoPrecancelacion;

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentaje penalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the new porcentaje penalizacion
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
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
	 * Gets the plazo minimo precancelacion.
	 *
	 * @return the plazo minimo precancelacion
	 */
	public String getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	/**
	 * Sets the plazo minimo precancelacion.
	 *
	 * @param plazoMinimoPrecancelacion
	 *            the new plazo minimo precancelacion
	 */
	public void setPlazoMinimoPrecancelacion(String plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

}
