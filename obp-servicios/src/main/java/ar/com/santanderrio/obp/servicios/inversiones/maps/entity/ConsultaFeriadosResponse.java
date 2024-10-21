package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ResponseEntityBase;

public class ConsultaFeriadosResponse extends ResponseEntityBase{
	
	@JsonProperty("Datos")
	private ListaFeriadosEntity datos;

	/**
	 * @return the datos
	 */
	public ListaFeriadosEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(ListaFeriadosEntity datos) {
		this.datos = datos;
	}

	
	
	

}
