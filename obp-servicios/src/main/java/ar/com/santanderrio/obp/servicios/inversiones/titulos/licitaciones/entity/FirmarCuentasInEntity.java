/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FirmarCuentasInEntity.
 */
public class FirmarCuentasInEntity {

	/** The email. */
	private String email;

	/** The razon social. */
	private String razonSocial = "4";

	/** The cuentasPorFirmarList. */
	private List<CuentasPorFirmarEntity> cuentasPorFirmarList = new ArrayList<CuentasPorFirmarEntity>();
	

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the razon social.
	 *
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Sets the razon social.
	 *
	 * @param razonSocial
	 *            the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * Gets the cuentas por firmar list.
	 *
	 * @return the cuentas por firmar list
	 */
	public List<CuentasPorFirmarEntity> getCuentasPorFirmarList() {
		return cuentasPorFirmarList;
	}

	/**
	 * Sets the cuentas por firmar list.
	 *
	 * @param cuentasPorFirmarList
	 *            the new cuentas por firmar list
	 */
	public void setCuentasPorFirmarList(List<CuentasPorFirmarEntity> cuentasPorFirmarList) {
		this.cuentasPorFirmarList = cuentasPorFirmarList;
	}

}
