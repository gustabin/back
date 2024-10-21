package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaComisionResponse.
 */
public class DatosConsultaComisionResponse{

	/** The bonificacion. */
	@JsonProperty("Bonificacion")
	private String  bonificacion;

	/** The comisionOriginal. */
	@JsonProperty("ComisionOriginal")
	private String comisionOriginal;

	/** The comision. */
	@JsonProperty("Comision")
	private String comision;

	/** The informacion. */
	@JsonProperty("Informacion")
	private String informacion;

	/** The TieneBonificacion. */
	@JsonProperty("TieneBonificacion")
	private boolean tieneBonificacion;

	/**
	 * Gets the bonificacion.
	 *
	 * @return the bonificacion
	 */
	public String getBonificacion() {
		return bonificacion;
	}

	/**
	 * Sets the bonificacion.
	 *
	 * @param bonificacion
	 *            the bonificacion to set
	 */
	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	/**
	 * Gets the comisionOriginal.
	 *
	 * @return the comisionOriginal
	 */
	public String getComisionOriginal() {
		return comisionOriginal;
	}

	/**
	 * Sets the comisionOriginal.
	 *
	 * @param comisionOriginal
	 *            the comisionOriginal to set
	 */
	public void setComisionOriginal(String comisionOriginal) {
		this.comisionOriginal = comisionOriginal;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public String getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the comision to set
	 */
	public void setComision(String comision) {
		this.comision = comision;
	}

	/**
	 * Gets the informacion.
	 *
	 * @return the informacion
	 */
	public String getInformacion() {
		return informacion;
	}

	/**
	 * Sets the informacion.
	 *
	 * @param informacion
	 *            the informacion to set
	 */
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	/**
	 * Gets the tieneBonificacion.
	 *
	 * @return the tieneBonificacion
	 */
	public boolean getTieneBonificacion() {
		return tieneBonificacion;
	}

	/**
	 * Sets the tieneBonificacion.
	 *
	 * @param tieneBonificacion
	 *            the tieneBonificacion to set
	 */
	public void setTieneBonificacion(boolean tieneBonificacion) {
		this.tieneBonificacion = tieneBonificacion;
	}

}
