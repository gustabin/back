/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.view;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class CuentasAdhesionDebitoAutomaticoView.
 */
public class CuentasAdhesionDebitoAutomaticoView {

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The numero. */
	private String numero;

	/** The cbu. */
	private String cbu;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The signo saldo pesos. */
	private String signoSaldoPesos;

	/** The alias. */
	private String alias;

	/** The is saldo pesos negativo. */
	private Boolean isSaldoPesosNegativo;

	/** esta definido asi por el componente generico de angular. */
	private Boolean showSaldoPesos = true;

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcion tipo cuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the new descripcion tipo cuenta
	 */
	public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
		this.descripcionTipoCuenta = descripcionTipoCuenta;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the signo saldo pesos.
	 *
	 * @return the signo saldo pesos
	 */
	public String getSignoSaldoPesos() {
		return signoSaldoPesos;
	}

	/**
	 * Sets the signo saldo pesos.
	 *
	 * @param signoSaldoPesos
	 *            the new signo saldo pesos
	 */
	public void setSignoSaldoPesos(String signoSaldoPesos) {
		if (StringUtils.isNotBlank(signoSaldoPesos)) {
			this.isSaldoPesosNegativo = ("-".equals(signoSaldoPesos));
		}
		this.signoSaldoPesos = signoSaldoPesos;
	}

	/**
	 * Gets the checks if is saldo pesos negativo.
	 *
	 * @return the checks if is saldo pesos negativo
	 */
	public Boolean getIsSaldoPesosNegativo() {
		return isSaldoPesosNegativo;
	}

	/**
	 * Sets the checks if is saldo pesos negativo.
	 *
	 * @param isSaldoPesosNegativo
	 *            the new checks if is saldo pesos negativo
	 */
	public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
		this.isSaldoPesosNegativo = isSaldoPesosNegativo;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the show saldo pesos.
	 *
	 * @return the show saldo pesos
	 */
	public Boolean getShowSaldoPesos() {
		return showSaldoPesos;
	}

	/**
	 * Sets the show saldo pesos.
	 *
	 * @param showSaldoPesos
	 *            the new show saldo pesos
	 */
	public void setShowSaldoPesos(Boolean showSaldoPesos) {
		this.showSaldoPesos = showSaldoPesos;
	}

}
