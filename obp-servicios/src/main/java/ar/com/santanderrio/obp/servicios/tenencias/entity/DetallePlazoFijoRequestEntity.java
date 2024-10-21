package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class DetallePlazoFijoRequestEntity  extends RequestBaseFirmado{
	
	@JsonProperty("Datos")
	private DatosDetallesPlazoFijoRequestEntity datos;

	/**
	 * @return the datos
	 */
	public DatosDetallesPlazoFijoRequestEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosDetallesPlazoFijoRequestEntity datos) {
		this.datos = datos;
	}

	

}
