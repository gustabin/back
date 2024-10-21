package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class BajaAdhesionRequestEntity extends RequestBaseFirmado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3255405388555281551L;
	
	@JsonProperty("Datos")
	private RequestMap datos = new BajaAdhesionMapsDatosRequest();

	public RequestMap getDatos() {
		return datos;
	}

	public void setDatos(RequestMap datos) {
		this.datos = datos;
	}

}
