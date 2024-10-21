/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase resultado del servicio TenenciaValuadaCuentaProductoOnline.
 *
 * @author marcelo.ruiz
 */
public class ResultadoCuentaProductoOL {

	/** The guid error. */
	@JsonProperty("GUIDError")
	private String guidError;

	/** The amortizaciones. */
	@JsonProperty("Amortizaciones")
	private String amortizaciones;

	/** The amortizacionesReexp. */
	@JsonProperty("AmortizacionesReexp")
	private String amortizacionesReexp;

	/** The codProducto. */
	@JsonProperty("CodProducto")
	private String codProducto;

	/** The dividendos. */
	@JsonProperty("Dividendos")
	private String dividendos;

	/** The dividendosReexp. */
	@JsonProperty("DividendosReexp")
	private String dividendosReexp;

	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;

	/** The Numero de Cuenta. */
	@JsonProperty("NumeroCuenta")
	private String numeroCuenta;

	/** The rentas. */
	@JsonProperty("Rentas")
	private String rentas;

	/** The rentasReexp. */
	@JsonProperty("RentasReexp")
	private String rentasReexp;

	/** The Resultado Bruto. */
	@JsonProperty("ResultadoBruto")
	private String resultadoBruto;

	/** The Resultado Bruto Corregido. */
	@JsonProperty("ResultadoBrutoCorregido")
	private String resultadoBrutoCorregido;

	/** The Resultado Bruto Corregido Reexp. */
	@JsonProperty("ResultadoBrutoCorregidoReexp")
	private String resultadoBrutoCorregidoReexp;

	/** The Resultado Bruto Reexp. */
	@JsonProperty("ResultadoBrutoReexp")
	private String resultadoBrutoReexp;

	/** The sucursal. */
	@JsonProperty("Sucursal")
	private String sucursal;

	/** The Tenencia Valuada. */
	@JsonProperty("TenenciaValuada")
	private String tenenciaValuada;

	/** The Tenencia Valuada Compra. */
	@JsonProperty("TenenciaValuadaCompra")
	private String tenenciaValuadaCompra;

	/** The Tenencia Valuada CompraReexp. */
	@JsonProperty("TenenciaValuadaCompraReexp")
	private String tenenciaValuadaCompraReexp;

	/** The Tenencia ValuadaReexp. */
	@JsonProperty("TenenciaValuadaReexp")
	private String tenenciaValuadaReexp;

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
	 * Gets the cod producto.
	 *
	 * @return the cod producto
	 */
	public String getCodProducto() {
		return codProducto;
	}

	/**
	 * Sets the cod producto.
	 *
	 * @param codProducto
	 *            the new cod producto
	 */
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the resultado bruto.
	 *
	 * @return the resultado bruto
	 */
	public String getResultadoBruto() {
		return resultadoBruto;
	}

	/**
	 * Sets the resultado bruto.
	 *
	 * @param resultadoBruto
	 *            the new resultado bruto
	 */
	public void setResultadoBruto(String resultadoBruto) {
		this.resultadoBruto = resultadoBruto;
	}

	/**
	 * Gets the resultado bruto corregido.
	 *
	 * @return the resultado bruto corregido
	 */
	public String getResultadoBrutoCorregido() {
		return resultadoBrutoCorregido;
	}

	/**
	 * Sets the resultado bruto corregido.
	 *
	 * @param resultadoBrutoCorregido
	 *            the new resultado bruto corregido
	 */
	public void setResultadoBrutoCorregido(String resultadoBrutoCorregido) {
		this.resultadoBrutoCorregido = resultadoBrutoCorregido;
	}

	/**
	 * Gets the resultado bruto corregido reexp.
	 *
	 * @return the resultado bruto corregido reexp
	 */
	public String getResultadoBrutoCorregidoReexp() {
		return resultadoBrutoCorregidoReexp;
	}

	/**
	 * Sets the resultado bruto corregido reexp.
	 *
	 * @param resultadoBrutoCorregidoReexp
	 *            the new resultado bruto corregido reexp
	 */
	public void setResultadoBrutoCorregidoReexp(String resultadoBrutoCorregidoReexp) {
		this.resultadoBrutoCorregidoReexp = resultadoBrutoCorregidoReexp;
	}

	/**
	 * Gets the resultado bruto reexp.
	 *
	 * @return the resultado bruto reexp
	 */
	public String getResultadoBrutoReexp() {
		return resultadoBrutoReexp;
	}

