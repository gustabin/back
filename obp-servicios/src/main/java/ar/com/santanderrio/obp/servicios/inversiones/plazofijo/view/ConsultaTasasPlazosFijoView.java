/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.List;

/**
 * The Class ConsultaTasasPlazosFijoView.
 */
public class ConsultaTasasPlazosFijoView {

	/** The legales. */
	private List<String> legales;

	/** The lista tasas por plazo. */
	List<TasasPorPlazoView> listaTasasPorPlazo;
	
	/** The banca seleccionada. */
	private String bancaSeleccionada;
	
	/**
	 * Gets the lista tasas por plazo.
	 *
	 * @return the lista tasas por plazo
	 */
	public List<TasasPorPlazoView> getListaTasasPorPlazo() {
		return listaTasasPorPlazo;
	}

	/**
	 * Sets the lista tasas por plazo.
	 *
	 * @param listaTasasPorPlazo
	 *            the new lista tasas por plazo
	 */
	public void setListaTasasPorPlazo(List<TasasPorPlazoView> listaTasasPorPlazo) {
		this.listaTasasPorPlazo = listaTasasPorPlazo;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public List<String> getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(List<String> legales) {
		this.legales = legales;
	}

	/**
	 * Gets the banca seleccionada.
	 *
	 * @return the banca seleccionada
	 */
	public String getBancaSeleccionada() {
		return bancaSeleccionada;
	}

	/**
	 * Sets the banca seleccionada.
	 *
	 * @param bancaSeleccionada
	 *            the new banca seleccionada
	 */
	public void setBancaSeleccionada(String bancaSeleccionada) {
		this.bancaSeleccionada = bancaSeleccionada;
	}
	
}
