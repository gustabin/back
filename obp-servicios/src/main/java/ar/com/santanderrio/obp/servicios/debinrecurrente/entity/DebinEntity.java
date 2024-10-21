package ar.com.santanderrio.obp.servicios.debinrecurrente.entity;

import java.math.BigDecimal;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class DebinEntity {

	@Field
	String idDebin;
	
	@Field
	String tpoDebin;
	
	@Field
	Integer moneda;
	
	@Field
	String claseDebin;
	
	@Field
	String canal;
	
	@Field
	String fecNegocio;
	
	@Field
	String fecOperacion;
	
	@Field
	BigDecimal importe;
	
	@Field
	String cbuCompra;
	
	@Field
	Long cuitCompra;
	
	@Field
	String cbuVende;
	
	@Field
	Long cuitVende;
	
	@Field
	String estado;
	
	@Field
	String cbu;
	
	@Field
	String nio;
	
	@Field
	String nroSolicitud;
	
	@Field
	String fecHorAlta;
	
	@Field
	Integer canalBsr;
	
	@Field
	String usofut;

	
	public String getIdDebin() {
		return idDebin;
	}

	public void setIdDebin(String idDebin) {
		this.idDebin = idDebin;
	}

	public String getTpoDebin() {
		return tpoDebin;
	}

	public void setTpoDebin(String tpoDebin) {
		this.tpoDebin = tpoDebin;
	}

	public Integer getMoneda() {
		return moneda;
	}

	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}

	public String getClaseDebin() {
		return claseDebin;
	}

	public void setClaseDebin(String claseDebin) {
		this.claseDebin = claseDebin;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getFecNegocio() {
		return fecNegocio;
	}

	public void setFecNegocio(String fecNegocio) {
		this.fecNegocio = fecNegocio;
	}

	public String getFecOperacion() {
		return fecOperacion;
	}

	public void setFecOperacion(String fecOperacion) {
		this.fecOperacion = fecOperacion;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getCbuCompra() {
		return cbuCompra;
	}

	public void setCbuCompra(String cbuCompra) {
		this.cbuCompra = cbuCompra;
	}

	public long getCuitCompra() {
		return cuitCompra;
	}

	public void setCuitCompra(Long cuitCompra) {
		this.cuitCompra = cuitCompra;
	}

	public String getCbuVende() {
		return cbuVende;
	}

	public void setCbuVende(String cbuVende) {
		this.cbuVende = cbuVende;
	}

	public Long getCuitVende() {
		return cuitVende;
	}

	public void setCuitVende(Long cuitVende) {
		this.cuitVende = cuitVende;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getNio() {
		return nio;
	}

	public void setNio(String nio) {
		this.nio = nio;
	}

	public String getNroSolicitud() {
		return nroSolicitud;
	}

	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

	public String getFecHorAlta() {
		return fecHorAlta;
	}

	public void setFecHorAlta(String fecHorAlta) {
		this.fecHorAlta = fecHorAlta;
	}

	public Integer getCanalBsr() {
		return canalBsr;
	}

	public void setCanalBsr(Integer canalBsr) {
		this.canalBsr = canalBsr;
	}

	public String getUsofut() {
		return usofut;
	}

	public void setUsofut(String usofut) {
		this.usofut = usofut;
	}
	
}
