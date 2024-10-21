/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view;

import java.util.List;

/**
 * DatosExtraccionYComprasExteriorView.
 *
 * @author Silvina_Luque
 */
public class DatosTarjetasExtraccionYComprasExteriorView {

	/** The tarjetas. */
	List<TarjetaOperacionExteriorView> tarjetas;

	/** The cuentas. */
	List<CuentaOperacionExteriorView> cuentas;
	
	/** The info legal. */
	String infoLegal;
	
	/** The legal cuenta. */
	String legalCuenta;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaOperacionExteriorView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaOperacionExteriorView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaOperacionExteriorView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaOperacionExteriorView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return the infoLegal
	 */
	public String getInfoLegal() {
		return infoLegal;
	}

	/**
	 * @return the legalCuenta
	 */
	public String getLegalCuenta() {
		return legalCuenta;
	}

	/**
	 * @param infoLegal the infoLegal to set
	 */
	public void setInfoLegal(String infoLegal) {
		this.infoLegal = infoLegal;
	}

	/**
	 * @param legalCuenta the legalCuenta to set
	 */
	public void setLegalCuenta(String legalCuenta) {
		this.legalCuenta = legalCuenta;
	}

}
