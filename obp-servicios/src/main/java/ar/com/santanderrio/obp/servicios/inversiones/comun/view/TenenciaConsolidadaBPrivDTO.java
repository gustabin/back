/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaConsolidadaBPrivDTO.
 */
public class TenenciaConsolidadaBPrivDTO {
	
	/** The lista cuentas. */
	private List<TenenciaPorCuentaBPrivDTO> listaTenenciaPorCuenta = new ArrayList<TenenciaPorCuentaBPrivDTO>();

	/**
	 * Gets the lista tenencia por cuenta.
	 *
	 * @return the lista tenencia por cuenta
	 */
	public List<TenenciaPorCuentaBPrivDTO> getListaTenenciaPorCuenta() {
		return listaTenenciaPorCuenta;
	}

	/**
	 * Sets the lista tenencia por cuenta.
	 *
	 * @param listaTenenciaPorCuenta
	 *            the new lista tenencia por cuenta
	 */
	public void setListaTenenciaPorCuenta(List<TenenciaPorCuentaBPrivDTO> listaTenenciaPorCuenta) {
		this.listaTenenciaPorCuenta = listaTenenciaPorCuenta;
	}

	

}
