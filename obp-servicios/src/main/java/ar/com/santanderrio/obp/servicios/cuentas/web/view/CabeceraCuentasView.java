/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.math.BigDecimal;

/**
 * The Class CabeceraCuentasView.
 */
public class CabeceraCuentasView {

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo pesos value. */
	private BigDecimal saldoPesosValue;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The saldo dolares value. */
	private BigDecimal saldoDolaresValue;

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

}
