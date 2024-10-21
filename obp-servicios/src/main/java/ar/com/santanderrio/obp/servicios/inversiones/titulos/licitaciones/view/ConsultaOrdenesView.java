/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OrdenesPorCuenta;

/**
 * The Class ConsultaOrdenesView.
 */
public class ConsultaOrdenesView {

	/** The cuentas. */
	private List<OrdenesPorCuenta> cuentas = new ArrayList<OrdenesPorCuenta>();

	/** The mensaje sin ordenes. */
	private String mensajeSinOrdenes;
	
	/** The legales. */
	private String legales;

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

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}
		
}
