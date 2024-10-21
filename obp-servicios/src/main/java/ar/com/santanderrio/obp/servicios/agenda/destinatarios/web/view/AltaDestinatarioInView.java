/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import javax.validation.constraints.Pattern;

/**
 * The Class AltaDestinatarioInView.
 */
public class AltaDestinatarioInView {

	/** The nro cuenta. */
	@Pattern(regexp = "^[0-9]{3}[-][0-9]{6}[/][0-9]$")
	private String nroCuenta;

	/** The tipo cuenta. */
	@Pattern(regexp = "^[0-9]{2}$")
	private String idTipoCuenta;

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getIdTipoCuenta() {
		return idTipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setIdTipoCuenta(String tipoCuenta) {
		this.idTipoCuenta = tipoCuenta;
	}

}
