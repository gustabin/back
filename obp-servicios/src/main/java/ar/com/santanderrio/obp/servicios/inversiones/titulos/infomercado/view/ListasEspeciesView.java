/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view;

/**
 * The Class ListasEspeciesView.
 */
public class ListasEspeciesView {

	/** The especies pesos. */
	private InstrumentosEspecieView especiesPesos;
	
	/** The especies dolares. */
	private InstrumentosEspecieView especiesDolares;
	
	/** The mensaje buscador. */
	private String mensajeBuscador;

	/**
	 * Gets the especies pesos.
	 *
	 * @return the especiesPesos
	 */
	public InstrumentosEspecieView getEspeciesPesos() {
		return especiesPesos;
	}

	/**
	 * Sets the especies pesos.
	 *
	 * @param especiesPesos
	 *            the especiesPesos to set
	 */
	public void setEspeciesPesos(InstrumentosEspecieView especiesPesos) {
		this.especiesPesos = especiesPesos;
	}

	/**
	 * Gets the especies dolares.
	 *
	 * @return the especiesDolares
	 */
	public InstrumentosEspecieView getEspeciesDolares() {
		return especiesDolares;
	}

	/**
	 * Sets the especies dolares.
	 *
	 * @param especiesDolares
	 *            the especiesDolares to set
	 */
	public void setEspeciesDolares(InstrumentosEspecieView especiesDolares) {
		this.especiesDolares = especiesDolares;
	}

	/**
	 * Gets the mensaje buscador.
	 *
	 * @return the mensaje buscador
	 */
	public String getMensajeBuscador() {
		return mensajeBuscador;
	}

	/**
	 * Sets the mensaje buscador.
	 *
	 * @param mensajeBuscador
	 *            the new mensaje buscador
	 */
	public void setMensajeBuscador(String mensajeBuscador) {
		this.mensajeBuscador = mensajeBuscador;
	}

}
