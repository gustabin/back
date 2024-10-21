/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class SaldosPDC.
 */
public class SaldosPDC {
	
	/** The tipo cuenta. */
	@JsonProperty("TipoCuenta")
	private String tipoCuenta;
	
	/** The sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;
	
	/** The numero cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;
	
	/** The fecha saldo compra. */
	@JsonProperty("FechaSaldoCompra")
	private String fechaSaldoCompra;
	
	/** The saldo PDC. */
	@JsonProperty("SaldoPdc")
	private double saldoPDC;

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
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the fecha saldo compra.
	 *
	 * @return the fechaSaldoCompra
	 */
	public String getFechaSaldoCompra() {
		return fechaSaldoCompra;
	}

	/**
	 * Sets the fecha saldo compra.
	 *
	 * @param fechaSaldoCompra
	 *            the fechaSaldoCompra to set
	 */
	public void setFechaSaldoCompra(String fechaSaldoCompra) {
		this.fechaSaldoCompra = fechaSaldoCompra;
	}

	/**
	 * Gets the saldo PDC.
	 *
	 * @return the saldoPDC
	 */
	public double getSaldoPDC() {
		return saldoPDC;
	}

	/**
	 * Sets the saldo PDC.
	 *
	 * @param saldoPDC
	 *            the saldoPDC to set
	 */
	public void setSaldoPDC(double saldoPDC) {
		this.saldoPDC = saldoPDC;
	}
}
