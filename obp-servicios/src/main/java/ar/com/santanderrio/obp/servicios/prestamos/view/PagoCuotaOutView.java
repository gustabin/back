package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PagoCuotaOutView {
	private BigDecimal interesesCompPeriodo;
	private BigDecimal interesesCompVto;
	private BigDecimal otrosImpuestos;
	private String nroComprobante;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fechaHora;
	private BigDecimal importeCuota;
	private BigDecimal capitalCuota;
	private BigDecimal iva;
	private BigDecimal iibb;
	private BigDecimal importeCuotaUVA;
	private BigDecimal capitalUVA;
	private BigDecimal interesesUVA;
	private BigDecimal tea;
	private BigDecimal cftnaConImpuestos;
	private BigDecimal cftnaSinImpuestos;

	private String moneda;

	public BigDecimal getInteresesCompPeriodo() {
		return interesesCompPeriodo;
	}

	public void setInteresesCompPeriodo(BigDecimal interesesCompPeriodo) {
		this.interesesCompPeriodo = interesesCompPeriodo;
	}

	public BigDecimal getInteresesCompVto() {
		return interesesCompVto;
	}

	public void setInteresesCompVto(BigDecimal interesesCompVto) {
		this.interesesCompVto = interesesCompVto;
	}

	public BigDecimal getOtrosImpuestos() {
		return otrosImpuestos;
	}

	public void setOtrosImpuestos(BigDecimal otrosImpuestos) {
		this.otrosImpuestos = otrosImpuestos;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public BigDecimal getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(BigDecimal importeCuota) {
		this.importeCuota = importeCuota;
	}

	public BigDecimal getCapitalCuota() {
		return capitalCuota;
	}

	public void setCapitalCuota(BigDecimal capitalCuota) {
		this.capitalCuota = capitalCuota;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getImporteCuotaUVA() {
		return importeCuotaUVA;
	}

	public void setImporteCuotaUVA(BigDecimal importeCuotaUVA) {
		this.importeCuotaUVA = importeCuotaUVA;
	}

	public BigDecimal getCapitalUVA() {
		return capitalUVA;
	}

	public void setCapitalUVA(BigDecimal capitalUVA) {
		this.capitalUVA = capitalUVA;
	}

	public BigDecimal getInteresesUVA() {
		return interesesUVA;
	}

	public void setInteresesUVA(BigDecimal interesesUVA) {
		this.interesesUVA = interesesUVA;
	}

	public BigDecimal getTea() {
		return tea;
	}

	public void setTea(BigDecimal tea) {
		this.tea = tea;
	}

	public BigDecimal getCftnaConImpuestos() {
		return cftnaConImpuestos;
	}

	public void setCftnaConImpuestos(BigDecimal cftnaConImpuestos) {
		this.cftnaConImpuestos = cftnaConImpuestos;
	}

	public BigDecimal getCftnaSinImpuestos() {
		return cftnaSinImpuestos;
	}

	public void setCftnaSinImpuestos(BigDecimal cftnaSinImpuestos) {
		this.cftnaSinImpuestos = cftnaSinImpuestos;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getIibb() {
		return iibb;
	}

	public void setIibb(BigDecimal iibb) {
		this.iibb = iibb;
	}

}
