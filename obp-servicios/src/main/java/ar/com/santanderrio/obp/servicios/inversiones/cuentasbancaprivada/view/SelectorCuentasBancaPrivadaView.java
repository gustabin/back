/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view;

import java.util.List;

/**
 * The Class SelectorCuentasBancaPrivadaView.
 */
public class SelectorCuentasBancaPrivadaView {

	/** The cuentas. */
	private List<CuentaBancaPrivadaSelectorView> cuentas;

	/** The selected. */
	private int selected;
	
	/** The legales. */
	private String legales;
	
	/** The unica cuenta. */
	private Boolean unicaCuenta = Boolean.FALSE;

	
	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaBancaPrivadaSelectorView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaBancaPrivadaSelectorView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public int getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected
	 *            the new selected
	 */
	public void setSelected(int selected) {
		this.selected = selected;
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

	/**
	 * Gets the unica cuenta.
	 *
	 * @return the unica cuenta
	 */
	public Boolean getUnicaCuenta() {
		return unicaCuenta;
	}

	/**
	 * Sets the unica cuenta.
	 *
	 * @param unicaCuenta
	 *            the new unica cuenta
	 */
	public void setUnicaCuenta(Boolean unicaCuenta) {
		this.unicaCuenta = unicaCuenta;
	}

}