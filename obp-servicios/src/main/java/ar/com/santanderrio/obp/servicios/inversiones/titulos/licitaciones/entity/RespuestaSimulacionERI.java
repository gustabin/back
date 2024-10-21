/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RespuestaSimulacionERI.
 */
public class RespuestaSimulacionERI {
	
	/** The id evaluacion. */
	@JsonProperty("IdEvaluacion")
	private long idEvaluacion;
	
	/** The tipo disclaimer. */
	@JsonProperty("TipoDisclaimer")
	private int tipoDisclaimer;
	
	/** The cabecera. */
	@JsonProperty("Cabecera")
	private String cabecera;
	
	/** The pie. */
	@JsonProperty("Pie")
	private String pie;
	
	/** The disclaimer. */
	@JsonProperty("Disclaimer")
	private String disclaimer;
	
	/** The disclaimer cumplimiento. */
	@JsonProperty("DisclaimerCumplimiento")
	private String disclaimerCumplimiento;

	/**
	 * Gets the id evaluacion.
	 *
	 * @return the idEvaluacion
	 */
	public long getIdEvaluacion() {
		return idEvaluacion;
	}

	/**
	 * Sets the id evaluacion.
	 *
	 * @param idEvaluacion
	 *            the idEvaluacion to set
	 */
	public void setIdEvaluacion(long idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	/**
	 * Gets the tipo disclaimer.
	 *
	 * @return the tipoDisclaimer
	 */
	public int getTipoDisclaimer() {
		return tipoDisclaimer;
	}

	/**
	 * Sets the tipo disclaimer.
	 *
	 * @param tipoDisclaimer
	 *            the tipoDisclaimer to set
	 */
	public void setTipoDisclaimer(int tipoDisclaimer) {
		this.tipoDisclaimer = tipoDisclaimer;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the pie.
	 *
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}

	/**
	 * Sets the pie.
	 *
	 * @param pie
	 *            the pie to set
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}

	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the disclaimer to set
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	/**
	 * Gets the disclaimer cumplimiento.
	 *
	 * @return the disclaimerCumplimiento
	 */
	public String getDisclaimerCumplimiento() {
		return disclaimerCumplimiento;
	}

	/**
	 * Sets the disclaimer cumplimiento.
	 *
	 * @param disclaimerCumplimiento
	 *            the disclaimerCumplimiento to set
	 */
	public void setDisclaimerCumplimiento(String disclaimerCumplimiento) {
		this.disclaimerCumplimiento = disclaimerCumplimiento;
	}
}
