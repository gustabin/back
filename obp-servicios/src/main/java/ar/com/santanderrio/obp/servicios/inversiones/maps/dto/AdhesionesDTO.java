package ar.com.santanderrio.obp.servicios.inversiones.maps.dto;

public class AdhesionesDTO {
	
	private String servicio;

	private String descripcion;

	private String vigenciaDesde;

	private String vigenciaHasta;

	private String estado;

	private long id;

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public String getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
