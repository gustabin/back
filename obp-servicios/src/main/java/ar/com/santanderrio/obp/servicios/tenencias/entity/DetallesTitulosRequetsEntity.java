package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class DetallesTitulosRequetsEntity  extends RequestBaseFirmado {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("Datos")
	private DatosTitulosRequestEntity datos;

	/**
	 * @return the datos
	 */
	public DatosTitulosRequestEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosTitulosRequestEntity datos) {
		this.datos = datos;
	}

}
