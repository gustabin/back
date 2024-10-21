/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RendimientoBPrivView.
 */
public class RendimientoBPrivView {

	/** The tooltip. */
	private String tooltip;

	/** The rendimiento por cuenta. */
	private List<RendimientoPorCuenta> rendimientoPorCuenta = new ArrayList<RendimientoPorCuenta>();


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
	public List<RendimientoPorCuenta> getRendimientoPorCuenta() {
		return rendimientoPorCuenta;
	}

	/**
	 * Sets the rendimiento por cuenta.
	 *
	 * @param rendimientoPorCuenta
	 *            the new rendimiento por cuenta
	 */
	public void setRendimientoPorCuenta(List<RendimientoPorCuenta> rendimientoPorCuenta) {
		this.rendimientoPorCuenta = rendimientoPorCuenta;
	}

}
