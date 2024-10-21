package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

@SuppressWarnings("serial")
public class DisponiblesRTFEntity extends RequestBaseFirmado{
	
	@JsonProperty("Datos")
	private DatosDisponiblesRTFEntity datos;

	public DatosDisponiblesRTFEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosDisponiblesRTFEntity datos) {
		this.datos = datos;
	}
	
	

}
