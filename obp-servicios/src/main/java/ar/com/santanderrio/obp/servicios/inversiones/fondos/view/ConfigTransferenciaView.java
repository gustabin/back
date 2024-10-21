/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

/**
 * The Class ConfigTransferenciaView.
 */
public class ConfigTransferenciaView {

	/** The legales. */
	private String legales;

	/** The contratoAceptado. */
	private boolean contratoAceptado;

	/** The informacion gastos. */
	private String informacionGastos;
	
	/** The url reglamento. */
	private String urlReglamento;

	/**
	 * @return the urlReglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * @param urlReglamento the urlReglamento to set
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the informacion gastos.
	 *
	 * @return the informacionGastos
	 */
	public String getInformacionGastos() {
		return informacionGastos;
	}

	/**
	 * Sets the informacion gastos.
	 *
	 * @param informacionGastos
	 *            the informacionGastos to set
	 */
	public void setInformacionGastos(String informacionGastos) {
		this.informacionGastos = informacionGastos;
	}

	/**
	 * Checks if is contrato aceptado.
	 *
	 * @return the contratoAceptado
	 */
	public boolean isContratoAceptado() {
		return contratoAceptado;
	}

	/**
	 * Sets the contrato aceptado.
	 *
	 * @param contratoAceptado
	 *            the contratoAceptado to set
	 */
	public void setContratoAceptado(boolean contratoAceptado) {
		this.contratoAceptado = contratoAceptado;
	}

}
