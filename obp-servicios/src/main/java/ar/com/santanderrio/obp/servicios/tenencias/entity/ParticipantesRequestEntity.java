package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class ParticipantesRequestEntity extends RequestBaseFirmado {
	
	@JsonProperty("Datos")
	private DatosDetallesParticipantesRequestEntity datos;

	/**
	 * @return the datos
	 */
	public DatosDetallesParticipantesRequestEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosDetallesParticipantesRequestEntity datos) {
		this.datos = datos;
	}

}
