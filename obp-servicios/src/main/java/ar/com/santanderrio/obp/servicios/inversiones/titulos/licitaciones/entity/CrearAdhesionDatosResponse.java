/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class CrearAdhesionDatosResponse.
 */
public class CrearAdhesionDatosResponse {
	
	/** The resultado. */
	@JsonProperty("Resultado")
	private String resultado;
	
	/** The form completed. */
	@JsonProperty("FormCompleted")
	private String formCompleted;
	
	/** The disclaimers. */
	@JsonProperty("Disclaimers")
	private List<DisclaimersAdhesionPDCResponse> disclaimers = new ArrayList<DisclaimersAdhesionPDCResponse>();
	
	/** The form campos. */
	@JsonProperty("FormCampos")
	private List<ArrayList<FormCamposAdhesionPDCResponse>> formCampos = new ArrayList<ArrayList<FormCamposAdhesionPDCResponse>>();

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the form completed.
	 *
	 * @return the form completed
	 */
	public String getFormCompleted() {
		return formCompleted;
	}

	/**
	 * Sets the form completed.
	 *
	 * @param formCompleted
	 *            the new form completed
	 */
	public void setFormCompleted(String formCompleted) {
		this.formCompleted = formCompleted;
	}

	/**
	 * Gets the disclaimers.
	 *
	 * @return the disclaimers
	 */
	public List<DisclaimersAdhesionPDCResponse> getDisclaimers() {
		return disclaimers;
	}

	/**
	 * Sets the disclaimers.
	 *
	 * @param disclaimers
	 *            the new disclaimers
	 */
	public void setDisclaimers(List<DisclaimersAdhesionPDCResponse> disclaimers) {
		this.disclaimers = disclaimers;
	}

	/**
	 * Gets the form campos.
	 *
	 * @return the form campos
	 */
	public List<ArrayList<FormCamposAdhesionPDCResponse>> getFormCampos() {
		return formCampos;
	}

	/**
	 * Sets the form campos.
	 *
	 * @param formCampos
	 *            the new form campos
	 */
	public void setFormCampos(List<ArrayList<FormCamposAdhesionPDCResponse>> formCampos) {
		this.formCampos = formCampos;
	}
}
