package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * @author B039920
 * Request para Alta Adhesion, servicio Maps
 *
 */
public class AltaAdhesionMapsRequestEntity extends RequestBaseFirmado {

	private static final long serialVersionUID = 8366745397063345040L;
	
	@JsonProperty("Datos")
	private RequestMap datos = new AltaAdhesionMapsDatosRequest();

	public RequestMap getDatos() {
		return datos;
	}

	public void setDatos(RequestMap datos) {
		this.datos = datos;
	}

}
