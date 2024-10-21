/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

/**
 * The Class ResumenCuentaPesosView.
 */
public class ResumenCuentaPesosView {

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The numero. */
	private String numero;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The signo saldo pesos. */
	private String signoSaldoPesos;

	/** The is saldo pesos negativo. */
	private Boolean isSaldoPesosNegativo;

	/** The is saldo pesos cero. */
	private Boolean isSaldoPesosCero;

	/** The alias. */
	private String alias;

	/**
	 * Instantiates a new resumen cuenta pesos view.
	 */
	public ResumenCuentaPesosView() {

	}

	/**
	 * Instantiates a new resumen cuenta pesos view.
	 *
	 * @param cuentaView
	 *            the cuenta view
	 */
	public ResumenCuentaPesosView(CuentasAdhesionDebitoView cuentaView) {
		this.descripcionTipoCuenta = cuentaView.getDescripcionTipoCuenta();
		this.numero = cuentaView.getNumero();
		this.saldoPesos = cuentaView.getSaldoPesos();
		this.signoSaldoPesos = cuentaView.getSignoSaldoPesos();
		this.isSaldoPesosNegativo = cuentaView.getIsSaldoPesosNegativo();
		this.isSaldoPesosCero = cuentaView.getIsSaldoPesosCero();
		this.alias = cuentaView.getAlias();
	}

	/**
	 * Gets the descripcion tipo cuenta.
	 *
	 * @return the descripcionTipoCuenta
	 */
	public String getDescripcionTipoCuenta() {
		return descripcionTipoCuenta;
	}

	/**
	 * Sets the descripcion tipo cuenta.
	 *
	 * @param descripcionTipoCuenta
	 *            the descripcionTipoCuenta to set
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
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * Gets the signo saldo pesos.
	 *
	 * @return the signoSaldoPesos
	 */
	public String getSignoSaldoPesos() {
		return signoSaldoPesos;
	}

	/**
	 * Sets the signo saldo pesos.
	 *
	 * @param signoSaldoPesos
	 *            the signoSaldoPesos to set
	 */
	public void setSignoSaldoPesos(String signoSaldoPesos) {
		this.signoSaldoPesos = signoSaldoPesos;
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

	/**
	 * Gets the checks if is saldo pesos cero.
	 *
	 * @return the isSaldoPesosCero
	 */
	public Boolean getIsSaldoPesosCero() {
		return isSaldoPesosCero;
	}

	/**
	 * Sets the checks if is saldo pesos cero.
	 *
	 * @param isSaldoPesosCero
	 *            the isSaldoPesosCero to set
	 */
	public void setIsSaldoPesosCero(Boolean isSaldoPesosCero) {
		this.isSaldoPesosCero = isSaldoPesosCero;
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
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
