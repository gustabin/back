/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

/**
 * The Class TendenciasCuentasExcelEntity.
 */
public class TendenciasCuentasExcelEntity {

	/** The cuenta. */
	private String cuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The pecodigofi. */
	private String pecodigofi;

	/** The cbu. */
	private String cbu;

	/** The saldo. */
	private String saldo;

	/**
	 * Instantiates a new tendencias cuentas excel entity.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param pecodigofi
	 *            the pecodigofi
	 * @param cbu
	 *            the cbu
	 * @param saldo
	 *            the saldo
	 */
	public TendenciasCuentasExcelEntity(String cuenta, String tipoCuenta, String pecodigofi, String cbu, String saldo) {
		super();
		this.tipoCuenta = tipoCuenta;
		this.pecodigofi = pecodigofi;
		this.cbu = cbu;
		this.saldo = saldo;
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the pecodigofi.
	 *
	 * @return the pecodigofi
	 */
	public String getPecodigofi() {
		return pecodigofi;
	}

	/**
	 * Sets the pecodigofi.
	 *
	 * @param pecodigofi
	 *            the pecodigofi to set
	 */
	public void setPecodigofi(String pecodigofi) {
		this.pecodigofi = pecodigofi;
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
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
