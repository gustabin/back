/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.util.List;

/**
 * The Class ConfiguracionModificacionView.
 */
public class ConfiguracionModificacionView extends TransferenciaAgendadaDetalleView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The is pesos. */
	private Boolean isPesos;

	/** The frecuencias. */
	private List<FrecuenciaTransferenciaAgendadaView> frecuencias;

	/** The frecuencia codigo. */
	private String frecuenciaCodigo;

	/** The conceptos. */
	private List<ConceptoTransferenciaAgendadaView> conceptos;

	/** The concepto codigo. */
	private String conceptoCodigo;

	/**
	 * Gets the checks if is pesos.
	 *
	 * @return the isPesos
	 */
	public Boolean getIsPesos() {
		return isPesos;
	}

	/**
	 * Sets the checks if is pesos.
	 *
	 * @param isPesos
	 *            the isPesos to set
	 */
	public void setIsPesos(Boolean isPesos) {
		this.isPesos = isPesos;
	}

	/**
	 * Gets the frecuencia codigo.
	 *
	 * @return the frecuenciaCodigo
	 */
	public String getFrecuenciaCodigo() {
		return frecuenciaCodigo;
	}

	/**
	 * Sets the frecuencia codigo.
	 *
	 * @param frecuenciaCodigo
	 *            the frecuenciaCodigo to set
	 */
	public void setFrecuenciaCodigo(String frecuenciaCodigo) {
		this.frecuenciaCodigo = frecuenciaCodigo;
	}

	/**
	 * Gets the frecuencias.
	 *
	 * @return the frecuencias
	 */
	public List<FrecuenciaTransferenciaAgendadaView> getFrecuencias() {
		return frecuencias;
	}

	/**
	 * Sets the frecuencias.
	 *
	 * @param frecuencias
	 *            the frecuencias to set
	 */
	public void setFrecuencias(List<FrecuenciaTransferenciaAgendadaView> frecuencias) {
		this.frecuencias = frecuencias;
	}

	/**
	 * Gets the conceptos.
	 *
	 * @return the conceptos
	 */
	public List<ConceptoTransferenciaAgendadaView> getConceptos() {
		return conceptos;
	}

	/**
	 * Sets the conceptos.
	 *
	 * @param conceptos
	 *            the conceptos to set
	 */
	public void setConceptos(List<ConceptoTransferenciaAgendadaView> conceptos) {
		this.conceptos = conceptos;
	}

	/**
	 * Gets the concepto codigo.
	 *
	 * @return the conceptoCodigo
	 */
	public String getConceptoCodigo() {
		return conceptoCodigo;
	}

	/**
	 * Sets the concepto codigo.
	 *
	 * @param conceptoCodigo
	 *            the conceptoCodigo to set
	 */
	public void setConceptoCodigo(String conceptoCodigo) {
		this.conceptoCodigo = conceptoCodigo;
	}

}
