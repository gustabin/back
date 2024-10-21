package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;

public class BajaAdhesionMapsDatosRequest implements RequestMap {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("IdServicio")
	private String idServicio;
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	@JsonProperty("IdSimulacion")
	private String idSimulacion;
	@JsonProperty("Nup")
	private String nup;
	@JsonProperty("Segmento")
	private String segmento;
	@JsonProperty("IdAdhesion")
	private long idAdhesion;
	@JsonProperty("Estado")
	private String estado;
	@JsonProperty("Items")
	private List<ControlMaps> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public long getIdAdhesion() {
		return idAdhesion;
	}

	public void setIdAdhesion(long idAdhesion) {
		this.idAdhesion = idAdhesion;
	}

	public List<ControlMaps> getItems() {
		return items;
	}

	public void setItems(List<ControlMaps> items) {
		this.items = items;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdSimulacion() {
		return idSimulacion;
	}

	public void setIdSimulacion(String idSimulacion) {
		this.idSimulacion = idSimulacion;
	}
	
	
}
