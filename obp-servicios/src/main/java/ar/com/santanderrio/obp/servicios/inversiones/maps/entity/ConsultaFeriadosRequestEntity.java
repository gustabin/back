package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class ConsultaFeriadosRequestEntity extends RequestBaseFirmado {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("Datos")
	private DatosFeriadosEntity datos;

	/**
	 * @return the datos
	 */
	public DatosFeriadosEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosFeriadosEntity datos) {
		this.datos = datos;
	}
	
}
