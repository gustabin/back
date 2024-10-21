/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import java.util.List;

/**
 * The Class ConfirmacionPlazoFijoOutView.
 */
public class ConfirmacionPlazoFijoOutView {

	/** The legales. */
	private List<String> legales;
	
	private String plazoMinimoPrecancelacion;
	
	private String porcentajeDePenalizacion;

	/**
	 * Gets the lista legales.
	 *
	 * @return the lista legales
	 */
	public List<String> getListaLegales() {
		return legales;
	}

	/**
	 * Sets the lista legales.
	 *
	 * @param listaLegales
	 *            the new lista legales
	 */
	public void setListaLegales(List<String> listaLegales) {
		this.legales = listaLegales;
	}

	public String getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	public void setPlazoMinimoPrecancelacion(String plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

	public String getPorcentajeDePenalizacion() {
		return porcentajeDePenalizacion;
	}

	public void setPorcentajeDePenalizacion(String porcentajeDePenalizacion) {
		this.porcentajeDePenalizacion = porcentajeDePenalizacion;
	}

}
