package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosDetalleCuotaparteRequestEntity extends EntityBase {
	
	@JsonProperty("Fecha")
	private String fecha;
	
	@JsonProperty("CodigoFondo")
	private String espeCod;
	
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEspeCod() {
		return espeCod;
	}

	public void setEspeCod(String espeCod) {
		this.espeCod = espeCod;
	}

	public DatosServiciosEntity getDatosServicios() {
		return datosServicios;
	}

	public void setDatosServicios(DatosServiciosEntity datosServicios) {
		this.datosServicios = datosServicios;
	}
	
	
	
	

}
