package ar.com.santanderrio.obp.servicios.clientes.view;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.clientes.entities.DatosConsultaCtasEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

@SuppressWarnings("serial")
public class RequestCtasTitServiceView  extends RequestBaseFirmado{
	
	@JsonProperty("Datos")
	private DatosConsultaCtasEntity datos;

	public DatosConsultaCtasEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosConsultaCtasEntity datos) {
		this.datos = datos;
	}
	

}
