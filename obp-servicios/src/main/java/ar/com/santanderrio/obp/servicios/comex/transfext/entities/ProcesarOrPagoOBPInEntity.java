package ar.com.santanderrio.obp.servicios.comex.transfext.entities;

import java.math.BigDecimal;

public class ProcesarOrPagoOBPInEntity {

	private Short aceptaDdjj;
	private String concepto;
	private String cuentaCredito;
	private BigDecimal importePago;
	private String nroDocCliente;
	private Integer nroForm;
	private String nroOperacion;
	private String nupCliente;
	private String razonSocial;
	private String tipoDocCliente;
	private Short empresaVinculada;

	public Short getAceptaDdjj() {
		return aceptaDdjj;
	}

	public void setAceptaDdjj(Short aceptaDdjj) {
		this.aceptaDdjj = aceptaDdjj;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	public BigDecimal getImportePago() {
		return importePago;
	}

	public void setImportePago(BigDecimal importePago) {
		this.importePago = importePago;
	}

	public String getNroDocCliente() {
		return nroDocCliente;
	}

	public void setNroDocCliente(String nroDocCliente) {
		this.nroDocCliente = nroDocCliente;
	}

	public Integer getNroForm() {
		return nroForm;
	}

	public void setNroForm(Integer nroForm) {
		this.nroForm = nroForm;
	}

	public String getNroOperacion() {
		return nroOperacion;
	}

	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

	public String getNupCliente() {
		return nupCliente;
	}

	public void setNupCliente(String nupCliente) {
		this.nupCliente = nupCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoDocCliente() {
		return tipoDocCliente;
	}

	public void setTipoDocCliente(String tipoDocCliente) {
		this.tipoDocCliente = tipoDocCliente;
	}

	public Short getEmpresaVinculada() {
		return empresaVinculada;
	}

	public void setEmpresaVinculada(Short empresaVinculada) {
		this.empresaVinculada = empresaVinculada;
	}

}
