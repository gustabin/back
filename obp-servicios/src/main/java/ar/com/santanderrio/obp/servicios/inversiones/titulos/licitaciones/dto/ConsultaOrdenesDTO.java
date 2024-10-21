/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OrdenesPorCuenta;

/**
 * The Class ConsultaOrdenesDTO.
 */
public class ConsultaOrdenesDTO {

	/** The cuentas. */
	private List<OrdenesPorCuenta> cuentas = new ArrayList<OrdenesPorCuenta>();

	/** The mensaje sin ordenes. */
	private String mensajeSinOrdenes;

	
	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<OrdenesPorCuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<OrdenesPorCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the mensaje sin ordenes.
	 *
	 * @return the mensaje sin ordenes
	 */
	public String getMensajeSinOrdenes() {
		return mensajeSinOrdenes;
	}

	/**
	 * Sets the mensaje sin ordenes.
	 *
	 * @param mensajeSinOrdenes
	 *            the new mensaje sin ordenes
	 */
	public void setMensajeSinOrdenes(String mensajeSinOrdenes) {
		this.mensajeSinOrdenes = mensajeSinOrdenes;
	}
	
}
