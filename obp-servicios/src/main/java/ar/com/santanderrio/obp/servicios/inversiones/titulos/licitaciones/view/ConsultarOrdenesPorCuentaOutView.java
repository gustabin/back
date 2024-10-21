/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesPorCuentaDTO;

/**
 * The Class ConsultarOrdenesPorCuentaOutView.
 */
public class ConsultarOrdenesPorCuentaOutView {

	/** The cuentas. */
	List<ConsultarOrdenesPorCuentaDTO> cuentas;

	/** The mensaje sin ordenes. */
	String mensajeSinOrdenes;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<ConsultarOrdenesPorCuentaDTO> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<ConsultarOrdenesPorCuentaDTO> cuentas) {
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
