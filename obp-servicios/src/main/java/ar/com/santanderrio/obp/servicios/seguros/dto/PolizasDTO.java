/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.dto;

import java.util.List;

/**
 * The Class PolizasDTO.
 */
public class PolizasDTO {

	/** The polizas. */
	public List<TenenciasPolizaDTO> polizas;

	/**
	 * Gets the polizas.
	 *
	 * @return the polizas
	 */
	public List<TenenciasPolizaDTO> getPolizas() {
		return polizas;
	}

	/**
	 * Sets the polizas.
	 *
	 * @param polizas
	 *            the new polizas
	 */
	public void setPolizas(List<TenenciasPolizaDTO> polizas) {
		this.polizas = polizas;
	}

}
