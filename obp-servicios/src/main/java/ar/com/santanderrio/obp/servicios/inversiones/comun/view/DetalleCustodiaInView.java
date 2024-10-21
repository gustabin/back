/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

/**
 * The Class DetalleCustodiaInView.
 */
public class DetalleCustodiaInView {

	/** The es banca privada. */
	private Boolean esBancaPrivada = false;
	
	/** The numero cuenta. */
	private String numeroCuenta;


	/**
	 * Gets the es banca privada.
	 *
	 * @return the es banca privada
	 */
	public Boolean getEsBancaPrivada() {
		return esBancaPrivada;
	}

	/**
	 * Sets the es banca privada.
	 *
	 * @param esBancaPrivada
	 *            the new es banca privada
	 */
	public void setEsBancaPrivada(Boolean esBancaPrivada) {
		this.esBancaPrivada = esBancaPrivada;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
}