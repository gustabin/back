/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase resultado del servicio TenenciaValuadaDetalleFondoOL.
 *
 * @author marcelo.ruiz
 */
public class ResultadoTenenciaValuadaDetalleFondoOL {

	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;

	/** The numero cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;

	/** The sucursal cuenta. */
	@JsonProperty("SucursalCuenta")
	private int sucursalCuenta;

	/** The codigo fondo. */
	@JsonProperty("CodigoFondo")
	private int codigoFondo;

	/** The descripcion fondo. */
	@JsonProperty("DescripcionFondo")
	private String descripcionFondo;

	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The cantidad cuotapartes. */
	@JsonProperty("CantidadCuotapartes")
	private BigDecimal cantidadCuotapartes;

	/** The valor cuotaparte. */
	@JsonProperty("ValorCuotaparte")
	private BigDecimal valorCuotaparte;

	/** The p PPC. */
	@JsonProperty("PPPC")
	private BigDecimal pPPC;

	/** The tenencia valuada. */
	@JsonProperty("TenenciaValuada")
	private BigDecimal tenenciaValuada;

	/** The tenencia valuada reexp. */
	@JsonProperty("TenenciaValuadaReexp")
	private BigDecimal tenenciaValuadaReexp;

	/** The tenencia valuada compra. */
	@JsonProperty("TenenciaValuadaCompra")
	private BigDecimal tenenciaValuadaCompra;

	/** The tenencia valuada compra reexp. */
	@JsonProperty("TenenciaValuadaCompraReexp")
	private BigDecimal tenenciaValuadaCompraReexp;

	/** The resultado bruto. */
	@JsonProperty("ResultadoBruto")
	private BigDecimal resultadoBruto;

	/** The resultado bruto reexp. */
	@JsonProperty("ResultadoBrutoReexp")
	private BigDecimal resultadoBrutoReexp;

	/** The resultado bruto reexp. */
	@JsonProperty("FechaBloqueo")
	private String fechaBloqueo;

	/**
	 * Gets the guid error.
	 *
	 * @return the guidError
	 */
	public String getGuidError() {
		return guidError;
	}

	/**
	 * Sets the guid error.
	 *
	 * @param guidError
	 *            the guidError to set
	 */
	public void setGuidError(String guidError) {
		this.guidError = guidError;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		if (numeroCuenta != null) {
			return numeroCuenta.trim();
		}
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
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursalCuenta
	 */
	public int getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the sucursalCuenta to set
	 */
	public void setSucursalCuenta(int sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public int getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(int codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the descripcion fondo.
	 *
	 * @return the descripcionFondo
	 */
	public String getDescripcionFondo() {
		return descripcionFondo;
	}

	/**
	 * Sets the descripcion fondo.
	 *
	 * @param descripcionFondo
	 *            the descripcionFondo to set
	 */
	public void setDescripcionFondo(String descripcionFondo) {
		this.descripcionFondo = descripcionFondo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the cantidad cuotapartes.
	 *
	 * @return the cantidadCuotapartes
	 */
	public BigDecimal getCantidadCuotapartes() {
		return cantidadCuotapartes;
	}

	/**
	 * Sets the cantidad cuotapartes.
	 *
	 * @param cantidadCuotapartes
	 *            the cantidadCuotapartes to set
	 */
	public void setCantidadCuotapartes(BigDecimal cantidadCuotapartes) {
		this.cantidadCuotapartes = cantidadCuotapartes;
	}

	/**
	 * Gets the valor cuotaparte.
	 *
	 * @return the valorCuotaparte
	 */
	public BigDecimal getValorCuotaparte() {
		return valorCuotaparte;
	}

	/**
	 * Sets the valor cuotaparte.
	 *
	 * @param valorCuotaparte
	 *            the valorCuotaparte to set
	 */
	public void setValorCuotaparte(BigDecimal valorCuotaparte) {
		this.valorCuotaparte = valorCuotaparte;
	}

	/**
	 * Gets the p PPC.
	 *
	 * @return the pPPC
	 */
	public BigDecimal getpPPC() {
		return pPPC;
	}

	/**
	 * Sets the p PPC.
	 *
	 * @param pPPC
	 *            the pPPC to set
	 */
	public void setpPPC(BigDecimal pPPC) {
		this.pPPC = pPPC;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenenciaValuada
	 */
	public BigDecimal getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the tenenciaValuada to set
	 */
	public void setTenenciaValuada(BigDecimal tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenenciaValuadaReexp
	 */
	public BigDecimal getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the tenenciaValuadaReexp to set
	 */
	public void setTenenciaValuadaReexp(BigDecimal tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

	/**
	 * Gets the tenencia valuada compra.
	 *
	 * @return the tenenciaValuadaCompra
	 */
	public BigDecimal getTenenciaValuadaCompra() {
		return tenenciaValuadaCompra;
	}

	/**
	 * Sets the tenencia valuada compra.
	 *
	 * @param tenenciaValuadaCompra
	 *            the tenenciaValuadaCompra to set
	 */
	public void setTenenciaValuadaCompra(BigDecimal tenenciaValuadaCompra) {
		this.tenenciaValuadaCompra = tenenciaValuadaCompra;
	}

	/**
	 * Gets the tenencia valuada compra reexp.
	 *
	 * @return the tenenciaValuadaCompraReexp
	 */
	public BigDecimal getTenenciaValuadaCompraReexp() {
		return tenenciaValuadaCompraReexp;
	}

	/**
	 * Sets the tenencia valuada compra reexp.
	 *
	 * @param tenenciaValuadaCompraReexp
	 *            the tenenciaValuadaCompraReexp to set
	 */
	public void setTenenciaValuadaCompraReexp(BigDecimal tenenciaValuadaCompraReexp) {
		this.tenenciaValuadaCompraReexp = tenenciaValuadaCompraReexp;
	}

	/**
	 * Gets the resultado bruto.
	 *
	 * @return the resultadoBruto
	 */
	public BigDecimal getResultadoBruto() {
		return resultadoBruto;
	}

	/**
	 * Sets the resultado bruto.
	 *
	 * @param resultadoBruto
	 *            the resultadoBruto to set
	 */
	public void setResultadoBruto(BigDecimal resultadoBruto) {
		this.resultadoBruto = resultadoBruto;
	}

	/**
	 * Gets the resultado bruto reexp.
	 *
	 * @return the resultadoBrutoReexp
	 */
	public BigDecimal getResultadoBrutoReexp() {
		return resultadoBrutoReexp;
	}

	/**
	 * Sets the resultado bruto reexp.
	 *
	 * @param resultadoBrutoReexp
	 *            the resultadoBrutoReexp to set
	 */
	public void setResultadoBrutoReexp(BigDecimal resultadoBrutoReexp) {
		this.resultadoBrutoReexp = resultadoBrutoReexp;
	}

	/**
	 * Gets the fecha bloqueo.
	 *
	 * @return the fechaBloqueo
	 */
	public String getFechaBloqueo() {
		return fechaBloqueo;
	}

	/**
	 * Sets the fecha bloqueo.
	 *
	 * @param fechaBloqueo
	 *            the fechaBloqueo to set
	 */
	public void setFechaBloqueo(String fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

}
