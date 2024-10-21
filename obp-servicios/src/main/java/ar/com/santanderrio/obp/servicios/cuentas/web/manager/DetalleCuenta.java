/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class DetalleCuenta.
 */
public class DetalleCuenta {

	/** The identificacion cuenta. */
	private IdentificacionCuenta identificacionCuenta;

	/** The is traspaso automatico. */
	private Boolean isTraspasoAutomatico;

	/**
	 * Gets the identificacion cuenta.
	 *
	 * @return the identificacion cuenta
	 */
	public IdentificacionCuenta getIdentificacionCuenta() {
		return identificacionCuenta;
	}

	/**
	 * Setter para identificacion cuenta.
	 *
	 * @param identificacionCuenta
	 *            el nuevo identificacion cuenta
	 */
	public void setIdentificacionCuenta(IdentificacionCuenta identificacionCuenta) {
		this.identificacionCuenta = identificacionCuenta;
	}

	/**
	 * Gets the checks if is traspaso automatico.
	 *
	 * @return the checks if is traspaso automatico
	 */
	public Boolean getIsTraspasoAutomatico() {
		return isTraspasoAutomatico;
	}

	/**
	 * Setter para checks if is traspaso automatico.
	 *
	 * @param isTraspasoAutomatico
	 *            el nuevo checks if is traspaso automatico
	 */
	public void setIsTraspasoAutomatico(Boolean isTraspasoAutomatico) {
		this.isTraspasoAutomatico = isTraspasoAutomatico;
	}

}
