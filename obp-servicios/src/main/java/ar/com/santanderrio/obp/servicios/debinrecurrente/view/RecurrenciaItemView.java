package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

public class RecurrenciaItemView {

	private String fechaAdhesion;

	private String empresa;
	
	private String servicio;

	private String estado;
	
	private String debinId;
	
	private String referencia;
	
	private String cbu;
	
	private String descripcion;
	
	private String cuitEmpresa;
	
	private String concepto;
	
	private String moneda;
	
	private String cuitComprador; 
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String detalle) {
		this.descripcion = detalle;
	}

	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	public String getDebinId() {
		return debinId;
	}

	public void setDebinId(String debinId) {
		this.debinId = debinId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaAdhesion() {
		return fechaAdhesion;
	}

	public void setFechaAdhesion(String fechaAdhesion) {
		this.fechaAdhesion = fechaAdhesion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
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

	public String getCuitComprador() {
		return cuitComprador;
	}

	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
	}
	
}
