package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ResponseEntityBase;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;

public class ConsultaAdhesionMapsResponse extends ResponseEntityBase{

	@JsonProperty("Datos")
	private FormularioControl datos;

	public FormularioControl getDatos() {
		return datos;
	}

	public void setDatos(FormularioControl datos) {
		this.datos = datos;
	}
}
