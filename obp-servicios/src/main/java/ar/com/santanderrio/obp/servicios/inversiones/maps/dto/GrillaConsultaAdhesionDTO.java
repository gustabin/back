package ar.com.santanderrio.obp.servicios.inversiones.maps.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;

public class GrillaConsultaAdhesionDTO {
	
	List<AdhesionesDTO> suscripcionesActivas = new ArrayList<AdhesionesDTO>();
	List<AdhesionesDTO> suscripcionesInactivas = new ArrayList<AdhesionesDTO>();
	@JsonProperty("Items")
	List<ControlMaps> items = new ArrayList<ControlMaps>();
	
	public List<AdhesionesDTO> getSuscripcionesActivas() {
		return suscripcionesActivas;
	}
	public void setSuscripcionesActivas(List<AdhesionesDTO> suscripcionesActivas) {
		this.suscripcionesActivas = suscripcionesActivas;
	}
	public List<AdhesionesDTO> getSuscripcionesInactivas() {
		return suscripcionesInactivas;
	}
	public void setSuscripcionesInactivas(List<AdhesionesDTO> suscripcionesInactivas) {
		this.suscripcionesInactivas = suscripcionesInactivas;
	}
	public List<ControlMaps> getItems() {
		return items;
	}
	public void setItems(List<ControlMaps> items) {
		this.items = items;
	}
}
