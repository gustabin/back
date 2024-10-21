/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class SaldosConsolidadosCuentaDTO.
 */
public class SaldosConsolidadosCuentaDTO {

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The saldo pesos. */
	private BigDecimal saldoPesosValue;

	/** The saldo dolares. */
	private BigDecimal saldoDolaresValue;
	
	/** The host nocturno. */
	private boolean hostNocturno;

	/**
	 * Instantiates a new saldos consolidados cuenta DTO.
	 */
	public SaldosConsolidadosCuentaDTO() {
		super();
		this.saldoPesosValue = null;
		this.saldoDolaresValue = null;
		this.saldoPesos = null;
		this.saldoDolares = null;
		this.hostNocturno = false;
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
	 * Checks if is host nocturno.
	 *
	 * @return true, if is host nocturno
	 */
	public boolean isHostNocturno() {
		return hostNocturno;
	}

	/**
	 * Sets the host nocturno.
	 *
	 * @param hostNocturno the new host nocturno
	 */
	public void setHostNocturno(boolean hostNocturno) {
		this.hostNocturno = hostNocturno;
	}
	
}
