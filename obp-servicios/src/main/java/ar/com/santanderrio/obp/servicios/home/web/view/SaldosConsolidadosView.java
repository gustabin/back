/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import java.math.BigDecimal;

/**
 * The Class SaldosConsolidadosView.
 */
public class SaldosConsolidadosView {

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The saldo pesos value. */
	private BigDecimal saldoPesosValue;

	/** The saldo dolares value. */
	private BigDecimal saldoDolaresValue;

	/** The unica cuenta. */
	private Boolean unicaCuenta = false;
	/**
	 * Gets the saldo pesos value.
	 *
	 * @return the saldo pesos value
	 */
	public BigDecimal getSaldoPesosValue() {
		return saldoPesosValue;
	}

	/**
	 * Sets the saldo pesos value.
	 *
	 * @param saldoPesosValue
	 *            the new saldo pesos value
	 */
	public void setSaldoPesosValue(BigDecimal saldoPesosValue) {
		this.saldoPesosValue = saldoPesosValue;
	}

	/**
	 * Gets the saldo dolares value.
	 *
	 * @return the saldo dolares value
	 */
	public BigDecimal getSaldoDolaresValue() {
		return saldoDolaresValue;
	}

	/**
	 * Sets the saldo dolares value.
	 *
	 * @param saldoDolaresValue
	 *            the new saldo dolares value
	 */
	public void setSaldoDolaresValue(BigDecimal saldoDolaresValue) {
		this.saldoDolaresValue = saldoDolaresValue;
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
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(String saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the unica cuenta.
	 *
	 * @return the unica cuenta
	 */
	public Boolean getUnicaCuenta() {
		return unicaCuenta;
	}

	/**
	 * Sets the unica cuenta.
	 *
	 * @param unicaCuenta
	 *            the new unica cuenta
	 */
	public void setUnicaCuenta(Boolean unicaCuenta) {
		this.unicaCuenta = unicaCuenta;
	}

}
