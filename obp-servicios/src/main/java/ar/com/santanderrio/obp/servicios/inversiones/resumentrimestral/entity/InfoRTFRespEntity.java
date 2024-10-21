package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class InfoRTFRespEntity {
	
	@JsonProperty("Datos")
	private InfoRTFEntity datos;

	public InfoRTFEntity getDatos() {
		return datos;
	}

	public void setDatos(InfoRTFEntity datos) {
		this.datos = datos;
	}
	
	

}
