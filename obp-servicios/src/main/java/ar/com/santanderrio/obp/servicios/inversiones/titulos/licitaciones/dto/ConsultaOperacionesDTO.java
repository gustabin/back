/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OperacionesPorCuenta;

/**
 * The Class ConsultaOperacionesDTO.
 */
public class ConsultaOperacionesDTO {

	/** The cuentas titulo. */
	private List<OperacionesPorCuenta> cuentasTitulo = new ArrayList<OperacionesPorCuenta>();

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<OperacionesPorCuenta> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<OperacionesPorCuenta> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}
	
	
	
	
}
