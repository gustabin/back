/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

/**
 * The Class ObtenerTenenciasPlazoFijoInView.
 */
public class ObtenerTenenciasPlazoFijoInView {

	/** estadoTotales "OK" si OK estadoTotales "ERROR" si NO OK. */

	@NotNull
	@Pattern(regexp = "OK|WARNING|ERROR")
	private String estadoTotales;

	/**
	 * Gets the estado totales.
	 *
	 * @return the estado totales
	 */
	public String getEstadoTotales() {
		return estadoTotales;
	}

	/**
	 * Sets the estado totales.
	 *
	 * @param estadoTotales
	 *            the new estado totales
	 */
	public void setEstadoTotales(String estadoTotales) {
		this.estadoTotales = estadoTotales;
	}

}
