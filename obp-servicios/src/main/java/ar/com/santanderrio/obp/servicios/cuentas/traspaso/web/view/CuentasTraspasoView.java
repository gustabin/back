/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.view;

import java.io.Serializable;

/**
 * The Class CuentasTraspasoView.
 */
public class CuentasTraspasoView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero cuenta. */
	private String numero;

	/** The tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The alias cuenta. */
	private String alias;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The isSaldoPesosNegativo. */
	private Boolean isSaldoPesosNegativo;

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the alias cuenta.
	 *
	 * @return the aliasCuenta
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias cuenta.
	 *
	 * @param alias
	 *            the aliasCuenta to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the isSaldoPesosNegativo
	 */
	public Boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the isSaldoPesosNegativo to set
	 */
	public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
		this.isSaldoPesosNegativo = isSaldoPesosNegativo;
	}

}
