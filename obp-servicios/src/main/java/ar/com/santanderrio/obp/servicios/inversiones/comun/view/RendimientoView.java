/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoView.
 */
public class RendimientoView {

	/** The tooltip. */
	private String tooltip;
	
	/** The informacion parcial. */
	private boolean informacionParcial;
	
	/** The rendimiento pesos. */
	private List<ElementoRendimientoView> rendimientoPesos = new ArrayList<ElementoRendimientoView>();
	
	/** The rendimiento dolares. */
	private List<ElementoRendimientoView> rendimientoDolares = new ArrayList<ElementoRendimientoView>();

	/**
	 * Gets the tooltip.
	 *
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Sets the tooltip.
	 *
	 * @param tooltip
	 *            the new tooltip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Checks if is informacion parcial.
	 *
	 * @return true, if is informacion parcial
	 */
	public boolean isInformacionParcial() {
		return informacionParcial;
	}

	/**
	 * Sets the informacion parcial.
	 *
	 * @param informacionParcial
	 *            the new informacion parcial
	 */
	public void setInformacionParcial(boolean informacionParcial) {
		this.informacionParcial = informacionParcial;
	}

	/**
	 * Gets the rendimiento pesos.
	 *
	 * @return the rendimiento pesos
	 */
	public List<ElementoRendimientoView> getRendimientoPesos() {
		return rendimientoPesos;
	}

	/**
	 * Sets the rendimiento pesos.
	 *
	 * @param rendimientoPesos
	 *            the new rendimiento pesos
	 */
	public void setRendimientoPesos(List<ElementoRendimientoView> rendimientoPesos) {
		this.rendimientoPesos = rendimientoPesos;
	}

	/**
	 * Gets the rendimiento dolares.
	 *
	 * @return the rendimiento dolares
	 */
	public List<ElementoRendimientoView> getRendimientoDolares() {
		return rendimientoDolares;
	}

	/**
	 * Sets the rendimiento dolares.
	 *
	 * @param rendimientoDolares
	 *            the new rendimiento dolares
	 */
	public void setRendimientoDolares(List<ElementoRendimientoView> rendimientoDolares) {
		this.rendimientoDolares = rendimientoDolares;
	}

}
