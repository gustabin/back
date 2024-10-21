/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoBPrivDTO.
 */
public class RendimientoBPrivDTO {

	/** The tooltip. */
	private String tooltip;

	/** The rendimiento por cuenta. */
	private List<RendimientoPorCuentaDTO> rendimientoPorCuenta = new ArrayList<RendimientoPorCuentaDTO>();

	

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
	 * Gets the rendimiento por cuenta.
	 *
	 * @return the rendimiento por cuenta
	 */
	public List<RendimientoPorCuentaDTO> getRendimientoPorCuenta() {
		return rendimientoPorCuenta;
	}

	/**
	 * Sets the rendimiento por cuenta.
	 *
	 * @param rendimientoPorCuenta
	 *            the new rendimiento por cuenta
	 */
	public void setRendimientoPorCuenta(List<RendimientoPorCuentaDTO> rendimientoPorCuenta) {
		this.rendimientoPorCuenta = rendimientoPorCuenta;
	}

}
