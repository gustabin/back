package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class CancelacionAnticipadaOutView {

	@JsonProperty("loanNumber")
	private String nroPrestamo;
	@JsonProperty("acountNumber")
	private String nroCuenta;
	@JsonProperty("totalAmount")
	private BigDecimal montoTotal;
	@JsonProperty("capitalAmount")
	private BigDecimal montoCapital;
	@JsonProperty("interestAmount")
	private BigDecimal montoInteres;
	@JsonProperty("iva")
	private BigDecimal iva;
	@JsonProperty("ivaAdditional")
	private BigDecimal ivaAdicional;
	@JsonProperty("ivaTotalAmount")
	private BigDecimal ivaTotal;
	@JsonProperty("otherTaxesAmount")
	private BigDecimal otroImpuestos;
	@JsonProperty("financialDebtTaxAmount")
	private BigDecimal impuestoFinanciero;
	@JsonProperty("iibbTaxAmount")
	private BigDecimal iibb;
	@JsonProperty("commissionTotalAmount")
	private BigDecimal montoComisiones;
	@JsonProperty("totalAmountUE")
	private BigDecimal montoTotalUE;
	@JsonProperty("capitalAmountUE")
	private BigDecimal montoCapitalUE;
	@JsonProperty("interestAmountUE")
	private BigDecimal montoInteresUE;
	@JsonProperty("expositionUnitCotizationUE")
	private BigDecimal cotizacionUE;
	@JsonProperty("cotizationDateUE")
	private String fechaCotizacionUE;
	@JsonProperty("nio")
	private String nio;

	public CancelacionAnticipadaOutView() {
		super();
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public BigDecimal getMontoCapital() {
		return montoCapital;
	}

	public void setMontoCapital(BigDecimal montoCapital) {
		this.montoCapital = montoCapital;
	}

	public BigDecimal getMontoInteres() {
		return montoInteres;
	}

	public void setMontoInteres(BigDecimal montoInteres) {
		this.montoInteres = montoInteres;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getIvaAdicional() {
		return ivaAdicional;
	}

	public void setIvaAdicional(BigDecimal ivaAdicional) {
		this.ivaAdicional = ivaAdicional;
	}

	public BigDecimal getIvaTotal() {
		return ivaTotal;
	}

	public void setIvaTotal(BigDecimal ivaTotal) {
		this.ivaTotal = ivaTotal;
	}

	public BigDecimal getOtroImpuestos() {
		return otroImpuestos;
	}

	public void setOtroImpuestos(BigDecimal otroImpuestos) {
		this.otroImpuestos = otroImpuestos;
	}

	public BigDecimal getImpuestoFinanciero() {
		return impuestoFinanciero;
	}

	public void setImpuestoFinanciero(BigDecimal impuestoFinanciero) {
		this.impuestoFinanciero = impuestoFinanciero;
	}

	public BigDecimal getIibb() {
		return iibb;
	}

	public void setIibb(BigDecimal iibb) {
		this.iibb = iibb;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}

	public BigDecimal getMontoComisiones() {
		return montoComisiones;
	}

	public void setMontoComisiones(BigDecimal montoComisiones) {
		this.montoComisiones = montoComisiones;
	}

	public BigDecimal getMontoTotalUE() {
		return montoTotalUE;
	}

	public void setMontoTotalUE(BigDecimal montoTotalUE) {
		this.montoTotalUE = montoTotalUE;
	}

	public BigDecimal getMontoCapitalUE() {
		return montoCapitalUE;
	}

	public void setMontoCapitalUE(BigDecimal montoCapitalUE) {
		this.montoCapitalUE = montoCapitalUE;
	}

	public BigDecimal getMontoInteresUE() {
		return montoInteresUE;
	}

	public void setMontoInteresUE(BigDecimal montoInteresUE) {
		this.montoInteresUE = montoInteresUE;
	}

	public BigDecimal getCotizacionUE() {
		return cotizacionUE;
	}

	public void setCotizacionUE(BigDecimal cotizacionUE) {
		this.cotizacionUE = cotizacionUE;
	}

	public String getFechaCotizacionUE() {
		return fechaCotizacionUE;
	}

	public void setFechaCotizacionUE(String fechaCotizacionUE) {
		this.fechaCotizacionUE = fechaCotizacionUE;
	}

}