	/**
	 * Sets the resultado bruto reexp.
	 *
	 * @param resultadoBrutoReexp
	 *            the new resultado bruto reexp
	 */
	public void setResultadoBrutoReexp(String resultadoBrutoReexp) {
		this.resultadoBrutoReexp = resultadoBrutoReexp;
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
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public String getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(String tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the tenencia valuada compra.
	 *
	 * @return the tenencia valuada compra
	 */
	public String getTenenciaValuadaCompra() {
		return tenenciaValuadaCompra;
	}

	/**
	 * Sets the tenencia valuada compra.
	 *
	 * @param tenenciaValuadaCompra
	 *            the new tenencia valuada compra
	 */
	public void setTenenciaValuadaCompra(String tenenciaValuadaCompra) {
		this.tenenciaValuadaCompra = tenenciaValuadaCompra;
	}

	/**
	 * Gets the tenencia valuada compra reexp.
	 *
	 * @return the tenencia valuada compra reexp
	 */
	public String getTenenciaValuadaCompraReexp() {
		return tenenciaValuadaCompraReexp;
	}

	/**
	 * Sets the tenencia valuada compra reexp.
	 *
	 * @param tenenciaValuadaCompraReexp
	 *            the new tenencia valuada compra reexp
	 */
	public void setTenenciaValuadaCompraReexp(String tenenciaValuadaCompraReexp) {
		this.tenenciaValuadaCompraReexp = tenenciaValuadaCompraReexp;
	}

	/**
	 * Gets the tenencia valuada reexp.
	 *
	 * @return the tenencia valuada reexp
	 */
	public String getTenenciaValuadaReexp() {
		return tenenciaValuadaReexp;
	}

	/**
	 * Sets the tenencia valuada reexp.
	 *
	 * @param tenenciaValuadaReexp
	 *            the new tenencia valuada reexp
	 */
	public void setTenenciaValuadaReexp(String tenenciaValuadaReexp) {
		this.tenenciaValuadaReexp = tenenciaValuadaReexp;
	}

	/**
	 * Gets the amortizaciones.
	 *
	 * @return the amortizaciones
	 */
	public String getAmortizaciones() {
		return amortizaciones;
	}

	/**
	 * Sets the amortizaciones.
	 *
	 * @param amortizaciones
	 *            the new amortizaciones
	 */
	public void setAmortizaciones(String amortizaciones) {
		this.amortizaciones = amortizaciones;
	}

	/**
	 * Gets the amortizaciones reexp.
	 *
	 * @return the amortizaciones reexp
	 */
	public String getAmortizacionesReexp() {
		return amortizacionesReexp;
	}

	/**
	 * Sets the amortizaciones reexp.
	 *
	 * @param amortizacionesReexp
	 *            the new amortizaciones reexp
	 */
	public void setAmortizacionesReexp(String amortizacionesReexp) {
		this.amortizacionesReexp = amortizacionesReexp;
	}

	/**
	 * Gets the dividendos.
	 *
	 * @return the dividendos
	 */
	public String getDividendos() {
		return dividendos;
	}

	/**
	 * Sets the dividendos.
	 *
	 * @param dividendos
	 *            the new dividendos
	 */
	public void setDividendos(String dividendos) {
		this.dividendos = dividendos;
	}

	/**
	 * Gets the dividendos reexp.
	 *
	 * @return the dividendos reexp
	 */
	public String getDividendosReexp() {
		return dividendosReexp;
	}

	/**
	 * Sets the dividendos reexp.
	 *
	 * @param dividendosReexp
	 *            the new dividendos reexp
	 */
	public void setDividendosReexp(String dividendosReexp) {
		this.dividendosReexp = dividendosReexp;
	}

	/**
	 * Gets the rentas.
	 *
	 * @return the rentas
	 */
	public String getRentas() {
		return rentas;
	}

	/**
	 * Sets the rentas.
	 *
	 * @param rentas
	 *            the new rentas
	 */
	public void setRentas(String rentas) {
		this.rentas = rentas;
	}

	/**
	 * Gets the rentas reexp.
	 *
	 * @return the rentas reexp
	 */
	public String getRentasReexp() {
		return rentasReexp;
	}

	/**
	 * Sets the rentas reexp.
	 *
	 * @param rentasReexp
	 *            the new rentas reexp
	 */
	public void setRentasReexp(String rentasReexp) {
		this.rentasReexp = rentasReexp;
	}
}
