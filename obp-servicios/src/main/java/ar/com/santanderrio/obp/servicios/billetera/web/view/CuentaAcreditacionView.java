/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.view;

/**
 * The Class CuentaAcreditacionView.
 */
public class CuentaAcreditacionView {

	/** The defaultCta. */
	private boolean defaultCta;

	/** The alias. */
	private String alias;

	/** The descripcion tipo cuenta. */
	private String descripcionTipoCuenta;

	/** The id. */
	private String id;

	/** The numero. */
	private String numero;

	/** The saldo pesos. */
	private String saldoPesos;

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Gets the default cta.
	 *
	 * @return the defaultCta
	 */
	public boolean getDefaultCta() {
		return defaultCta;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
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

	/**
	 * Sets the default cta.
	 *
	 * @param defaultCta
	 *            the defaultCta to set
	 */
	public void setDefaultCta(boolean defaultCta) {
		this.defaultCta = defaultCta;
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
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(String saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

}
