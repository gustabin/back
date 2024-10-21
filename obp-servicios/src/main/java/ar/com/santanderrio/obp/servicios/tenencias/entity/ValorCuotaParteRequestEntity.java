package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class ValorCuotaParteRequestEntity extends RequestBaseFirmado{
	
	@JsonProperty("Datos")
	private DatosDetalleCuotaparteRequestEntity datos;

	public DatosDetalleCuotaparteRequestEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosDetalleCuotaparteRequestEntity datos) {
		this.datos = datos;
	}
	
	

}
