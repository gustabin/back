package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import ar.com.santanderrio.obp.servicios.debinrecurrente.bo.EstadoRecurrenciaDebinEnum;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class CrearRecurrenciaView {

	private String nombreFantasia;
	private String cbuComprador;
	private String cuitComprador;
	private String concepto;
	private String moneda;
	private String detalle;
	private String provision;
	private String referencia;
	private String cuitVendedor;
	private AutentificacionDTO desafio;
	private String mensajeFeedback;
	private String nroCuenta;
	private DatosComprobanteDebinRecurrente datosComprobante;
	private EstadoRecurrenciaDebinEnum estado;


	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getCbuComprador() {
		return cbuComprador;
	}
	
	public void setCbuComprador(String cbuComprador) {
		this.cbuComprador = cbuComprador;
	}
	
	public String getCuitComprador() {
		return cuitComprador;
	}
	
	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
	}
	
	public String getConcepto() {
		return concepto;
	}
	
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	public String getMoneda() {
		return moneda;
	}
	
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getProvision() {
		return provision;
	}
	
	public void setProvision(String provision) {
		this.provision = provision;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getCuitVendedor() {
		return cuitVendedor;
	}
	
	public void setCuitVendedor(String cuitVendedor) {
		this.cuitVendedor = cuitVendedor;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public DatosComprobanteDebinRecurrente getDatosComprobante() {
		return datosComprobante;
	}

	public void setDatosComprobante(DatosComprobanteDebinRecurrente datosComprobante) {
		this.datosComprobante = datosComprobante;
	}

	public EstadoRecurrenciaDebinEnum getEstado() { return estado; }

	public void setEstado(EstadoRecurrenciaDebinEnum estado) { this.estado = estado; }
	
}
