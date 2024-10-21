/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.entity;

import java.util.List;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author marcelo.ruiz
 *
 */
public class CuentaTituloOutEntity {

	/** The cuentas tit. */
	private List<CuentaBP> relacionesOperativaTitulo;

	/**
	 * Gets the cuentas tit.
	 *
	 * @return the cuentas tit
	 */
	public List<CuentaBP> relacionesOperativaTitulo() {
		return relacionesOperativaTitulo;
	}

	/**
	 * Sets the cuentas tit.
	 *
	 * @param relacionesOperativaTitulo
	 *            the new cuentas tit
	 */
	public void relacionesOperativaTitulo(List<CuentaBP> relacionesOperativaTitulo) {
		this.relacionesOperativaTitulo = relacionesOperativaTitulo;
	}

}
