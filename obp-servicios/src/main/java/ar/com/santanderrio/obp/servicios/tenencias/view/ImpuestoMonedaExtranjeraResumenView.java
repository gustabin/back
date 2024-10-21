/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

/**
 * The Class ImpuestoMonedaExtranjeraResumenView.
 */
public class ImpuestoMonedaExtranjeraResumenView {

	/** The cuenta. */
	private String cuenta;

	/** The anio. */
	private String anio;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The impuestos moneda extranjera. */
	private List<ImpuestoMonedaExtranjeraView> impuestosMonedaExtranjera;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio
	 *            the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the impuestos moneda extranjera.
	 *
	 * @return the custodias
	 */
	public List<ImpuestoMonedaExtranjeraView> getImpuestosMonedaExtranjera() {
		return impuestosMonedaExtranjera;
	}

	/**
	 * Sets the impuestos moneda extranjera.
	 *
	 * @param custodias
	 *            the custodias to set
	 */
	public void setImpuestosMonedaExtranjera(List<ImpuestoMonedaExtranjeraView> custodias) {
		this.impuestosMonedaExtranjera = custodias;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
