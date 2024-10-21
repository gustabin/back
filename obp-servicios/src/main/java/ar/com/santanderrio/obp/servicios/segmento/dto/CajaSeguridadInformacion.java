package ar.com.santanderrio.obp.servicios.segmento.dto;

import org.beanio.annotation.Field;

public class CajaSeguridadInformacion {

	@Field
	private String producto;
	
	@Field
	private String descripcionProducto;
	
	@Field
	private String fechaDesde;
	
	@Field
	private String fechaHasta;
	
	@Field
	private String codigoBonificacion;
	
	@Field
	private String descripcionBonificacion;
	
	@Field
	private String porcentajeBonificacion;
	
	@Field
	private String sucursalCaja;
	
	@Field
	private String contratoCaja;

	
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getCodigoBonificacion() {
		return codigoBonificacion;
	}

	public void setCodigoBonificacion(String codigoBonificacion) {
		this.codigoBonificacion = codigoBonificacion;
	}

	public String getDescripcionBonificacion() {
		return descripcionBonificacion;
	}

	public void setDescripcionBonificacion(String descripcionBonificacion) {
		this.descripcionBonificacion = descripcionBonificacion;
	}

	public String getPorcentajeBonificacion() {
		return porcentajeBonificacion;
	}

	public void setPorcentajeBonificacion(String porcentajeBonificacion) {
		this.porcentajeBonificacion = porcentajeBonificacion;
	}

	public String getSucursalCaja() {
		return sucursalCaja;
	}

	public void setSucursalCaja(String sucursalCaja) {
		this.sucursalCaja = sucursalCaja;
	}

	public String getContratoCaja() {
		return contratoCaja;
	}

	public void setContratoCaja(String contratoCaja) {
		this.contratoCaja = contratoCaja;
	}
	
	
}
