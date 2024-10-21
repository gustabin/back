/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

/**
 * DatosExtraccionYComprasExteriorView.
 *
 * @author Silvina_Luque
 */
public class DatosPrestamoSueldosView {


	/** The cuentas. */
	List<CuentaPrestamoSueldosView> cuentas;
	
	/** The info legal. */
	String infoLegal;
	
	/** The legal cuenta. */
	String legalCuenta;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaPrestamoSueldosView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas the new cuentas
	 */
	public void setCuentas(List<CuentaPrestamoSueldosView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the info legal.
	 *
	 * @return the info legal
	 */
	public String getInfoLegal() {
		return infoLegal;
	}

	/**
	 * Sets the info legal.
	 *
	 * @param infoLegal the new info legal
	 */
	public void setInfoLegal(String infoLegal) {
		this.infoLegal = infoLegal;
	}

	/**
	 * Gets the legal cuenta.
	 *
	 * @return the legal cuenta
	 */
	public String getLegalCuenta() {
		return legalCuenta;
	}

	/**
	 * Sets the legal cuenta.
	 *
	 * @param legalCuenta the new legal cuenta
	 */
	public void setLegalCuenta(String legalCuenta) {
		this.legalCuenta = legalCuenta;
	}

}
