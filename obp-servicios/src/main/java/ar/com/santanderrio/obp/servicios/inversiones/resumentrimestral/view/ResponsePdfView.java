package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.view;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity.InfoRTFEntity;

public class ResponsePdfView {
	@JsonProperty("Datos")
	List<InfoRTFEntity> datos;

	public List<InfoRTFEntity> getDatos() {
		return datos;
	}

	public void setDatos(List<InfoRTFEntity> datos) {
		this.datos = datos;
	}
	
	
}
