/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoDTO.
 */
public class RendimientoDTO {

	/** The tooltip. */
	private String tooltip;
	
	/** The informacion parcial. */
	private boolean informacionParcial = false;
	
	/** The rendimiento pesos. */
	private List<ElementoRendimientoDTO> rendimientoPesos = new ArrayList<ElementoRendimientoDTO>();
	
	/** The rendimiento dolares. */
	private List<ElementoRendimientoDTO> rendimientoDolares = new ArrayList<ElementoRendimientoDTO>();

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
	public List<ElementoRendimientoDTO> getRendimientoPesos() {
		return rendimientoPesos;
	}

	/**
	 * Sets the rendimiento pesos.
	 *
	 * @param rendimientoPesos
	 *            the new rendimiento pesos
	 */
	public void setRendimientoPesos(List<ElementoRendimientoDTO> rendimientoPesos) {
		this.rendimientoPesos = rendimientoPesos;
	}

	/**
	 * Gets the rendimiento dolares.
	 *
	 * @return the rendimiento dolares
	 */
	public List<ElementoRendimientoDTO> getRendimientoDolares() {
		return rendimientoDolares;
	}

	/**
	 * Sets the rendimiento dolares.
	 *
	 * @param rendimientoDolares
	 *            the new rendimiento dolares
	 */
	public void setRendimientoDolares(List<ElementoRendimientoDTO> rendimientoDolares) {
		this.rendimientoDolares = rendimientoDolares;
	}

}
