package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class DetalleFondoRimpRequestEntity extends RequestBaseFirmado {

	@JsonProperty("Datos")
	private DatosDetalleFondoRimpRequestEntity datos;
	
	

	public DatosDetalleFondoRimpRequestEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosDetalleFondoRimpRequestEntity datoPrueba) {
		this.datos = datoPrueba;
	}
		
		
}
