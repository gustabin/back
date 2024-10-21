/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

/**
 * Este objeto devuelve la informacion inherente a un tipo de banca en
 * particular.
 *
 * @author marcelo.ruiz
 */
public class BancaView {

	/** Lista de cuentas de la banca. */
	private List<CuentaTituloView> cuentas = null;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaTituloView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaTituloView> cuentas) {
		this.cuentas = cuentas;
	}

}
